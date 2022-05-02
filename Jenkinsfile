pipeline {
    agent {
        node {
            label 'linux'
        }
    }
    tools {
        maven 'maven'
        jdk 'openjdk11'
    }

    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '40', artifactNumToKeepStr: '40'))
        timeout(time: 1, unit: 'HOURS')
    }
    environment {
        JACOCO_VERSION = "0.8.7"
        JAVA_HOME = tool name: 'openjdk11', type: 'jdk'
    }
    parameters {
        booleanParam(name: 'isRelease', defaultValue: false, description: 'Skal prosjektet releases? Alle andre parametere ignoreres ved snapshot-bygg.')
        string(name: "specifiedVersion", defaultValue: "", description: "Hva er det nye versjonsnummeret (X.X.X)? Som default releases snapshot-versjonen")
        string(name: "apiVersion", defaultValue: "main", description: "Hva er API versjon som skal brukes under bygg? Default er main")
        text(name: "releaseNotes", defaultValue: "Ingen endringer utført", description: "Hva er endret i denne releasen?")
        string(name: "reviewer", defaultValue: "Endringene krever ikke review", description: "Hvem har gjort review?")
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    wrap([$class: 'BuildUser']) {
                        env.user = sh ( script: 'echo "${BUILD_USER}"', returnStdout: true).trim()
                    }

                    env.GIT_SHA = sh(returnStdout: true, script: 'git rev-parse HEAD').substring(0, 7)
                    env.REPO_NAME = scm.getUserRemoteConfigs()[0].getUrl().tokenize('/').last().split("\\.")[0]
                    env.PROFILE_EXTRA = ""
                    env.API_VERSION = "${params.apiVersion}"
                }
                sh '''
                 echo "PATH = ${PATH}"
                 echo "M2_HOME = ${M2_HOME}"
                '''
                sh 'git submodule  update --init --recursive --remote'

                dir("fiks-arkiv-specification") {
                    sh "git fetch"
                    sh "git checkout ${API_VERSION}"
                    sh "git pull"
                }

                rtMavenDeployer (
                        id: "MAVEN_DEPLOYER",
                        serverId: "KS Artifactory",
                        releaseRepo: "ks-maven",
                        snapshotRepo: "maven-all"
                )
                rtMavenResolver (
                        id: "MAVEN_RESOLVER",
                        serverId: "KS Artifactory",
                        releaseRepo: "maven-all",
                        snapshotRepo: "maven-all"
                )
                rtBuildInfo(
                        captureEnv: true,
                        maxBuilds: 30
                )
            }
        }

        stage('Build') {
            steps {
                script {
                    def pom = readMavenPom file: 'pom.xml'
                    env.POM_VERSION = pom.version

                }
                rtMavenRun (
                        pom: 'pom.xml',
                        goals: "-U -B ${PROFILE_EXTRA} clean install",
                        deployerId: "MAVEN_DEPLOYER",
                        resolverId: "MAVEN_RESOLVER"
                )
            }
            post {
                success {
                    recordIssues enabledForFailure: true, tool: java()
                    rtMavenRun(
                        pom: 'pom.xml',
                        goals: "org.jacoco:jacoco-maven-plugin:${JACOCO_VERSION}:report",
                        tool: 'maven',
                        resolverId: "MAVEN_RESOLVER"
                    )
                    jacoco(execPattern: '**/*.exec')
                }
            }
        }
        stage('Sonar') {
            when {
                anyOf {
                    branch 'master'
                    branch 'main'
                }
            }
            steps {
                withSonarQubeEnv('SonarCloud') {
                    rtMavenRun (
                            pom: 'pom.xml',
                            goals: '-B sonar:sonar',
                            resolverId: "MAVEN_RESOLVER",
                            opts: "-Dsonar.organization=ks-no")
                }
            }

        }

        stage('Validate') {
            steps {
                script {
                    if (params.isRelease) {
                        if (params.specifiedVersion != null && !params.specifiedVersion.isEmpty() && !isValidReleaseVersion(params.specifiedVersion)) {
                            error("Invalid version: " + params.releaseVersion);
                        }
                        sh(script: 'mvn -U -B validate')
                    } else {
                        sh(script: 'mvn -U -B -P snapshot validate')
                    }
                }
            }
        }

        stage('Release: new version') {
            when {
                allOf {
                    expression { params.isRelease }
                    anyOf {
                        branch 'master'
                        branch 'main'
                    }
                }
            }

            steps {
                script {
                    if (params.specifiedVersion == null || params.specifiedVersion == "")
                        env.releaseVersion = env.POM_VERSION.replace("-SNAPSHOT", "")
                    else
                        env.releaseVersion = params.specifiedVersion

                    currentBuild.description = "${env.user} released version ${env.releaseVersion}"
                }

                gitCheckout(env.BRANCH_NAME)
                prepareReleaseNoBuild releaseVersion
                rtMavenRun(
                        pom: 'pom.xml',
                        goals: '-DskipTests -U -B clean install',
                        resolverId: 'MAVEN_RESOLVER'
                )
                gitTag(isRelease, releaseVersion)
            }
        }

        stage('Run component tests') {
            when {
                expression { pipelineParams.componentTestProject }
            }

            steps {
                build job: "/KS/${pipelineParams.componentTestProject}/master", propagate: true, wait: true
            }
        }

        stage('Deploy to Artifactory') {
            when {
                branch 'master'
            }

            steps {
                configFileProvider([configFile(fileId: 'oss-settings.xml', variable: 'SETTINGS_XML')]) {
                    script {
                        def deployRepo = ""
                        if (params.isRelease) {
                            deployRepo = " -DaltReleaseDeploymentRepository=central::https://artifactory.fiks.ks.no/artifactory/ks-maven/"
                        } else {
                            deployRepo = " -DaltSnapshotDeploymentRepository=snapshots::https://artifactory.fiks.ks.no/artifactory/maven-all/"
                        }

                        sh(script: "mvn -Dmaven.install.skip=true -DskipTests -Duse-nexus-staging-maven-plugin=false ${deployRepo} -s $SETTINGS_XML jar:jar org.apache.maven.plugins:maven-deploy-plugin:3.0.0-M1:deploy")
                    }
                }
            }
        }

        stage('Deploy to Maven Central') {
            when {
                branch 'master'
            }

            steps {
                configFileProvider([configFile(fileId: 'oss-settings.xml', variable: 'SETTINGS_XML')]) {
                    script {
                        def profile = ""
                        if (params.isRelease) {
                            profile = "-P release"
                        }
                        sh(script: "mvn -s $SETTINGS_XML ${profile} -Dmaven.install.skip=true -DskipTests -Dmaven.test.skip=true -Duse-nexus-staging-maven-plugin=true -DautoReleaseAfterClose=${params.autoReleaseAfterClose} deploy")
                    }

                }
            }
        }

        stage('Release: set snapshot') {
            when {
                expression { params.isRelease }
            }
            steps {
                setSnapshot releaseVersion
                gitPush()
            }
            post {
                success {
                    createGithubRelease env.REPO_NAME, params.reviewer, params.releaseNotes, env.releaseVersion, env.user
                }
            }
        }
    }
    post {
        always {
            rtPublishBuildInfo(
                    serverId: "KS Artifactory"
            )
            jiraSendBuildInfo(
                site: "ksfiks.atlassian.net"
            )
            deleteDir()
        }
    }
}


@NonCPS
def isValidReleaseVersion(String version){
    def m = version =~ /\d+.\d+.\d+/
    assert m instanceof Matcher
    if (!m) {
        error("Invalid version: " + version);
    }
    env.TAG_VERSION="${version}"
    def commit = sh (returnStdout: true, script: '''
        version=$(git rev-parse "$TAG_VERSION" 2>/dev/null)||true
        if [ "$version" != "$TAG_VERSION" ]; then
            echo "Version is already released - version ${TAG_VERSION}"
            exit 128
        fi
        echo "Version is valid for release - version ${TAG_VERSION}"
    ''')

    return true
}

pipeline {
    agent {
        node {
            label 'linux'
        }
    }
    tools {
        maven 'maven'
        jdk 'openjdk17'
    }

    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '40', artifactNumToKeepStr: '40'))
        timeout(time: 1, unit: 'HOURS')
    }
    environment {
        JACOCO_VERSION = "0.8.7"
        JAVA_HOME = tool name: 'openjdk17', type: 'jdk'
    }
    parameters {
        booleanParam(name: 'isRelease', defaultValue: false, description: 'Skal prosjektet releases? Alle andre parametere ignoreres ved snapshot-bygg.')
        string(name: "specifiedVersion", defaultValue: "", description: "Hva er det nye versjonsnummeret (X.X.X)? Som default releases snapshot-versjonen")
        string(name: "apiVersion", defaultValue: "", description: "Tag for fiks-arkiv-specification")
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
                    if(params.apiVersion?.trim()) {
                        env.API_VERSION = "${params.apiVersion}"
                    } else {
                        env.API_VERSION = ""
                    }
                }
                sh '''
                 echo "PATH = ${PATH}"
                 echo "M2_HOME = ${M2_HOME}"
                '''
                sh 'git submodule  update --init --recursive --remote'
                dir('fiks-arkiv-spsification') {
                    sh 'git fetch'
                    sh 'git checkout ${API_VERSION}'
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

        stage('Snapshot: verify pom') {
            when {
                expression { !params.isRelease }
            }

            steps {
                rtMavenRun (
                        pom: 'pom.xml',
                        goals: "-T0.5C enforcer:enforce@validate-snap",
                        resolverId: "MAVEN_RESOLVER"
                )
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
                prepareReleaseNoBuildRT(releaseVersion,'MAVEN_RESOLVER')
                rtMavenRun(
                        pom: 'pom.xml',
                        goals: '-DskipTests -U -B clean install',
                        deployerId: 'MAVEN_DEPLOYER',
                        resolverId: 'MAVEN_RESOLVER'
                )
                gitTag(isRelease, releaseVersion)
            }
        }
        stage('Release: set snapshot') {
            when {
                expression { params.isRelease }
            }
            steps {
                setSnapshotRT(releaseVersion, 'MAVEN_RESOLVER')
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
        }
    }
}
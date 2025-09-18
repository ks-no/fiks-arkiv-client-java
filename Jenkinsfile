import java.util.regex.Matcher

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
        POM_VERSION = readMavenPom(file: 'pom.xml').getVersion()
        ARTIFACT_ID = readMavenPom(file: 'pom.xml').getArtifactId()
        JACOCO_VERSION = "0.8.7"
        JAVA_HOME = tool name: 'openjdk11', type: 'jdk'
    }
    parameters {
        booleanParam(name: 'isRelease', defaultValue: false, description: 'Skal prosjektet releases? Alle andre parametere ignoreres ved snapshot-bygg.')
        string(name: "specifiedVersion", defaultValue: "", description: "Hva er det nye versjonsnummeret (X.X.X)? Som default releases snapshot-versjonen")
        string(name: "apiVersion", defaultValue: "main", description: "Hva er API versjon som skal brukes under bygg? Default er main")
        text(name: "releaseNotes", defaultValue: "Ingen endringer utført", description: "Hva er endret i denne releasen?")
        string(name: "reviewer", defaultValue: "Endringene krever ikke review", description: "Hvem har gjort review?")
        booleanParam(name: 'autoReleaseAfterClose', defaultValue: true, description: 'Automatisk release til maven central?')
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
                echo "PATH = ${env.PATH}"
                echo "M2_HOME = ${env.M2_HOME}"
                echo "POM_VERSION = ${env.POM_VERSION}"
                echo "ARTIFACT_ID = ${env.ARTIFACT_ID}"

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

        stage('Validate') {
            steps {
                script {
                    if (params.isRelease) {
                        if (params.specifiedVersion != null && !params.specifiedVersion.isEmpty() && !isValidReleaseVersion(params.specifiedVersion)) {
                            error("Invalid version: " + params.releaseVersion);
                        }
                        withMaven {
                          sh(script: 'mvn -U -B validate')
                        }
                    } else {
                        withMaven {
                          sh(script: 'mvn -U -B -P snapshot validate')
                        }
                    }
                }
            }
        }
        stage('Release: set new version') {
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
                withMaven {
                    prepareReleaseNoBuild releaseVersion
                }
                gitTag(isRelease, releaseVersion)
            }
        }

        stage('Build') {
            steps {
                rtMavenRun (
                        pom: 'pom.xml',
                        goals: "-U -B clean install",
                        opts: " -DinstallAtEnd=true -Dkotlin.compiler.incremental=false -Dkotlin.environment.keepalive=true",
                        resolverId: "MAVEN_RESOLVER",
                        deployerId: "MAVEN_DEPLOYER"
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

        stage('Security check') {
            when {
                anyOf {
                    branch 'master'
                    branch 'main'
                }
            }
            steps {
                warnError(message: "Feilet under generering av bom") {
                    rtMavenRun(
                        pom: 'pom.xml',
                        goals: '-B org.cyclonedx:cyclonedx-maven-plugin:2.7.10:makeAggregateBom',
                        resolverId: "MAVEN_RESOLVER",
                        opts: "-DexcludeTestProject=true -Dcyclonedx.skipAttach=false -Dcyclonedx.projectType=application -Dcyclonedx.verbose=true"
                    )
                }
            }

        }

         stage('Deploy to Maven Central') {
                        when {
                            anyOf {
                                branch 'master'
                                branch 'main'
                            }
                        }
                        environment {
                            MAVEN_GPG_PASSPHRASE = credentials('gpg.oss.passphrase')
                            GNUPGHOME = "${env.WORKSPACE}/.gnupg"
                            GPG_KEYS = credentials('gpg.oss.keys')
                            profile = "${params.isRelease ? '-P release' : ''}"
                        }

                        steps {
                            sh(script: 'gpg --batch --with-colons --import $GPG_KEYS', label: 'Import GPG keys')
                            withMaven(mavenSettingsConfig: 'oss-settings.xml') {
                                sh script: "mvn -B -e ${env.profile ?: ''} -Dmaven.install.skip=true -DskipTests -Dmaven.test.skip=true deploy", label: 'Deploy to Maven Central'
                            }
                        }
                        post{
                            cleanup {
                                dir(path: '.gnupg') {
                                    deleteDir()
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

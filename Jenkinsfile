pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {

        stage('Checkout Build Repo') {
            steps {
                dir('build-project') {
                    git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                }
            }
        }

        stage('Build Maven Project') {
            steps {
                dir('build-project') {
                    sh 'mvn -Dmaven.test.failure.ignore=true clean package'
                }
            }
            post {
                always {
                    junit 'build-project/**/surefire-reports/*.xml'
                    archiveArtifacts 'build-project/target/*.jar'
                }
            }
        }

        stage('Checkout Playwright Repo') {
            steps {
                dir('playwright-project') {
                    git branch: 'main',
                        url: 'https://github.com/suneel0811/PlawrightOpenCart'
                }
            }
        }

        stage('Install Playwright Browsers') {
            steps {
                dir('playwright-project') {
                    sh 'mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"'
                }
            }
        }

        stage('Run Playwright Tests') {
            steps {
                dir('playwright-project') {
                    sh 'mvn clean test -Dsurefire.suiteXmlFiles=testng.xml'
                }
            }
            post {
                always {
                    junit 'playwright-project/**/surefire-reports/*.xml'
                }
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'playwright-project/test_reports',
                    reportFiles: 'TestExecutionReport.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }
}
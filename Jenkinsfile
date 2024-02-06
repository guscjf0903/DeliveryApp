pipeline {
    environment {
        repository = "majanada2/jenkins_test"
        DOCKERHUB_CREDENTIALS = credentials('dockerhub_token')
        dockerImageUI = ''
        dockerImageAPI = ''
    }
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }

            post {
                success {
                    echo 'Successfully Cloned Repository'
                }
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }

        stage('Build gradle') {
            steps {
                script {
                    sh './gradlew clean build'
                }
            }
            post {
                success {
                    echo "Successfully Build gradle"
                }
                failure {
                    echo "Fail to build gradle"
                    error 'This pipeline stops here...'
                }
            }
        }
        stage('Debug') {
            steps {
                echo 'Current Working Directory:'
                sh 'pwd'
            }
        }

        stage('Build Docker UI') {
            steps {
                echo 'Build Docker UI'
                dir ('DeliveryUI') {
                    script {
                        dockerImageUI = docker.build("${repository}:UI")
                    }
                }
            }
            post {
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }

        stage('Debug After Build Docker UI') {
                steps {
                    echo 'Current Working Directory:'
                    sh 'pwd'
                }
        }

        stage('Build Docker API') {
            steps {
                echo 'Build Docker API'
                dir ('DeliveryAPI') {
                    script {
                        dockerImageAPI = docker.build("${repository}:API")
                    }
                }
            }
            post {
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }

        stage('Push Docker UI') {
            steps {
                echo 'Push Docker UI'
                dir ('DeliveryUI') {
                    script {
                        docker.withRegistry('', DOCKERHUB_CREDENTIALS) {
                            dockerImageUI.push()
                        }
                        dockerImageUI.remove()
                    }
                }
            }
            post {
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }

        stage('Push Docker API') {
            steps {
                echo 'Push Docker API'
                dir ('DeliveryAPI') {
                    script {
                        docker.withRegistry('', DOCKERHUB_CREDENTIALS) {
                            dockerImageAPI.push()
                        }
                        dockerImageAPI.remove()
                    }
                }
            }
            post {
                failure {
                    error 'This pipeline stops here...'
                }
            }

    }
    }
}

pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        BACKEND_IMAGE = 'my-backend-app'
        FRONTEND_IMAGE = 'my-frontend-app'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/ShaikMohammedSameer3903/FARMER-CROPMASTER1.git'
            }
        }

        stage('Build Backend (Spring Boot)') {
            steps {
                dir('backend') {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend (React)') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    sh "docker build -t ${BACKEND_IMAGE}:latest ./backend"
                    sh "docker build -t ${FRONTEND_IMAGE}:latest ./frontend"
                }
            }
        }

        stage('Run with Docker Compose') {
            steps {
                sh "docker-compose -f ${DOCKER_COMPOSE_FILE} down"
                sh "docker-compose -f ${DOCKER_COMPOSE_FILE} up -d --build"
            }
        }
    }

    post {
        always {
            sh 'docker ps -a'
        }
    }
}

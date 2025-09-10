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
                    bat 'mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend (React)') {
            steps {
                dir('frontend') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    bat "docker build -t %BACKEND_IMAGE%:latest ./backend"
                    bat "docker build -t %FRONTEND_IMAGE%:latest ./frontend"
                }
            }
        }

        stage('Run with Docker Compose') {
            steps {
                bat "docker-compose -f %DOCKER_COMPOSE_FILE% down"
                bat "docker-compose -f %DOCKER_COMPOSE_FILE% up -d --build"
            }
        }
    }

    post {
        always {
            bat 'docker ps -a'
        }
    }
}

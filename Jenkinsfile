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
                dir('Back End') {
                    // Use Maven installed on Jenkins machine
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend (React)') {
            steps {
                dir('Front End') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    bat "docker build -t %BACKEND_IMAGE%:latest \"./Back End\""
                    bat "docker build -t %FRONTEND_IMAGE%:latest \"./Front End\""
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

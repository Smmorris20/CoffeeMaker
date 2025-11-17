pipeline {
    agent any

    environment {
        DOCKERHUB = credentials('DHub') // Exposes DOCKERHUB_USR / DOCKERHUB_PSW
    }

    stages {
        stage('Docker Login') {
            steps {
                sh 'echo "$DOCKERHUB_PSW" | docker login -u "$DOCKERHUB_USR" --password-stdin'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    docker stop coffeemaker || true
                    docker rm coffeemaker || true
                    docker rmi smmorris20/coffeemaker || true
                    docker build -t smmorris20/coffeemaker .
                '''
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh '''
                    docker run --rm smmorris20/coffeemaker \
                    java -jar libs/junit-platform-console-standalone.jar \
                    --class-path out:out_test \
                    --scan-class-path
                '''
            }
        }

        stage('Run Application') {
            steps {
                sh 'docker compose up -d'
            }
        }

        stage('Cleaning') {
            steps {
                sh 'docker compose down || true'
            }
        }
    }
}

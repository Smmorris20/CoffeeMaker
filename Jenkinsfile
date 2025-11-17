pipeline {
    agent any

    environment {
        DOCKERHUB = credentials('Dhub')
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
                    -cp "out:test-classes" \
                    --scan-classpath
                '''
            }
        }

        stage('Run Application') {
            steps {
                sh 'docker run -d --name coffeemaker smmorris20/coffeemaker'
            }
        }

        stage('Cleaning') {
            steps {
                sh 'docker stop coffeemaker || true'
                sh 'docker rm coffeemaker || true'
            }
        }
    }
}

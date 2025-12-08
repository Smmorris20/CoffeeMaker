pipeline {
    agent any

    environment {
        DOCKERHUB = credentials('Dhub')

        EC2_HOST = '3.10.59.2'
        EC2_USER = 'ec2-user'
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
                    docker rmi st20285209/coffeemaker || true
                    docker build -t st20285209/coffeemaker .
                '''
            }
        }

        stage('Push Docker Image') {
            steps {
                sh 'docker push st20285209/coffeemaker'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh '''
                    docker run --rm st20285209/coffeemaker \
                    java -jar libs/junit-platform-console-standalone.jar \
                    -cp "out:test-classes" \
                    --scan-classpath
                '''
            }
        }

        stage('Run Application') {
            steps {
                sh 'docker run -d --name coffeemaker st20285209/coffeemaker'
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

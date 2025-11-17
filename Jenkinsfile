 pipeline {
  agent any

  environment {
    DOCKERHUB = credentials('Dhub')         // Exposes DOCKERHUB_USR / DOCKERHUB_PSW
  }

  stages {
    stage('Docker Login') {
      steps {
        sh 'echo "$DOCKERHUB_PSW" | docker login -u "$DOCKERHUB_USR" --password-stdin'
      }
    }

    stage('Pull , build and Run dockerfile ') {
      steps {
       
      sh '''
                docker stop coffeemaker || true
		        docker rm coffeemaker || true
		        docker rmi smmorris20/coffeemaker || true
		        docker build -t smmorris20/coffeemaker  . 
				docker compose up -d
        
        '''
      }
    }

   

    
    stage('Run Tests') {
      steps {
        echo "done testing"
      }
    }

  stage ('cleaning'){
  steps{
	  sh  'docker compose down'
	sh 'docker compose down || true'
	   }
  }
  
}
 }

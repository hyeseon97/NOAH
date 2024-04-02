pipeline {
  agent any
  
  tools {
    nodejs 'NodeJS 20.11.0'
  }

  stages {
    stage('Clone Repository') {
      steps {
        git branch: 'main', credentialsId: 'noah', url: 'https://lab.ssafy.com/s10-fintech-finance-sub2/S10P22B106.git'
      }
    }
    stage('Deploy') {
      steps {
        sshagent(credentials: ['SSAFY_EC2']) {
          sh '''
            scp -r /var/jenkins_home/workspace/NOAH/backend ubuntu@43.203.217.53:/home/ubuntu/back
            scp -r /var/jenkins_home/workspace/NOAH/frontend/noah ubuntu@43.203.217.53:/home/ubuntu/front;
          '''
        }
      }
    }
    // stage('build1') {
    //   steps {
    //     sshagent(credentials: ['SSAFY_EC2']) {
    //       sh '''
    //         ssh -o StrictHostKeyChecking=no ubuntu@43.203.217.53 "
    //             cd /home/ubuntu &&
    //             chmod 755 ./script.sh &&
    //             ./script.sh
    //           "
    //       '''
    //     }
    //   }
    // }
    stage('build1') {
      steps {
        sshagent(credentials: ['SSAFY_EC2']) {
          sh '''
            ssh -o StrictHostKeyChecking=no ubuntu@43.203.217.53 "
                cd /home/ubuntu/back/backend &&
                cp -r ../ymls/* ./src/main/resources &&
                rm -rf ./src/test &&
                rm -f test && 
                touch test &&
                echo $JAVA_HOME >> test &&
                echo $PATH >> test &&
                chmod +x gradlew
              "
          '''
        }
      }
    }
    stage('build2') {
      steps {
        sshagent(credentials: ['SSAFY_EC2']) {
          sh '''
            ssh -o StrictHostKeyChecking=no ubuntu@43.203.217.53 "
                cd /home/ubuntu/back/backend &&
                source /etc/profile &&
                ./gradlew build > java.log 2>&1 &&
                mv ./build/libs/backend-0.0.1-SNAPSHOT.jar ../
              "
          '''
        }
      }
    }
    stage('build3') {
      steps {
        sshagent(credentials: ['SSAFY_EC2']) {
          sh '''
            ssh -o StrictHostKeyChecking=no ubuntu@43.203.217.53 "
                cd /home/ubuntu/front/noah &&
                cp ../.env . &&
                npm i &&
                npm run build &&
                rm -r ../html &&
                mkdir ../html &&
                mv ./build/* ../html
              "
          '''
        }
      }
    }
    stage('build4') {
      steps {
        sshagent(credentials: ['SSAFY_EC2']) {
          sh '''
            ssh -o StrictHostKeyChecking=no ubuntu@43.203.217.53 "
                cd /home/ubuntu &&
                docker-compose down &&
                docker-compose up -d &&
                echo "last fixed: sacrifice for env"
              "
          '''
        }
      }
    }
  }
}

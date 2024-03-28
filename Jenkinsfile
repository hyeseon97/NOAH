pipeline {
    agent any

    stages {
    	stage('Deploy') {
        stage('Clone Repository') {
            steps {
                git branch: 'main', credentialsId: 'noah', url: 'https://lab.ssafy.com/s10-fintech-finance-sub2/S10P22B106.git'
            }
        }
	stage('Deploy') {
                sshagent(credentials: ['SSAFY_EC2'] {
                        sh '''
                                ssh -o StrictHostKeyChecking=no ubuntu@43.203.217.53
                                scp -r /var/jenkins_home/workspace/NOAH:/home/ubuntu/NOAH
                        '''
                }
        }
    }
}

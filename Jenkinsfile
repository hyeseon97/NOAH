pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', credentialsId: 'noah', url: 'https://lab.ssafy.com/s10-fintech-finance-sub2/S10P22B106.git'
            }
        }
    }
}

pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Rakesh-7881/my_project/tree/main'
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'pip3 install -r requirements.txt'
            }
        }

        stage('Deploy WebApp') {
            steps {
                // Kill old process if running
                sh "pkill -f app.py || true"
                // Run Flask app in background
                sh "nohup python3 app.py > app.log 2>&1 &"
            }
        }
    }
}


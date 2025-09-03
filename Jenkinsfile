pipeline {
    agent any

    environment {
        APP_PORT = "9090"
        OUT_DIR  = "out"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Rakesh-7881/my_project.git'
            }
        }

        stage('Compile') {
            steps {
                sh '''
                  mkdir -p ${OUT_DIR}
                  javac -d ${OUT_DIR} HelloWorldServer.java
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                  # Kill old process if running
                  pkill -f "HelloWorldServer" || true

                  # Start new server
                  nohup java -cp ${OUT_DIR} HelloWorldServer ${APP_PORT} > app.log 2>&1 &

                  sleep 3
                  echo "Server running on port ${APP_PORT}"
                '''
            }
        }
    }
}

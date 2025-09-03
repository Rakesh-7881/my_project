pipeline {
    agent any
    environment {
        OUT_DIR = "out"
        APP_PORT = "9090"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Rakesh-7881/my_project.git'
            }
        }
        stage('Compile') {
            steps {
                bat '''
                  if not exist %OUT_DIR% mkdir %OUT_DIR%
                  javac -d %OUT_DIR% HelloWorldServer.java
                '''
            }
        }
        stage('Deploy') {
            steps {
                bat '''
                  for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%APP_PORT%') do taskkill /F /PID %%a

                  start java -cp %OUT_DIR% HelloWorldServer %APP_PORT%
                  timeout /t 3
                  echo Server started on http://localhost:%APP_PORT%
                '''
            }
        }
    }
}

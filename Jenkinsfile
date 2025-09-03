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
                  rem Kill process using port 9090 if running
                  for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%APP_PORT%') do taskkill /F /PID %%a

                  rem Start new server in background
                  start java -cp %OUT_DIR% HelloWorldServer %APP_PORT%

                  rem Small wait using ping (instead of timeout)
                  ping -n 5 127.0.0.1 >nul

                  echo Server started on http://localhost:%APP_PORT%
                '''
            }
        }
    }
}

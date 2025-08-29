pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Rakesh-7881/my_project.git'
            }
        }

        stage('Build') {
            steps {
                sh '''
                  rm -rf out
                  mkdir -p out
                  javac HelloWorldServer.java -d out
                '''
            }
        }

        stage('Deploy (start)') {
            steps {
                sh '''
                  # Kill existing HelloWorldServer if running
                  PID=$(pgrep -f "java .*HelloWorldServer" || true)
                  if [ -n "$PID" ]; then
                    echo "Killing existing HelloWorldServer with PID $PID"
                    kill -9 $PID
                  else
                    echo "No existing HelloWorldServer process found"
                  fi

                  # Start new one in background
                  nohup java -cp out HelloWorldServer 9090 > app.log 2>&1 &
                '''
            }
        }
    }
}

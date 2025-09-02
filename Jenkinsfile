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
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                  pkill -f "HelloWorldServer" || true
                  nohup java -cp target/helloworld-1.0-jar-with-dependencies.jar com.example.HelloWorldServer > app.log 2>&1 &
                  sleep 2
                '''
            }
        }
    }

    post {
        success {
            echo "✅ Deployed! Access at http://172.178.13.187/:9090/"
        }
    }
}

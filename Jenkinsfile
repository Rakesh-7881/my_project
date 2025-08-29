pipeline {
    agent any

    tools {
        // This will use the Maven you configured in Jenkins (Manage Jenkins → Global Tool Configuration)
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Rakesh-7881/my_project.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Java App') {
            steps {
                sh '''
                    javac HelloWorld.java
                    nohup java HelloWorld > app.log 2>&1 &
                '''
            }
        }
    }
}

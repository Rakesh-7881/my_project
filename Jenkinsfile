pipeline {
    agent any

    tools {
        // Use the JDK you configured in Global Tool Config (example: JDK11)
        jdk "JDK11"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/<your-repo>.git'
            }
        }

        stage('Compile') {
            steps {
                sh 'javac HelloWorld.java'
            }
        }

        stage('Run') {
            steps {
                sh 'nohup java HelloWorld > app.log 2>&1 &'
            }
        }
    }
}

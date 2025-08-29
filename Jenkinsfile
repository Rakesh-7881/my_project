pipeline {
  agent {
    docker { image 'openjdk:11' } // uses OpenJDK 11 container on the agent
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Compile') {
      steps {
        sh 'javac HelloWorld.java'
      }
    }
    stage('Run') {
      steps {
        sh 'java HelloWorld'           // prints Hello, World! to console
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: '**/*.class', fingerprint: true
    }
  }
}

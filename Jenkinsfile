pipeline {
  agent any

  environment {
    APP_PORT = "9090"
    APP_FILE = "HelloWorldServer.java"
    OUT_DIR  = "out"
  }

  stages {
    stage('Clean Workspace') {
      steps {
        // Wipe old files from Jenkins workspace
        cleanWs()
      }
    }

    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Stop old process') {
      steps {
        sh '''
          PID=$(pgrep -f "java .*HelloWorldServer" || true)
          if [ -n "$PID" ]; then
            echo "Stopping old process $PID"
            kill $PID || true
            sleep 2
          else
            echo "No existing HelloWorldServer process found"
          fi
        '''
      }
    }

    stage('Build') {
      steps {
        sh '''
          rm -rf ${OUT_DIR}
          mkdir -p ${OUT_DIR}
          javac ${APP_FILE} -d ${OUT_DIR}
        '''
      }
    }

    stage('Deploy (start)') {
      steps {
        sh '''
          nohup sudo java -cp ${OUT_DIR} HelloWorldServer ${APP_PORT} > app.log 2>&1 &
          sleep 2
          echo "Started new process:"
          pgrep -a -f "java .*HelloWorldServer" || true
        '''
      }
    }

    stage('Verify') {
      steps {
        sh '''
          echo "HTTP response from server:"
          curl -sS http://localhost:${APP_PORT}/ || true
        '''
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'app.log', allowEmptyArchive: true
    }
  }
}

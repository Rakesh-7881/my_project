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
        powershell '''
          Set-Location "C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\Test"

          # Kill old process on port 9090
          Get-NetTCPConnection -LocalPort $env:APP_PORT -ErrorAction SilentlyContinue | ForEach-Object {
              Stop-Process -Id $_.OwningProcess -Force
          }

          # Start new process
          Start-Process java -ArgumentList "-cp", "out", "HelloWorldServer", $env:APP_PORT -NoNewWindow
          Start-Sleep -Seconds 5
          Write-Output "Server started on http://localhost:$env:APP_PORT (first run)"

          # Simulate wait before restart (1 minute)
          Write-Output "Waiting 60 seconds before restart..."
          Start-Sleep -Seconds 60

          # Kill again (in case updated file is detected)
          Get-NetTCPConnection -LocalPort $env:APP_PORT -ErrorAction SilentlyContinue | ForEach-Object {
              Stop-Process -Id $_.OwningProcess -Force
          }

          # Start process again
          Start-Process java -ArgumentList "-cp", "out", "HelloWorldServer", $env:APP_PORT -NoNewWindow
          Start-Sleep -Seconds 5
          Write-Output "Server restarted on http://localhost:$env:APP_PORT (second run)"
        '''
        }
      }

    }
}

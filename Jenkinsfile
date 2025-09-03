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
                  # Use Jenkins workspace
                  Set-Location $env:WORKSPACE

                  # Kill old process on port if running
                  $connections = Get-NetTCPConnection -LocalPort $env:APP_PORT -ErrorAction SilentlyContinue
                  if ($connections) {
                      $connections | ForEach-Object {
                          try {
                              Stop-Process -Id $_.OwningProcess -Force -ErrorAction SilentlyContinue
                              Write-Output "Stopped process $($_.OwningProcess) on port $env:APP_PORT"
                          } catch {
                              Write-Output "Failed to stop process $($_.OwningProcess)"
                          }
                      }
                  }

                  # Start new Java server in background
                  Start-Process java -ArgumentList "-cp", "$env:OUT_DIR", "HelloWorldServer", $env:APP_PORT -NoNewWindow
                  Start-Sleep -Seconds 5
                  Write-Output "Server started on http://localhost:$env:APP_PORT"
                '''
            }
        }
    }
}

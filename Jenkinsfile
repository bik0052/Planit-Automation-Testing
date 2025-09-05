pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build & Test') {
      steps {
        sh 'mvn -B clean test -Dheadless=true'
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: 'target/ExtentReport.html', fingerprint: true
      junit 'target/surefire-reports/*.xml'
    }
  }
}

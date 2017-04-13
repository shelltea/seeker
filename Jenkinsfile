pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        withMaven(mavenOpts: 'clean package')
      }
    }
  }
}
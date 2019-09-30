pipeline {
  agent any

  options {
    ansiColor('xterm')
  }

  stages {
    stage ('checkout') {
      steps {
        git 'https://github.com/quarkusio/quarkus-quickstarts.git'
      }
    }
    stage ('build') {
      agent {
        docker {
          image 'maven:3.6.2-jdk-11'
          reuseNode true
          args "-e MAVEN_CONFIG=${WORKSPACE}/.m2 -e MAVEN_OPTS=-Duser.home=${WORKSPACE}"
        }
      }
      steps {
        dir('getting-started-testing') {
           sh "mvn clean package"
        }
      }
    }
    stage('report') {
      steps {
        junit '**/target/surefire-reports/*.xml'
      }
    }
  }
}

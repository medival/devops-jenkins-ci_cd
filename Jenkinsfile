pipeline{
    agent any

    environment {
      APP_NAME = 'testing-ci-cd'

      //docker
      IMAGE_NAME = "haffjjj/devops-jenkins-ci_cd"
      REGISTRY_CREDENTIAL = 'haffjjj-dockerhub'
      DOCKER_IMAGE = ''

      // TELEGRAM_TOKEN = credentials('TELEGRAM_TOKEN')
    }

    stages{
        stage("Init"){
          steps{
            sh 'ls'
          }
        }

        stage("Testing"){
          steps{
            echo 'Test application'
            sh 'npm run test'
          }
        }

        stage("Build Docker Image"){
          steps{
            echo 'Build docker image'
          }
        }

        stage("Deploy"){
          steps{
            echo 'Deploy to server'
          }
        }
    }
}

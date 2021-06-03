pipeline{
    agent any

    environment {
      APP_NAME = 'testing-ci-cd'
      GIT_URL = 'git@github.com:jonsoftware/devops-jenkins-ci_cd.git'

      //docker
      IMAGE_NAME = "haffjjj/devops-jenkins-ci_cd"
      REGISTRY_CREDENTIAL = 'haffjjj-dockerhub'
      DOCKER_IMAGE = ''

      // TELEGRAM_TOKEN = credentials('TELEGRAM_TOKEN')
    }

    stages{
        // stage("Clone repository"){
        //   steps{
        //     echo 'Clone repository'
        //     git credentialsId: 'RPO_SSH', url: GIT_URL, branch: 'master'
        //   }
        // }

        stage("Init"){
          steps{
            sh 'ls'
          }
        }

        stage("Testing"){
          steps{
            echo 'Test application'
            // sh 'npm run test'
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

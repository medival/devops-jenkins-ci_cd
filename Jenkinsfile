pipeline{
    agent any

    environment {
      APP_NAME = 'jenkins-ci'
      IMAGE_USERNAME = 'haffjjj'
      
      APP_NAME = "${APP_NAME}-${BRANCH_NAME}"
      TAG = "${BUILD_NUMBER}"
      DOCKER_IMAGE = ''
      DOCKER_IMAGE_NAME = "${IMAGE_USERNAME}/${APP_NAME}:${TAG}"
      DOKKU_IMAGE_NAME = "dokku/${APP_NAME}:${TAG}"
    }
    
    stage("Build Docker Image"){
      steps{
        script{
          dockerImage = docker.build DOCKER_IMAGE_NAME
        }
      }
    }

    stages{
        stage("Deploy to Server"){
          steps{
            sh "dokku apps:exists ${APP_NAME}"
            sh "docker tag ${DOCKER_IMAGE_NAME} ${DOKKU_IMAGE_NAME}"
            sh "dokku tags:deploy ${APP_NAME} ${TAG}"
          }
        }
    }
}

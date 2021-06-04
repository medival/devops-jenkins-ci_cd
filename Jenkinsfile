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

    stages{
        stage("Validation"){
          echo 'Checking application is created'
          sh "dokku apps:exists ${APP_NAME}"
        }
        
        stage("Build Docker Image"){
          steps{
            echo 'Building the docker image, this may take a few minutes..'
            script{
              dockerImage = docker.build DOCKER_IMAGE_NAME
            }
          }
        }

        stage("Deploy to Server"){
          steps{
            echo "Deploying our app into ${BRANCH_NAME}"
            sh "docker tag ${DOCKER_IMAGE_NAME} ${DOKKU_IMAGE_NAME}"
            sh "dokku tags:deploy ${APP_NAME} ${TAG}"
          }
        }
    }
}

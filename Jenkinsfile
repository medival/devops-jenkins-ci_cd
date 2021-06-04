pipeline{
    agent any

    environment {
      NAME = 'jenkins-ci'
      IMAGE_USERNAME = 'haffjjj'
      DOMAIN = 'jenkins-ci.syafie.me'
      HTTP_PORT = '8080' // exposed from docker
      
      APP_NAME = "${NAME}-${BRANCH_NAME}"
      TAG = "${BUILD_NUMBER}"
      DOCKER_IMAGE = ''
      DOCKER_IMAGE_NAME = "${IMAGE_USERNAME}/${APP_NAME}:${TAG}"
      DOKKU_IMAGE_NAME = "dokku/${APP_NAME}:${TAG}"
    }

    stages{
        stage("Validation"){
          steps{
            echo 'Checking application is created'
            sh "dokku apps:exists ${APP_NAME}"
          }
        }
        
        stage("Build Docker Image"){
          steps{
            echo 'Building the docker image, this may take a few minutes..'
            script{
              dockerImage = docker.build DOCKER_IMAGE_NAME
            }
          }
        }

        stage("Deploy to the Server"){
          steps{
            echo "Deploying our app into ${BRANCH_NAME}"
            sh "docker tag ${DOCKER_IMAGE_NAME} ${DOKKU_IMAGE_NAME}"
            sh "dokku tags:deploy ${APP_NAME} ${TAG}"
            sh "dokku proxy:ports-set ${APP_NAME} http:80:${HTTP_PORT}"
            sh "dokku domains:add ${APP_NAME} ${DOMAIN}"
          }
        }
    }
}

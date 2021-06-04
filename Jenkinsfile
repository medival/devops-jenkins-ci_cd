pipeline{
    agent any

    environment {
      NAME = 'jenkins-ci-new'
      IMAGE_USERNAME = 'haffjjj'
      DOMAIN = ''
      HTTP_PORT = '8080' // exposed from docker
  
      APP_NAME = "${NAME}-${BRANCH_NAME}"
      TAG = "${BUILD_NUMBER}"
      DOCKER_IMAGE_NAME = "${IMAGE_USERNAME}/${APP_NAME}:${TAG}"
      DOKKU_IMAGE_NAME = "dokku/${APP_NAME}:${TAG}"
    }

    stages{
        stage("Validation"){
          steps{
            echo 'Checking application'

            script{
              try{
                sh "dokku apps:exists ${APP_NAME}"
              } catch(err){
                sh "dokku apps:create ${APP_NAME}"
              }
            }
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
           
            script{
              if(env.HTTP_PORT){
                 sh "dokku proxy:ports-set ${APP_NAME} http:80:${HTTP_PORT}"
              }
              if(env.DOMAIN){
                sh "dokku domains:add ${APP_NAME} ${DOMAIN}"
              }
            }
          }
        }
    }
    post{
      failure{
        echo 'failure, send notification'
      }
      success{
        echo 'success, send notification'
      }
    }
}

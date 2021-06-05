pipeline{
    agent any

    environment {
      NAME = 'hello-world-app'
      IMAGE_USERNAME = 'haffjjj'
      DOMAIN = ''
      HTTP_PORT = '8080' // exposed from docker
  
      APP_NAME = "${NAME}-${BRANCH_NAME}"
      TAG = "${BUILD_NUMBER}"
      DOCKER_IMAGE_NAME = "${IMAGE_USERNAME}/${APP_NAME}:${TAG}"
      DOKKU_IMAGE_NAME = "dokku/${APP_NAME}:${TAG}"

      TELE_SECRET = credentials('TELE_SECRET')
      TELE_USER_ID = credentials('TELE_USER_ID')

      SERVER_IP = '194.233.74.212'
      CF_EMAIL = 'hafizjoundys@gmail.com'
      CF_ZONE = '692e5464858990ff83891a631e4e574f'
      CF_AUTH_KEY = credentials('CF_AUTH_KEY')
      CF_MAIN_DOMAIN = 'syafie.me'
    }

    stages{
      stage("Init"){
        steps{
          script{
            gv = load "script.groovy"
            gv.sendTeleMessage("🏗️ Building ${APP_NAME} #${TAG} 🙏 ..")
          }
        }
      }

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

      stage("Registering Domain"){
        steps{
          echo "Registering domain.."
          script{
            gv.registerDomain()
          }
        }
      }
    }
    post{
      failure{
        script{
          gv.sendTeleMessage("🔴 Failed when building ${APP_NAME} #${TAG}")
        }
      }
      success{
        script{
          def message = "🤘 Success build ${APP_NAME} #${TAG}, ${APP_NAME}.${CF_MAIN_DOMAIN}"
          if(env.DOMAIN){
            gv.sendTeleMessage(message + ", ${DOMAIN}")
          } else {
            gv.sendTeleMessage(message)
          }
        }
      }
    }
}

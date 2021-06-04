pipeline{
    agent any

    environment {
      APP_NAME = 'testing-ci-cd'
      //docker
      DOCKER_IMAGE = ''
      DOCKER_IMAGE_NAME = "haffjjj/devops-jenkins-ci_cd"
      DOCKER_REGISTRY_CREDENTIAL = 'DOCKER_CREDENTIAL'

      // TELEGRAM_TOKEN = credentials('TELEGRAM_TOKEN')
    }

    stages{
        stage("Build Docker Image"){
          steps{
            echo 'Build docker image'
            script{
              dockerImage = docker.build DOCKER_IMAGE_NAME
            }
          }
        }

        stage("Deploy Docker Image"){
          steps{
            echo 'Deploy to server ho'
            script {
              docker.withRegistry( '', DOCKER_REGISTRY_CREDENTIAL ) {
                dockerImage.push("$BUILD_NUMBER")
                dockerImage.push('latest')
              }
            }
          }
        }
    }
}

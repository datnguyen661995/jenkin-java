node {
    def WORKSPACE = "/var/jenkins_home/workspace/springboot-demo-deploy"
    def dockerImageTag = "springboot-deploy${env.BUILD_NUMBER}"

    try {
//          notifyBuild('STARTED')
        stage("docker install") {
            steps {
                // Detect the operating system and install Docker accordingly
                sh '''
                #!/bin/bash

                # Detect OS
                OS=$(uname -s)
                if [ "$OS" = "Linux" ]; then
                    DISTRO=$(lsb_release -is)

                    if [ "$DISTRO" = "Ubuntu" ] || [ "$DISTRO" = "Debian" ]; then
                        # Update package index
                        sudo apt-get update

                        # Install required packages
                        sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common

                        # Add Docker’s official GPG key
                        curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

                        # Add Docker repository
                        sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

                        # Update package index again
                        sudo apt-get update

                        # Install Docker
                        sudo apt-get install -y docker-ce

                    elif [ "$DISTRO" = "CentOS" ] || [ "$DISTRO" = "RHEL" ]; then
                        # Remove old versions
                        sudo yum remove -y docker docker-common docker-selinux docker-engine

                        # Install required packages
                        sudo yum install -y yum-utils device-mapper-persistent-data lvm2

                        # Add Docker repository
                        sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

                        # Install Docker
                        sudo yum install -y docker-ce

                        # Start Docker
                        sudo systemctl start docker
                    else
                        echo "Unsupported Linux distribution"
                        exit 1
                    fi

                    # Add Jenkins user to the Docker group
                    sudo usermod -aG docker jenkins

                    # Restart Jenkins to apply group change
                    sudo systemctl restart jenkins
                else
                    echo "Unsupported OS: $OS"
                    exit 1
                fi
                '''
             }
        }
        stage('verify docker installation') {
            steps {
                sh 'docker --version'
            }
        }
        stage("Clone Repo") {
            // for display purpose
            // get some code from a github repository
            git url: 'https://github.com/datnguyen661995/jenkin-java.git',
                credentialsId: 'springdeploy-user'
                branch: 'main'
        }
        stage("Build Docker") {
            dockerImage = docker.build("springboot-demo-deploy:${env.BUILD_NUMBER}")
        }

        stage("Deploy docker") {
            echo "Docker Image Tag Name: ${dockerImageTag}"
            sh "docker stop springboot-deploy || true && docker rm springboot-deploy || true"
            sh "docker run --name springboot-deploy -d -p 8082 springboot-deploy:${env.BUILD_NUMBER}"
        }
    } catch(e) {
        // currentBuild.result = "FAILED"
        throw e
    } finally {
//         notifyBuild(currentBuild.result)
    }
}

def notifyBuild(String buildStatus = 'STARTED'){

// build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'
  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def now = new Date()
  // message
  def subject = "${buildStatus}, Job: ${env.JOB_NAME} FRONTEND - Deployment Sequence: [${env.BUILD_NUMBER}] "
  def summary = "${subject} - Check On: (${env.BUILD_URL}) - Time: ${now}"
  def subject_email = "Spring boot Deployment"
  def details = """<p>${buildStatus} JOB </p>
    <p>Job: ${env.JOB_NAME} - Deployment Sequence: [${env.BUILD_NUMBER}] - Time: ${now}</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME}</a>"</p>"""


  // Email notification
    emailext (
         to: "admin@gmail.com",
         subject: subject_email,
         body: details,
         recipientProviders: [[$class: 'DevelopersRecipientProvider']]
       )
}

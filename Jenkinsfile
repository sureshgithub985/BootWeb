//declartive pipelline

pipeline{
    agent any
    //agent { dockerfile true }
    //agent {
      //  docker { image 'node:14.15.5-buster' }
    //}
    environment {
        //dockerHome = tool 'myDocker'
        mavenHome = "D:/Softwares/apache-maven-3.6.3-bin/apache-maven-3.6.3/bin"
        PATH = "$mavenHome/bin:$PATH"
    }
    stages {
        stage('Checkout'){
            steps {
                //sh 'mvn --version'
                echo "After maven version"
                //sudo usermod -a -G docker jenkins
                //sh  'docker version'
                echo "Build"
				echo "maven home is $mavenHome"
                sh 'printenv'
            }
        }
        stage('Compile'){
            steps {
                echo "compile"
                sh "mvn clean compile"
            }
        }
        stage('Test'){
            steps {
                echo "Test"
                sh "mvn test"
            }
        }
       stage('Integration Test'){
            steps {
                echo "Integration Test"
                //sh "mvn failsafe:integration-test failsafe:verfiy"
            }
        }
        stage('Package'){
            steps {
                echo "Package"
                sh "mvn package -DskipTests"
            }
        }
          stage('Build Docker Image'){
            steps {
                echo "Build Docker Image"
                //"docker build -t suresh931/spring-boot-devops:$env.BUILD_TAG"
                script {
                    dockerImage = docker.build("suresh931/spring-boot-devops:${env.BUILD_TAG}")
                }
            }
        }
          stage('Push Docker Image'){
            steps {
                echo "Push Docker Image"
                script {
                    docker.withRegistry('' , dockerHub){
                        dockerImage.Push();
                        dockerImage.Push('latest');
                    }
                }
            }
        }
    }
    post {
        always {
            echo "Iam awesome i run always"
        }
        success {
            echo "i run only in case of success"
        }
        failure {
            echo " I run only in case of failure"
        }
    }
}
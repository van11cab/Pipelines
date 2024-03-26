pipeline{
    agent any
    environment{
        AWS_SECRET_KEY = credentials('AWS_SECRET_ACCESS_KEY_TEXT')
        AWS_ACCESS_KEY = credentials('AWS_ACCESS_KEY_TEXT')
        AWS_DEFAULT_REGION = 'us-west-2'
    }
    stages{
        stage('Terraform init') {
            steps {
                dir('/var/lib/jenkins/workspace/Terraform/Terraform - Pipeline'){
                    sh 'terraform init'
                }
            }
        }
        stage('Terraform apply'){
            steps{
                dir('/var/lib/jenkins/workspace/Terraform/Terraform - Pipeline'){
                    sh 'terraform apply -auto-approve'
                }
            }
        }
    }
}
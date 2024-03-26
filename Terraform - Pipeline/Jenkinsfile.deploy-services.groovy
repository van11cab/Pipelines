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
                sh 'terraform init'
            }
        }
        stage('Terraform apply'){
            steps{
                sh 'terraform apply'
            }
        }
    }
}
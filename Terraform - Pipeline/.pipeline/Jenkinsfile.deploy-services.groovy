def secrets = [
    [path: 'kv/credentials/jenkins', engineVersion: 2, secretValues: [
    [envVar: 'ACCESS', vaultKey: 'AWS_ACCESS_KEY'],
    [envVar: 'SECRET', vaultKey: 'AWS_SECRET_KEY'],
    [envVar: 'REGION', vaultKey: 'region']]],
]
def configuration = [vaultUrl: 'http://35.86.125.58:8200',  vaultCredentialId: 'Vault-JenkinsApprole', engineVersion: 2]
pipeline{
    agent any
    stages{
        stage('Vault Access'){
            steps{
               dir('/var/lib/jenkins/workspace/terraform pipeline/Terraform - Pipeline/deployment'){
                    script{
                        def vaultResponse
                        withVault([configuration: configuration, vaultSecrets: secrets]){
                            vaultResponse = [
                                access_pass: env.ACCESS,
                                secret_pass: env.SECRET,
                                region: env.REGION
                            ]
                        }
                        
                        env.AWS_ACCESS_KEY = vaultResponse['access_pass']
                        env.AWS_SECRET_ACCESS_KEY = vaultResponse['secret_pass']
                    }
               }
            }
        }
        stage('Terraform init') {
            steps {
                dir('/var/lib/jenkins/workspace/terraform pipeline/Terraform - Pipeline/deployment'){
                    sh 'terraform init'
                }
            }
        }
        stage('Terraform apply'){
            steps{
                dir('/var/lib/jenkins/workspace/terraform pipeline/Terraform - Pipeline/deployment'){
                    sh 'terraform apply -auto-approve'
                }
            }
        }
    }
}

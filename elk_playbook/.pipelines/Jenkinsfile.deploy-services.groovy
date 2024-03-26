pipeline{
    agent any
    stages{
        stage("Run Kibana"){
            steps{
                ansiblePlaybook become: true,
                credentialsId: 'Ec2Connection',
                disableHostKeyChecking: true,
                inventory: 'elk_playbook/deployments/myinventory.ini',
                playbook: 'elk_playbook/deployments/deploy_kibana.yml',
                vaultTmpPath: ''
            }
        }
        stage("Run Vault"){
            steps{
                ansiblePlaybook become: true,
                credentialsId: 'Ec2Connection',
                disableHostKeyChecking: true,
                inventory: 'elk_playbook/deployments/myinventory.ini',
                playbook: 'elk_playbook/deployments/deploy_vault.yml',
                vaultTmpPath: ''
            }
        }
        stage("Run Elastic"){
            steps{
                ansiblePlaybook become: true,
                credentialsId: 'Ec2Connection',
                disableHostKeyChecking: true,
                inventory: 'elk_playbook/deployments/myinventory.ini',
                playbook: 'elk_playbook/deployments/deploy_elastic.yml',
                vaultTmpPath: ''
    		}
        }
    }
}
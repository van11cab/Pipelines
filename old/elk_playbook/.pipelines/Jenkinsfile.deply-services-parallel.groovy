pipeline{
    agent { label 'common'}
    stages{
        stage('Run Ansible'){
            parallel{
                 stage("Run Kibana"){
                    steps{
                        ansiblePlaybook extras: '-vvvv', become: true,
                        credentialsId: 'b5b71884-1907-4a3f-b93a-3710ad7926ff',
                        disableHostKeyChecking: true,
                        inventory: '/home/ubuntu/workspace/elk_playbook/elk_playbook/deployments/myinventory.ini',
                        playbook: '/home/ubuntu/workspace/elk_playbook/elk_playbook/deployments/deploy_kibana.yml',
                        vaultTmpPath: ''
                    }
                }
                stage("Run Elastic"){
                    steps{
                        ansiblePlaybook extras: '-vvvv', become: true,
                        credentialsId: 'b5b71884-1907-4a3f-b93a-3710ad7926ff',
                        disableHostKeyChecking: true,
                        inventory: '/home/ubuntu/workspace/elk_playbook/elk_playbook/deployments/myinventory.ini',
                        playbook: '/home/ubuntu/workspace/elk_playbook/elk_playbook/deployments/deploy_elastic.yml',
                        vaultTmpPath: ''
                	}
                }
            }
        }
    }
}
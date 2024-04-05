pipeline{
    agent any
    stages{
        stage("Parallel_Deployment"){
            when{ tag pattern: "parallel", comparator: "REGEXP"}
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
        stage{"Serial_Deployment"}{
            steps{
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
    }
}
#!groovy
pipeline {
    agent {label 'master'}
environment 
{
        
imagename = 'adilashraf_10615395_img'
        
containername = 'adilashraf_10615395_con'
        
storagepath = "example-repo-local/adilashraf_10615395"      
buildnumber  = "${currentBuild.number}"
    
}
    stages {
        stage('build') {

            agent { 
            
	    docker {
               
            image 'maven:3.5-jdk-8-alpine'
               
	    label 'master'
               
	    args  '-v /root/.m2:/root/.m2:rw'
            
} 
            
} 

            steps {
                
                sh 'ls -lrth' 
                sh 'cd angularjavaapp; mvn clean install'
            
}

        }

		stage ('Upload Artifacts') { 
                            agent {label 'master'}
                    steps {
                             artifactory_upload(storagepath, buildnumber)
                }
    }

stage('Download Artifacts'){
    
                      
agent {label 'master'}
               
steps {
                         
	artifactory_download(storagepath, buildnumber)
    
}
     
 }
       
   stage('deployment'){
                   
	agent {label 'master'}
                
	steps {
                         
	      sh 'ansible-playbook /home/skill_user/playbooks/app-deployment-test.yml --extra-vars "target=Adil_vm app_name=${Application} env_name=${Environment}"'
    
 }   
       
  }

				

    }
}

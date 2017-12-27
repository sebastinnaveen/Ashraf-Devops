#!groovy
pipeline {
    agent {label 'jenkinsslave111'}
environment 
{        
imagename = 'adilashraf_10615395_img'        
containername = 'adilashraf_10615395_con'        
storagepath = "example-repo-local/adilashraf_10615395"      
buildnumber  = "${currentBuild.number}"    
}
    stages {        stage('build') {
            agent {             
	    docker {               
            image 'maven:3.5-jdk-8-alpine'               
	    label 'jenkinsslave111'               
	    args  '-v /root/.m2:/root/.m2:rw'            
}             
} 
            steps {
                echo 'Hello, Maven'
                sh 'mvn clean install'
            }
			post { 
                always { 
                        junit 'target/surefire-reports/*.xml'
                }
            }

        }
        stage('sonat test') {
            agent { docker {               
	image 'sonar-scanner2.8:latest'               
	label 'jenkinsslave111'               
	args  '-v /root/.sonar/cache:/root/.sonar/cache:rw'            
	} } 
            steps {
                echo 'Sonar qube'
                sh "sonar-scanner"
            }
        }
		stage('System Testing') {
                        agent {label 'jenkinsslave111'}
                                    steps {
                                         system_testing(imagename, containername)
                                    }
                        }
		stage ('Upload Artifacts') { 
                            agent {label 'jenkinsslave111'}
                    steps {
                             artifactory_upload(storagepath, buildnumber)
                }
    }
				

    }
}

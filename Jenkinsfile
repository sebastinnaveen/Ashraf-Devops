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
                sh 'cp angularjavaapp/target/AngularJavaApp.war /opt/'
            
}

        }

       
   stage('deployment'){
                   
	agent {
           docker{
              image 'tomcat:8.0.20-jre8'
              label 'master'
              args '-v /usr/local/tomcat/webapps:/usr/local/tomcat/webapps:rw -p 8080:8080'    
}
}

steps{

 sh 'cp /opt/AngularJavaApp.war /usr/local/tomcat/webapps/'


}

                
    

				

    }
}

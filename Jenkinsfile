#!groovy
pipeline {
    agent {label 'master'}
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

stage('copy artifacts'){

agent{ label 'master' }

steps{
sh 'cp /var/lib/jenkins/workspace/jenkins.test3@2/angularjavaapp/target/AngularJavaApp.war /usr/local/tomcat/webapps/AngularJavaApp.war'
}

}



       
   stage('deployment'){

agent{ label 'master' }

steps{
sh 'docker run -it -v /usr/local/tomcat/webapps/:/usr/local/tomcat/webapps/:rw -p 8088:8080 -d tomcat:7-jre7'
}

                   
}

}
}

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
               
            image 'maven'
               
	    label 'master'
               
	    args  '-v /root/.m2:/root/.m2:rw'
            
} 
            
} 

            steps {
                
                sh 'ls -lrth' 
                sh 'cd angularjavaapp; mvn clean -e install'
            
}

        }

stage('copy artifacts'){

agent{ label 'master' }

steps{
sh 'cp /var/lib/jenkins/workspace/jenkins.test@2/angularjavaapp/target/AngularJavaApp.war /opt/AngularJavaApp.war'
}

}

stage('build a docker image'){

agent{ label 'master' }

steps{
sh 'cd /opt ; docker build -t malu/tomcat . '
}

}




       
   stage('remove docker'){

agent{ label 'master' }

steps{
sh 'docker rm -f tomcatdev'
}

                   
}

stage('deployment'){

agent{ label 'master' }

steps{
sh 'docker run -it  -p 8088:8080 -d --name=tomcatdev malu/tomcat'
}


}

stage('rbt post'){

agent{ label 'master' }

steps{
sh 'cd /apps/opt/Ashraf-Devops; rbt post --tracking=master'
}


}

}
}

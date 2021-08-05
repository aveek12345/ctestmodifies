pipeline {
   agent any
   stages {
    stage('Extract from Github') {
      steps {
        script {
           git branch: 'main', url: 'https://github.com/aveek12345/ctestmodifies.git'
          }
       }
    }
    
    stage('Build Maven Job') {
      steps {
        script {
          bat 'mvn clean install -DskipTests=true'
          }
       }
    }
    
      stage('Artifactory'){
           
       steps{
        rtUpload (
    serverId: 'check',
    
     spec: '''{
          "files": [
            {
              "pattern": "./target/*CucumberTest2-0.0.1-SNAPSHOT*.jar",
              "target": "pipeline2/"
            }
         ]
    } ''')
       }
       
       }
      
      stage ('Publish build info') {
            steps {
                rtPublishBuildInfo (
                    
                    serverId: 'check'
                )

               
            }
        }
       
        stage('Test') {
      steps {
        script {
          bat 'mvn test'
          }
          //cucumber failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/*.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
          //cucumber '**/*.json'
       }
    }
    
    stage('SonarQube') {
  environment {
    SCANNER_HOME = tool 'Sonar'
    
  }
  //In Dsonar.login mention the appropriate login code generated in SonarQube related to the projectkey
       steps {
    withSonarQubeEnv('Default') {
        bat '''mvn sonar:sonar -Dsonar.projectKey=Sonar3 
  -Dsonar.host.url=http://localhost:9000 
  -Dsonar.login=
  -Dsonar.java.binaries=target/test-classes/cucumberJava2/
  
     '''
    }
  }
  }
  
   }
  
  post {
        always {
            cucumber '**/cucumber.json'
        }
    }

    
}



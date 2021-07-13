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
              "target": "pipeline/"
            }
         ]
    } ''')
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
  steps {
    withSonarQubeEnv('Default') {
        bat '''mvn sonar:sonar -Dsonar.projectKey=Sonar3 
  -Dsonar.host.url=http://localhost:9000 
  -Dsonar.login=c6f8829fc05077e44427d952354e17f32fd5b1be 
  -Dsonar.java.binaries=target/test-classes/cucumberJava2/
  
     '''
    }
  }
  }
  
    
    //stage('Cucumber Report'){
        
    
    //steps{
    //cucumber failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/*.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
    //}
        
    //} 
    
    
    
    
    
    
       
    
    
  }
  
  post {
        always {
            cucumber '**/cucumber.json'
        }
    }

    
}



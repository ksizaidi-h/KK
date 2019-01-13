pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh '/usr/local/Cellar/gradle/4.10.2/libexec/bin/gradle build'
        sh '/usr/local/Cellar/gradle/4.10.2/libexec/bin/gradle javadoc'
        archiveArtifacts 'build/libs/*.jar'
        archiveArtifacts 'build/docs/javadoc/*'
      }
    }
      stage('Mail notification') {
        steps {
          mail(subject: 'notification ', body: 'new deployment', to: 'fm_ameddah@esi.dz')
        }
      }
    
     stage('Code analysis') {
      parallel {
        
         stage('SonarQube') {
                    steps {
                       
                        withSonarQubeEnv('sonarqube') {
                          sh '/Users/mac/Downloads/sonar-scanner-3.2.0.1227-macosx/bin/sonar-scanner'
                        }
                          waitForQualityGate abortPipeline: false
                    }
                  }
          
         stage('Test Reporting') {
              steps {
                jacoco(maximumBranchCoverage: '60')
              }
            }
        }
 }
     
  
  }
}

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
       post {
              failure {
                mail(subject: 'Repported changes', body: 'Salam, some changes occured and the build failed', from: 'fa_chenine@esi.dz', to: 'chenineazeddine5@gmail.com')
              }

              success {
                mail(subject: 'Repported changes', body: 'Salam, some changes occured and the build successeded', from: 'fa_chenine@esi.dz', to: 'chenineazeddine5@gmail.com')
              }

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
    
    stage('slack notification') {
      when {
        branch 'master'
      }
      steps {
        slackSend(message: 'Salam, Project deployed')
      }
    }
     
  
  }
}

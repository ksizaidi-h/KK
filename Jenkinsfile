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
                mail(subject: '[Report]', body: 'Greetings,<br> The build failed', from: 'fm_ameddah@esi.dz', to: 'kowdou@gmail.com')

              }

              success {
                mail(subject: '[Report]', body: 'Greetings,<br> The build successeded', from: 'fm_ameddah@esi.dz', to: 'kowdou@gmail.com')

              }
         }
      
    }
  }
}

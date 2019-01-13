pipeline {
  agent any
  stages {
    stage('Build') {
      post {
        failure {
          mail(subject: '[Report]', body: 'Greetings,<br> The build failed', from: 'fm_ameddah@esi.dz', to: 'kowdou@gmail.com')

        }

        success {
          mail(subject: '[Report]', body: 'Greetings,<br> The build successeded', from: 'fm_ameddah@esi.dz', to: 'kowdou@gmail.com')

        }

      }
      steps {
        sh '/usr/local/Cellar/gradle/4.10.2/libexec/bin/gradle build'
        sh '/usr/local/Cellar/gradle/4.10.2/libexec/bin/gradle javadoc'
        archiveArtifacts 'build/libs/*.jar'
        archiveArtifacts 'build/docs/javadoc/*'
      }
    }
    stage('Code analysis') {
      parallel {
        stage('SonarQube') {
          steps {
            withSonarQubeEnv('sonarqube') {
              sh '/Users/mac/Downloads/sonar-scanner-3.2.0.1227-macosx/bin/sonar-scanner'
            }

          }
        }
        stage('Test Reporting') {
          steps {
            jacoco(maximumBranchCoverage: '60')
          }
        }
      }
    }
    stage('Deployment') {
      when {
        branch 'master'
      }
      steps {
        sh '/usr/local/Cellar/gradle/4.10.2/libexec/bin/gradle uploadArchives'
      }
    }
    stage('slack notification') {
      when {
        branch 'master'
      }
      steps {
        slackSend(message: 'Salam, Project deployed', token: 'xoxp-522407053698-522239839092-522407735906-d7d4bdedfe3467f29fc60fcb107b48c9', baseUrl: 'https://hooks.slack.com/services/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', channel: '#general', tokenCredentialId: 'xoxp-522407053698-522239839092-522407735906-d7d4bdedfe3467f29fc60fcb107b48c9')
      }
    }
  }
}
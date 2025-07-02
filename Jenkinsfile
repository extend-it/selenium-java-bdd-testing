// Assumes an agent with JDK 11+, Maven and Google Chrome on the PATH;
// WebDriverManager downloads the matching chromedriver at runtime.
pipeline {
    agent any

    options {
        timestamps()
        timeout(time: 20, unit: 'MINUTES')
    }

    stages {
        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B test -Dheadless=true'
                    } else {
                        bat 'mvn -B test -Dheadless=true'
                    }
                }
            }
        }
    }

    post {
        always {
            // Per-scenario results from Cucumber's own junit plugin (see TestRunner).
            junit testResults: 'target/cucumber-reports/cucumber.xml', allowEmptyResults: true
            // Cucumber's report is the one with real per-scenario detail; the
            // Surefire one always says "Tests run: 0" (see TestRunner javadoc).
            archiveArtifacts artifacts: 'target/cucumber-reports/**, target/surefire-reports/**',
                             allowEmptyArchive: true
        }
    }
}

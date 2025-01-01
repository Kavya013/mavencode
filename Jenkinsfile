pipeline {
    agent any

    tools {
        maven 'sonarmaven'
        jdk 'JDK 17'  // Ensure that JDK 17 is configured in Jenkins tools
    }

    environment {
        MAVEN_PATH = 'C:\\Users\\prabh\\Downloads\\apache-maven-3.9.9\\bin'  // Adjust the Maven path for Windows
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'  // Set the correct JAVA_HOME path for Windows
        SONAR_TOKEN = credentials('sonar-token')  // Use Jenkins credentials for the token
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean target folder') {
            steps {
                echo 'Cleaning target directory...'
                bat '''
                "%MAVEN_PATH%\\mvn" clean
                '''
            }
        }

        stage('Test') {
            steps {
                echo 'Testing the project...'
                bat '''
                "%MAVEN_PATH%\\mvn" test
                '''
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the compiled code...'
                bat '''
                "%MAVEN_PATH%\\mvn" package
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                bat '''
                "%MAVEN_PATH%\\mvn" sonar:sonar ^
                  -Dsonar.projectKey=mavencode1 ^
                  -Dsonar.projectName="mavencode1" ^
                  -Dsonar.host.url=http://localhost:9000 ^
                  -Dsonar.token=%SONAR_TOKEN%
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
    }
}

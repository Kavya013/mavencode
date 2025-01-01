pipeline {
    agent any
    tools {
        maven 'sonarmaven'
        jdk 'JAVA_HOME'  
    }
    environment {
        MAVEN_PATH = 'C:\\Users\\prabh\\Downloads\\apache-maven-3.9.9\\bin' 
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17' 
        SONAR_TOKEN = credentials('sonar-token') 
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

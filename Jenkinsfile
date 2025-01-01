pipeline {
    agent any

    tools {
        maven 'sonarmaven'
    }

    environment {
        MAVEN_PATH = 'C:\\Users\\prabh\\Downloads\\apache-maven-3.9.9\\bin'
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
                set PATH=%MAVEN_PATH%;%PATH%
                mvn clean
                '''
            }
        }

        stage('Test') {
            steps {
                echo 'Testing the project...'
                bat '''
                set PATH=%MAVEN_PATH%;%PATH%
                mvn test
                '''
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the compiled code...'
                bat '''
                set PATH=%MAVEN_PATH%;%PATH%
                mvn package
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                bat '''
                set PATH=%MAVEN_PATH%;%PATH%
                mvn sonar:sonar ^
                  -Dsonar.projectKey=mavencode ^
                  -Dsonar.sources=src/test/java ^
                  -Dsonar.host.url=http://localhost:9000 ^
                  -Dsonar.login=%SONAR_TOKEN% 
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
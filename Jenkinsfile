pipeline {
    agent any
    tools {
        maven 'sonarmaven'
    }
    environment {
        SONAR_TOKEN = credentials('sonar-token')
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
        SONAR_SCANNER_PATH = 'C:/Users/your_username/Downloads/sonar-scanner-cli-6.2.1.4610-windows-x64/sonar-scanner-6.2.1.4610-windows-x64/bin'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    bat """
                    set PATH=%SONAR_SCANNER_PATH%;%PATH%
                    where sonar-scanner || echo "SonarQube scanner not found. Please install it."
                        mvn sonar:sonar ^
                        -Dsonar.projectKey=mavencode ^
                        -Dsonar.sources=src/main/java,src/test/java ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.token=%SONAR_TOKEN%
                    """
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}

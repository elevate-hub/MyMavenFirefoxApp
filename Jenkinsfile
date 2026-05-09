pipeline {
    agent any  

    tools {
        maven 'Maven'
    }

    environment {
        JAR_FILE = "target/MyMavenFirefoxApp-1.0-SNAPSHOT.jar"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/elevate-hub/MyMavenFirefoxApp.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Verify JAR') {
            steps {
                sh '''
                echo "Current directory:"
                pwd

                echo "Files in target folder:"
                ls -l target/
                '''
            }
        }

        stage('Run Application') {
            steps {
                sh '''
                echo "Starting application..."
                nohup java -jar target/MyMavenFirefoxApp-1.0-SNAPSHOT.jar > app.log 2>&1 &
                '''
            }
        }
    }

    post {
        success {
            echo 'Build and deployment successful! 🎉'
        }
        failure {
            echo 'Build failed! ❌'
        }
    }
}

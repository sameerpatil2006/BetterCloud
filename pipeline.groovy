def targetNode = env.TARGET_NODE ?: 'master'
boolean buildChrome = env.CHROME_BROWSER ?: true
boolean buildFirefox = env.FIREFOX_BROWSER ?: false
def gitURL = scm.getUserRemoteConfigs()[0].getUrl()
String branch = env.BRANCH ?: 'master'
def localProjectName = gitURL.substring(gitURL.lastIndexOf('/') + 1, gitURL.lastIndexOf('.'))

node(targetNode){
    stage('Prep'){
        sh "sudo rm -rf ${localProjectName} || true"
        checkout([$class: 'GitSCM', branches: [[name: "*/${branch}"]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CheckoutOption', timeout: 10], [$class: 'CleanBeforeCheckout', deleteUntrackedNestedRepositories: true], [$class: 'RelativeTargetDirectory', relativeTargetDir: localProjectName]], gitTool: 'Default', submoduleCfg: [], userRemoteConfigs: [[url: gitURL]]])
    }

    stage('Build'){
        dir(localProjectName){
            sh "docker build -t sameer:chrome ."
            if(buildChrome) {
                def id = sh(label: 'start container', returnStdout: true, script: "docker run -d --rm -v ${WORKSPACE}/${localProjectName}:/var/lib/${localProjectName}:rw -v /dev/shm:/dev/shm sameer:chrome").trim()
                try {
                    sh label: 'build command', script: """docker exec ${id} /bin/bash -c 'sudo chmod 777 -R /var/lib/${localProjectName} && cd /var/lib/${localProjectName} && ./gradlew clean build' """
                } finally {
                    publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'BetterCloud Report', reportTitles: ''])
                    sh "docker stop -t 5 ${id}"
                }
            }
        }
    }
}

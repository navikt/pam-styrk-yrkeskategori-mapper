// pam-styrk-yrkeskategori-mapper build and test

githubAppId = '23179'
githubAppCredentialId = 'teampam-ci'
def newAppToken() {
    withEnv(['HTTPS_PROXY=webproxy-internett.nav.no:8088']) {
        withCredentials([file(credentialsId: githubAppCredentialId, variable: 'KEYFILE')]) {
            dir('token') {
                def generatedToken = sh(script: "generate-jwt.sh \$KEYFILE ${githubAppId} | xargs generate-installation-token.sh", returnStdout: true)
                return generatedToken.trim()
            }
        }
    }
}

node {
    def application = "pam-styrk-yrkeskategori-mapper"
    def repo = "navikt"
    def githubAppToken = newAppToken()

    def committer, committerEmail, changelog, pom, releaseVersion, isSnapshot, nextVersion // metadata

    def mvnHome = tool "maven-3.3.9"
    def mvn = "${mvnHome}/bin/mvn"

    try {

        stage("Checkout") {
            cleanWs()
            withEnv(['HTTPS_PROXY=http://webproxy-internett.nav.no:8088']) {
               println("Repository URL is https://x-access-token:****@github.com/${repo}/${application}.git")
               sh(script: "set +x; git clone https://x-access-token:${githubAppToken}@github.com/${repo}/${application}.git .")
            }
        }

        stage("initialize") {
            pom = readMavenPom file: 'pom.xml'
            releaseVersion = pom.version.tokenize("-")[0]
            isSnapshot = pom.version.contains("-SNAPSHOT")
            committer = sh(script: 'git log -1 --pretty=format:"%an (%ae)"', returnStdout: true).trim()
            committerEmail = sh(script: 'git log -1 --pretty=format:"%ae"', returnStdout: true).trim()
            changelog = sh(script: 'git log `git describe --tags --abbrev=0`..HEAD --oneline', returnStdout: true)
        }

        stage("Verify maven versions") {
            sh 'echo "Verifying that no snapshot dependencies is being used."'
            sh 'grep module pom.xml | cut -d">" -f2 | cut -d"<" -f1 > snapshots.txt'
            sh 'echo "./" >> snapshots.txt'
            sh 'while read line;do if [ "$line" != "" ];then if [ `grep SNAPSHOT $line/pom.xml | wc -l` -gt 1 ];then echo "SNAPSHOT-dependencies found. See file $line/pom.xml.";exit 1;fi;fi;done < snapshots.txt'
        }


        stage("Build and test backend") {
            if (isSnapshot) {
                sh "${mvn} clean install -Dit.skip=true -Djava.io.tmpdir=/tmp/${application} -B -e"
            } else {
                println("POM version is not a SNAPSHOT, it is ${pom.version}. Skipping build and testing of backend")
            }

        }

        color = '#BDFFC3'
        GString message = ":heart_eyes_cat: Siste commit på ${application} bygd og deploya OK.\nSiste commit ${changelog}"
        slackSend color: color, channel: '#pam_bygg', message: message, teamDomain: 'nav-it', tokenCredentialId: 'pam-slack'


    } catch (e) {
        color = '#FF0004'
        GString message = ":crying_cat_face: :crying_cat_face: :crying_cat_face: :crying_cat_face: :crying_cat_face: :crying_cat_face: Halp sad cat! \n Siste commit på ${application} gikk ikkje gjennom. Sjå logg for meir info ${env.BUILD_URL}\nLast commit ${changelog}"
        slackSend color: color, channel: '#pam_bygg', message: message, teamDomain: 'nav-it', tokenCredentialId: 'pam-slack'
    }

}

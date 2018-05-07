@Library('deploy')
import deploy

def deployLib = new deploy()

node {
    def application = "pam-styrk-yrkeskategori-mapper"

    def committer, committerEmail, changelog, pom, releaseVersion, isSnapshot, nextVersion // metadata

    def mvnHome = tool "maven-3.3.9"
    def mvn = "${mvnHome}/bin/mvn"

    try {

        stage("checkout") {
                    cleanWs()
                    withCredentials([string(credentialsId: 'navikt-ci-oauthtoken', variable: 'token')]) {
                     withEnv(['HTTPS_PROXY=http://webproxy-utvikler.nav.no:8088']) {
                            git url: "https://${token}:x-oauth-basic@github.com/navikt/${application}.git"
                        }
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

        stage("verify maven versions") {
            sh 'echo "Verifying that no snapshot dependencies is being used."'
            sh 'grep module pom.xml | cut -d">" -f2 | cut -d"<" -f1 > snapshots.txt'
            sh 'echo "./" >> snapshots.txt'
            sh 'while read line;do if [ "$line" != "" ];then if [ `grep SNAPSHOT $line/pom.xml | wc -l` -gt 1 ];then echo "SNAPSHOT-dependencies found. See file $line/pom.xml.";exit 1;fi;fi;done < snapshots.txt'
        }


        stage("build and test backend") {
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

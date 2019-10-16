pipelineJob('java-meetup-2-docker-image-build') {
  concurrentBuild(false)
  definition {
    cpsScm {
      scm {
        git {
          remote {
            credentials('ssh-github-java-meetup-key')
            github('isaaceindhoven/java-meetup-quarkus')
            url('git@github.com:isaaceindhoven/java-meetup-quarkus.git')
          }
          branch('*/master')
          extensions {
            ignoreNotifyCommit()
          }
        }
      }
      scriptPath('Jenkinsfile-2-docker-image-build')
    }
  }
}

pipelineJob('java-meetup-1-build') {
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
      scriptPath('Jenkinsfile-1-build')
    }
  }
}

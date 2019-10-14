multibranchPipelineJob('java-meetup-quarkus') {
  branchSources {
    github {
      id('c7ce2794-b14e-469f-a113-87ffb0590b69')
      scanCredentialsId('github')
      checkoutCredentialsId('ssh-github-java-meetup-key')
      repoOwner('jwnmulder')
      repository('java-meetup-quarkus')
    }
  }
  orphanedItemStrategy {
    discardOldItems {
      daysToKeep(-1)
      numToKeep(-1)
    }
  }
}
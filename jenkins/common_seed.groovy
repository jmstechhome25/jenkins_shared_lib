def baseUrl = "https://github.com/jmstechhome25/"
def repoName = "$reponame"
def gitRepoUrl= baseUrl + repoName + '.git'
def jobName = "$reponame"
  pipelineJob(jobName) {
        properties {
         pipelineTriggers {
          triggers {
            pollSCM {
              scmpoll_spec('*/1 * * * *')
              ignorePostCommitHooks(true)
            }
          }
         }
        }
        logRotator{
         numToKeep(5)
        }
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(gitRepoUrl)
                            credentials('github_new_credentials')
                        }
                        branches('main')
                        extensions {
                            cleanBeforeCheckout()
                        }
                    }
                }
                scriptPath("Jenkinsfile")
            }
        }
    }

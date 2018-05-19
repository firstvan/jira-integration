import geb.Page

class JiraIntegrationHomePage extends Page {

    static at = { title == "Jira integration" }

    static content = {
        loginModule { module(LoginModule) }
    }
}
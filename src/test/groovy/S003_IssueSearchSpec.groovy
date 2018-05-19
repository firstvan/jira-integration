import geb.spock.GebSpec

class S003_IssueSearchSpec extends GebSpec {

    def setupSpec() {
        Util.setProperties()
        to JiraIntegrationHomePage
        loginModule.fill()
        loginModule.login()
        at IndexPage
        searchInList.startSearch()
        searchInList.toIssue()
    }

    def "search FT-1 issue"() {
        when:
        at IssuePage

        and:
        worklogSearch.changeDate()

        then:
        assert true == worklogSearch.checkResult()

        when:
        worklogSearch.toModifyWorklog()

        then:
        at ModifyWorklogPage

        when:
        at ModifyWorklogPage

        and:
        changeDataModule.changeTimeSpent()

        then:
        at IssuePage
    }

}

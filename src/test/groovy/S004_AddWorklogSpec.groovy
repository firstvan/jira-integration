import geb.spock.GebSpec;

public class S004_AddWorklogSpec extends GebSpec {

    void setupSpec() {
        Util.setProperties()
        to JiraIntegrationHomePage
        loginModule.fill()
        loginModule.login()
        at IndexPage
        searchInList.startSearch()
        searchInList.toIssue()
    }


    def "add a new worklog to issue"() {
        when:
        at IssuePage

        and:
        worklogSearch.addWorklog()

        then:
        at NewWorklogPage

        when:
        at NewWorklogPage

        and:
        addWorklogModule.fillForm()

        then:
        at IssuePage
    }
}
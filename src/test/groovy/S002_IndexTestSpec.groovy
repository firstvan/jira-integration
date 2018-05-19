import geb.spock.GebSpec

class S002_IndexTestSpec extends GebSpec {

    def setupSpec() {
        Util.setProperties()
        to JiraIntegrationHomePage
        loginModule.fill()
        loginModule.login()
    }

    def "test the index page"() {
        when:
        at IndexPage

        and:
        searchInList.startSearch()

        then:
        assert true == searchInList.checkResult()
    }
}

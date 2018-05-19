import geb.spock.GebSpec

class S001_LoginSpec extends GebSpec {

    static {
        System.setProperty("webdriver.chrome.driver", "c:\\Dev\\workspace\\jira-integration\\drivers\\chromedriver-windows-32bit.exe")
        System.setProperty("geb.build.baseUrl", "http://localhost:8888/jira-integration/")
    }

    def "can login to jira-integration"() {
        when:
        to JiraIntegrationHomePage

        and:
        loginModule.fill()

        then:
        assert loginModule.isFilled() == true

        when:
        loginModule.login()

        then:
        at IndexPage
    }
}
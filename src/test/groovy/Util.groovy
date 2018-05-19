class Util {
    static void setProperties() {
        System.setProperty("webdriver.chrome.driver", "c:\\Dev\\workspace\\jira-integration\\drivers\\chromedriver-windows-32bit.exe")
        System.setProperty("geb.build.baseUrl", "http://localhost:8888/jira-integration/")
    }
}

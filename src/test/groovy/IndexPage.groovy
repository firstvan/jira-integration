import geb.Page

class IndexPage extends Page {
    static at = { $('.display-4').text() == "Főoldal"}

    static content = {
        searchInList { module(SearchInListModule) }
    }
}

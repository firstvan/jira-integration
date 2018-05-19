import geb.Module

class SearchInListModule extends Module{
    static content = {
        searchField(wait:true) { $('input', id: 'searchPanel:inputKey') }
        searchButton(wait:true) { $('button', id: 'searchPanel:startSearch') }
        table { $('table > tbody > tr') }
        ftOneLink(wait:true) { table.getAt(0).children().getAt(0).children().getAt(0) }
    }

    void startSearch() {
        searchField = 'FT-1'
        searchButton.click()
    }

    boolean checkResult() {
        def rowCount = table.size()
        def firstRowName = ftOneLink.text()
        rowCount == 1 && firstRowName == 'FT-1'
    }

    void toIssue() {
        ftOneLink.click()
    }
}

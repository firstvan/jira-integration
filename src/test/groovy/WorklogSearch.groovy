import geb.Module

class WorklogSearch extends Module {
    static content = {
        calendar { $('input', id: 'searchPanel:c1_input') }
        searchButton { $('button', id: 'searchPanel:startSearch') }
        table { $('table > tbody > tr') }
        firstWorklogLink(wait:true) { table.getAt(0).children().getAt(0).children().getAt(0) }
        addWorklogLink(wait:true) { $('#addWorklog') }
    }

    void changeDate() {
        calendar = '2018/05/18'
        searchButton.click()
    }

    boolean checkResult() {
        def rowCount = table.size()
        def firstRowName = firstWorklogLink.text()
        rowCount == 1 && firstRowName == 'firstvan'
    }

    void toModifyWorklog() {
        firstWorklogLink.click()
    }

    void addWorklog() {
        addWorklogLink.click()
    }
}

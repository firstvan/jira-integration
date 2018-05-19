import geb.Page

class NewWorklogPage extends Page {
    static at = { $('.display-4').text() == "Új worklog rögzítése" }

    static content = {
        addWorklogModule { module(AddWorklogModule) }
    }
}

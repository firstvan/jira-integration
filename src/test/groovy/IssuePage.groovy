import geb.Page

class IssuePage extends Page {
    static at = { $('.display-4').text() == "Feladat részletei" }

    static content = {
        worklogSearch { module(WorklogSearch)}
    }
}

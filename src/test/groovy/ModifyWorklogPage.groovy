import geb.Page

class ModifyWorklogPage extends Page {
    static at = { $('.display-4').text() == "Worklog részletei" }

    static content = {
        changeDataModule { module(ChangeDataModule) }
    }
}

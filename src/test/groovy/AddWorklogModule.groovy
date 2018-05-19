import geb.Module

class AddWorklogModule extends Module {
    static content = {
        startTime { $('input', id: 'j_idt10:datetime_input') }
        spentTime { $('input', id: 'j_idt10:j_idt13') }
        comment { $('textarea', id: 'j_idt10:j_idt15') }
        saveBtn { $('button', id: 'j_idt10:mentes') }
    }

    void fillForm() {
        startTime = '2018/05/19 10:30'
        spentTime = '30m'
        comment = 'Automatizált tesztelés során keletkező üzenet!'
        saveBtn.click()
    }
}

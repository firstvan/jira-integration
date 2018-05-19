import geb.Module;

public class ChangeDataModule extends Module {
    static content = {
        timeSpent { $('input', id: 'j_idt10:j_idt13') }
        saveBtn { $('button', id: 'j_idt10:mentes') }
    }

    void changeTimeSpent() {
        timeSpent = "2h"
        saveBtn.click()
    }
}


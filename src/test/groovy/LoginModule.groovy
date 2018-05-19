import geb.Module
import org.apache.commons.lang3.StringUtils

class LoginModule extends Module {
    static content = {
        username(wait:true) { $( 'input', id: 'loginForm:j_username')}
        password(wait:true) { $('input', id: 'loginForm:j_password') }
        loginBtn(wait:true) { $('button', id: 'loginForm:doLogin') }
    }

    void fill() {
        username.value('firstvan')
        password.value('Pid34Kut')
    }

    void login() {
        loginBtn.click()
    }

    boolean isFilled() {
        return StringUtils.isNotBlank(username.value() as String) && StringUtils.isNotBlank(password.value() as String)
    }
}
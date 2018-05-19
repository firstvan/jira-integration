package hu.firstvan.view;

import hu.firstvan.controller.LoginBean;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginView implements Serializable {
    private String username;
    private String password;

    @Inject
    private LoginBean login;

    public LoginView() {

    }

    public LoginView(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void doLogin() {
        try {
            if (login.doLogin(username, password, false)) {
                HttpServletRequest httpRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                HttpSession httpSession = httpRequest.getSession();
                httpSession.setAttribute("usrname", username);
                FacesContext.getCurrentInstance().getExternalContext().redirect("views/index.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/error.xhtml");
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

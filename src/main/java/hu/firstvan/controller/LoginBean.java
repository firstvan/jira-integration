package hu.firstvan.controller;

import hu.firstvan.client.LoginToJira;
import hu.firstvan.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LoginBean {

    @Inject
    LoginToJira loginToJira;

    public boolean doLogin(final String username, final String password, final boolean isRest) {
        User user = new User(username, password);
        return loginToJira.login(user, isRest);
    }
}

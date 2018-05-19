package hu.firstvan.controller;

import hu.firstvan.communication.LoggedUser;
import org.apache.commons.lang3.StringUtils;

import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class HttpUtils {

    protected String getUsernamePropertyFromSession(final ExternalContext externalContext) {
        HttpServletRequest servletRequest = (HttpServletRequest) externalContext.getRequest();
        return (String) servletRequest.getSession().getAttribute("usrname");
    }

    protected String getUsernamePropertyFromSession(final HttpServletRequest request) {
        return (String) request.getSession().getAttribute("usrname");
    }

    protected String getParam(final String parameterName, final ExternalContext context) {
        Map<String, String> paramMap = context.getRequestParameterMap();
        return paramMap.get(parameterName);
    }

    protected boolean checkUserIsAuthenticated(final HttpServletRequest request) {
        String user = getUsernamePropertyFromSession(request);
        LoggedUser loggedUser = LoggedUser.getInstance();
        return !StringUtils.isEmpty(user) && loggedUser.userIsAuthenticated(user);
    }

    protected void navigateToErrorSite(final ExternalContext externalContext) {
        try {
            externalContext.redirect("faces/error.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

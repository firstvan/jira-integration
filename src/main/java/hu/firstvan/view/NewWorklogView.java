package hu.firstvan.view;

import hu.firstvan.controller.HttpUtils;
import hu.firstvan.controller.IssueBean;
import hu.firstvan.model.Worklog;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class NewWorklogView extends HttpUtils implements Serializable {

    private Worklog worklog;
    private String issueId;
    private String message;

    @Inject
    private IssueBean issueBean;

    @PostConstruct
    public void init() {
        message = "";
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        issueId = this.getParam("issueId", context);
        worklog = new Worklog();
    }

    public void save() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        int result = issueBean.createWorklog(this.getUsernamePropertyFromSession(context), issueId, worklog);
        if(result == 201) {
            context.redirect(String.format("issue.xhtml?issueId=%s", issueId));
        } else {
            message = "Hiba történt a feldolgozás során!";
        }
    }

    public boolean messageIsNotEmpty() {
        return !StringUtils.isEmpty(message);
    }

    public Worklog getWorklog() {
        return worklog;
    }

    public void setWorklog(Worklog worklog) {
        this.worklog = worklog;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

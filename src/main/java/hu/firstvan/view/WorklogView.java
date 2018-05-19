package hu.firstvan.view;

import hu.firstvan.controller.DateUtil;
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
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Named
@ViewScoped
public class WorklogView extends HttpUtils implements Serializable {

    private String issueId;
    private String worklogId;
    private String message;

    private Worklog worklog;

    @Inject
    private IssueBean issueBean;

    @PostConstruct
    public void init() {
        message = "";
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        issueId = this.getParam("issueId", context);
        worklogId = this.getParam("worklogId", context);
        worklog = issueBean.getWorklogByKey(this.getUsernamePropertyFromSession(context), issueId, worklogId);
        LocalDateTime time = worklog.getStarted().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        worklog.setStarted(Date.from(time.toInstant(ZoneOffset.ofHours(4))));
    }

    public void save() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        int status = issueBean.modifyWorklog(this.getUsernamePropertyFromSession(context), issueId, worklog);
        if(status == 200) {
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
}

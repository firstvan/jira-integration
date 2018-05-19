package hu.firstvan.view;

import hu.firstvan.controller.HttpUtils;
import hu.firstvan.controller.IssueBean;
import hu.firstvan.model.Issue;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class IndexView extends HttpUtils implements Serializable {

    private List<Issue> issues;

    private String issueKey;

    @Inject
    private IssueBean issueBean;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String username = this.getUsernamePropertyFromSession(externalContext);
        issues = issueBean.getUsedIssueByUser(username);
    }

    public void searchIssue() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String username = this.getUsernamePropertyFromSession(externalContext);
        issues = new ArrayList<>();
        Issue searched = issueBean.getIssueByKey(username, issueKey);
        if (searched != null) {
            issues.add(searched);
        }
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }
}

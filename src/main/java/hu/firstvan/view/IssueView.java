package hu.firstvan.view;

import hu.firstvan.controller.HttpUtils;
import hu.firstvan.controller.IssueBean;
import hu.firstvan.filter.WorklogFilter;
import hu.firstvan.model.Issue;
import hu.firstvan.model.Worklog;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class IssueView extends HttpUtils implements Serializable {

    private String issueId;

    private Issue issue;

    private Date searchDate;

    private List<Worklog> worklogs;

    @Inject
    private IssueBean issueBean;

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        issueId = this.getParam("issueId", context);
        issue = issueBean.getIssueByKey(this.getUsernamePropertyFromSession(context), issueId);
        worklogs = Arrays.asList(issue.getFields().getWorklog().getWorklogs());
        searchDate = new Date();
        worklogs = worklogs.stream().filter(worklog -> WorklogFilter.sameDateByDay(worklog.getStarted(), searchDate)).collect(Collectors.toList());
    }

    public void changeDay() {
        worklogs = Arrays.asList(issue.getFields().getWorklog().getWorklogs());
        Calendar c = Calendar.getInstance();
        c.setTime(searchDate);
        c.add(Calendar.DATE, 1);
        worklogs = worklogs.stream().filter(worklog -> WorklogFilter.sameDateByDay(worklog.getStarted(), c.getTime())).collect(Collectors.toList());
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public List<Worklog> getWorklogs() {
        return worklogs;
    }

    public void setWorklogs(List<Worklog> worklogs) {
        this.worklogs = worklogs;
    }
}

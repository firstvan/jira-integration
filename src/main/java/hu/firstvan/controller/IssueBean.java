package hu.firstvan.controller;

import hu.firstvan.client.IssueFromJira;
import hu.firstvan.model.Issue;
import hu.firstvan.model.Worklog;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class IssueBean {

    @Inject
    private IssueFromJira issueFromJira;

    public List<Issue> getUsedIssueByUser(final String username) {
        return issueFromJira.getLoggedInUserIssues(username);
    }

    public Issue getIssueByKey(final String username, final String key) {
        return issueFromJira.getIssueByKey(username, key);
    }

    public Worklog getWorklogByKey(final String username, final String issueId, final String worklogId) {
        return issueFromJira.getWorklogById(username, issueId, worklogId);
    }

    public int modifyWorklog(final String username, final String issueId, final Worklog worklog) {
        return issueFromJira.modifyWorklog(username, issueId, worklog);
    }

    public int createWorklog(final String username, final String issueId, final Worklog worklog) {
        return issueFromJira.createWorklog(username, issueId, worklog);
    }

    public String getWorklogSummaryByDateAndUser(final String username, final String date,
                                              final String user, final String token) {
        return issueFromJira.getWorklogSummaryByDateAndUser(username, date, user, token);
    }
}

package hu.firstvan.client;

import hu.firstvan.communication.LoggedUser;
import hu.firstvan.communication.TokenHolder;
import hu.firstvan.converter.WorklogChangeConverter;
import hu.firstvan.filter.WorklogFilter;
import hu.firstvan.model.Issue;
import hu.firstvan.model.IssueList;
import hu.firstvan.model.Worklog;
import hu.firstvan.model.WorklogChanges;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class IssueFromJira extends CommunicateWithJira {

    public List<Issue> getLoggedInUserIssues(final String username) {
        String formatedUrl = String.format("rest/api/2/search?jql=assignee=%s+order+by+updated", username);

        try (Response response = this.get(formatedUrl, username)) {
            if (response.getStatus() == 200) {
                IssueList issueList = response.readEntity(IssueList.class);
                return new ArrayList<Issue>(Arrays.asList(issueList.getIssues()));
            }
        }

        return null;
    }

    public Issue getIssueByKey(final String username, final String key) {
        String path = String.format("/rest/api/2/issue/%s", key);
        return this.getObjectFromPath(Issue.class, path, username);
    }

    public Worklog getWorklogById(final String username, final String issueId, final String worklogId) {
        String path = String.format("/rest/api/2/issue/%s/worklog/%s", issueId, worklogId);
        return this.getObjectFromPath(Worklog.class, path, username);
    }

    public int modifyWorklog(final String username, final String issueId, final Worklog worklog) {
        String path = String.format("/rest/api/2/issue/%s/worklog/%s", issueId, worklog.getId());
        LoggedUser loggedUser = LoggedUser.getInstance();
        TokenHolder tokenHolder = loggedUser.getUserToken(username);

        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + path).request()
                .accept(MediaType.APPLICATION_JSON_TYPE).cookie("JSESSIONID", tokenHolder.getToken())
                .header("x-ausername", tokenHolder.getUsername());


        Entity<WorklogChanges> worklogEntity = Entity.json(WorklogChangeConverter.convert(worklog));
        Response response = request.buildPut(worklogEntity).invoke();

        return response.getStatus();
    }

    public int createWorklog(final String username, final String issueId, final Worklog worklog) {
        String path = String.format("/rest/api/2/issue/%s/worklog", issueId);
        LoggedUser loggedUser = LoggedUser.getInstance();
        TokenHolder tokenHolder = loggedUser.getUserToken(username);

        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + path).request()
                .accept(MediaType.APPLICATION_JSON_TYPE).cookie("JSESSIONID", tokenHolder.getToken())
                .header("x-ausername", tokenHolder.getUsername());


        Entity<WorklogChanges> worklogEntity = Entity.json(WorklogChangeConverter.convert(worklog));
        Response response = request.buildPost(worklogEntity).invoke();

        return response.getStatus();
    }

    public String getWorklogSummaryByDateAndUser(final String username, final String date,
                                              final String user, final String token) {
        if (!date.matches("[0-2][0-9][0-9][0-9]-(0[1-9]|1[0-2])-([0-2][0-9]|3[0-2])")) {
            return "!Dátum formátum nem megfelelő: 'yyyy-MM-dd'";
        }

        TokenHolder tokenHolder = new TokenHolder();
        tokenHolder.setUsername(user);
        tokenHolder.setToken(token);

        String path =
                String.format("/rest/api/latest/search?jql=worklogDate='%s' AND worklogAuthor='%s'&fields=worklog",
                        date, username);

        Client client = ClientBuilder.newClient();
        Invocation.Builder request = client.target(BASE_URL + path).request()
                .accept(MediaType.APPLICATION_JSON_TYPE).cookie("JSESSIONID", tokenHolder.getToken())
                .header("x-ausername", tokenHolder.getUsername());

        IssueList issueList = request.get(IssueList.class);

        if(issueList == null) {
            return "!Hiba történt a lekérdezés során!";
        }

        List<Issue> issues = Arrays.asList(issueList.getIssues());

        long summary = issues.stream()
                .flatMap(issue -> Arrays.stream(issue.getFields().getWorklog().getWorklogs()))
                .filter(wl -> WorklogFilter.sameDateByDay(wl.getStarted(), date))
                .mapToLong(wl -> Long.valueOf(wl.getTimeSpentSeconds())).sum();

        return WorklogFilter.getStringFromSecound(summary);
    }
}

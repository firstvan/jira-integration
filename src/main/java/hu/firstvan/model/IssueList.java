package hu.firstvan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueList {
    private Long total;
    private Issue[] issues;

    public IssueList() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Issue[] getIssues() {
        return issues;
    }

    public void setIssues(Issue[] issues) {
        this.issues = issues;
    }
}

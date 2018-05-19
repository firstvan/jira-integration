package hu.firstvan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorklogList {
    private long total;
    private Worklog[] worklogs;

    public WorklogList() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Worklog[] getWorklogs() {
        return worklogs;
    }

    public void setWorklogs(Worklog[] worklogs) {
        this.worklogs = worklogs;
    }
}

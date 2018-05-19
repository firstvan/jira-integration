package hu.firstvan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hu.firstvan.controller.DateUtil;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {
    private String description;
    private String summary;
    private Date updated;
    private Status status;
    private Project project;
    private Date created;
    private WorklogList worklog;

    public Fields() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getUpdated() {
        return DateUtil.getNormalDate(updated);
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getCreated() {
        return DateUtil.getNormalDate(created);
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public WorklogList getWorklog() {
        return worklog;
    }

    public void setWorklog(WorklogList worklog) {
        this.worklog = worklog;
    }
}

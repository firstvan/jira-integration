package hu.firstvan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hu.firstvan.controller.DateUtil;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Worklog {
    private Long id;
    private Author author;
    private Date created;
    private Date updated;
    private Date started;
    private String timeSpent;
    private String timeSpentSeconds;
    private String comment;

    public Worklog() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getCreated() {
        return DateUtil.getNormalDate(created);
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return DateUtil.getNormalDate(updated);
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getStarted() {
        return started;
    }

    public Date getStartedWithoutNormalize() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeSpentSeconds() {
        return timeSpentSeconds;
    }

    public void setTimeSpentSeconds(String timeSpentSeconds) {
        this.timeSpentSeconds = timeSpentSeconds;
    }
}

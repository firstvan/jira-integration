package hu.firstvan.model;

import java.time.LocalDateTime;
import java.util.Date;

public class WorklogChanges {
    private String comment;
    private String timeSpent;
    private String started;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }
}

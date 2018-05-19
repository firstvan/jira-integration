package hu.firstvan.converter;

import hu.firstvan.model.Worklog;
import hu.firstvan.model.WorklogChanges;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class WorklogChangeConverter {

    public static WorklogChanges convert(final Worklog worklog) {
        WorklogChanges worklogChanges = new WorklogChanges();
        worklogChanges.setComment(worklog.getComment());
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat time = new SimpleDateFormat("HH:mm:ss");
        String startString = date.format(worklog.getStartedWithoutNormalize()) + "T" + time.format(worklog.getStartedWithoutNormalize()) + ".000+0000";
        worklogChanges.setStarted(startString);
        worklogChanges.setTimeSpent(worklog.getTimeSpent());

        return worklogChanges;
    }
}

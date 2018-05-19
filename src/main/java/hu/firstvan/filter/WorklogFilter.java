package hu.firstvan.filter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorklogFilter {
    public static boolean sameDateByDay(Date date, Date filterCriteria) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = format.format(date);
        String filterCriteriaInString = format.format(filterCriteria);
        boolean res = dateInString.equals(filterCriteriaInString);
        return res;
    }

    public static boolean sameDateByDay(Date date, String criteria) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = format.format(date);
        boolean res = dateInString.equals(criteria);
        return res;
    }

    public static String getStringFromSecound(final long secound) {
        String res = "";

        long hour = secound / 3600;
        long rest = secound % 3600;
        long minute = rest / 60;

        res = String.format("%dh %dm", hour, minute);

        return res;
    }
}

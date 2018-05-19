package hu.firstvan.controller;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    private Date date;
    private Date dateAfter;

    @Before
    public void setUp() throws Exception {
        String string = "2018-05-19 08:00";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        date = format.parse(string);
        String afterDateString = "2018-05-19 10:00";
        dateAfter = format.parse(afterDateString);
    }

    @Test
    public void testGetNormalizedDate() {
        assertEquals(dateAfter, DateUtil.getNormalDate(date));
    }
}

package hu.firstvan.converter;

import hu.firstvan.model.Author;
import hu.firstvan.model.Worklog;
import hu.firstvan.model.WorklogChanges;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class WorklogChangeConverterTest {

    private WorklogChanges worklogChangesWithConverter;
    private WorklogChanges worklogChanges;

    @Before
    public void setUp() throws ParseException {
        String string = "2018-05-19 08:00";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = format.parse(string);

        Author author = new Author();
        author.setEmailAddress("firstvan@live.com");
        author.setName("firstvan");

        Worklog worklog = new Worklog();
        worklog.setStarted(date);
        worklog.setId(1L);
        worklog.setAuthor(author);
        worklog.setComment("commentelve van");
        worklog.setTimeSpent("1h");

        worklogChangesWithConverter = WorklogChangeConverter.convert(worklog);

        worklogChanges = new WorklogChanges();
        worklogChanges.setTimeSpent("1h");
        worklogChanges.setComment("commentelve van");
        worklogChanges.setStarted("2018-05-19T08:00:00.000+0000");
    }

    @Test
    public void testTimeSpent() {
        assertEquals(worklogChanges.getTimeSpent(), worklogChangesWithConverter.getTimeSpent());
    }

    @Test
    public void testComment() {
        assertEquals(worklogChanges.getComment(), worklogChangesWithConverter.getComment());
    }

    @Test
    public void testStarted() {
        assertEquals(worklogChanges.getStarted(), worklogChangesWithConverter.getStarted());
    }
}

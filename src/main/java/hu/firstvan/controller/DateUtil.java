package hu.firstvan.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtil {
    public static Date getNormalDate(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(localDateTime.toInstant(ZoneOffset.ofHours(0)));
    }
}

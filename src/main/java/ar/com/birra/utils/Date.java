package ar.com.birra.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class Date {
    public static java.util.Date GetDateWithoutTime(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static java.util.Date ConvertStringToDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
    }

    public static java.util.Date ConvertStringToDateWithoutTime(String date) throws ParseException {
        return GetDateWithoutTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));
    }

    public static long GetDaysBetween(java.util.Date date1, java.util.Date date2) {
        LocalDate dt1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dt2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(dt1, dt2);
    }

    public static long GetHoursBetween(java.util.Date date1, java.util.Date date2) {
        LocalDateTime dt1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime dt2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return ChronoUnit.HOURS.between(dt1, dt2);
    }
}
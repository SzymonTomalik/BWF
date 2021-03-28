package pl.coderslab.BWF.converter;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;


@Component
public class DateConverter {
    public static String getDateNow() {
        DateTime d = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd, HH:mm");
        return d.toString(fmt);

    }

    public String changeDateToPattern(String date) {
        DateTime d = DateTime.parse(date);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd, HH:mm");
        return d.toString(fmt);
    }

    public boolean isEarlier(String data1, String data2) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd, HH:mm");
        LocalDateTime d1 = LocalDateTime.parse(data1, fmt);
        LocalDateTime d2 = LocalDateTime.parse(data2, fmt);
        return d1.isBefore(d2);
    }

}

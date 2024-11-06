package com.mocktest.until;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ParseTime {
    public static LocalTime convertToTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(dateTime.toLocalTime().format(formatter), formatter);

        return time;
    }
}

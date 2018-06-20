package com.programista.EventsManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateTimeParser {

    public static String parseToString(LocalDateTime localDateTime, String format) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String formattedDateTime = localDateTime.format(formatter);// "1986-04-08 12:30"
        return formattedDateTime;

    }

    public static LocalDateTime parseToLocalDateTime(String dateTime, String format) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

        return localDateTime;
    }

}

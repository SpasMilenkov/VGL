package com.jaba.vgl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static Date toISO8601(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        String formattedDateTime = localDateTime.format(formatter);

        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(formattedDateTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

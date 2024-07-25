package com.bytecoder.DesignPatterns.structural.adapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LocalDateTime getLocaltimeDate(String dateTime, DateTimeFormatter str){
        return LocalDateTime.parse(dateTime, str);
    }
}

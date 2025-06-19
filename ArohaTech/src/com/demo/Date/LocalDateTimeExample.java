package com.demo.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeExample {
    public static void main(String[] args) {
        // Current date and time
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current DateTime: " + now);
        
        // Specific date and time
        LocalDateTime newYear = LocalDateTime.of(2024, 1, 1, 0, 0);
        System.out.println("New Year: " + newYear);
        
        
        // Formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println("Formatted DateTime: " + formattedDateTime);
        
        // DateTime arithmetic
        LocalDateTime nextHour = now.plusHours(1);
        LocalDateTime nextDay = now.plusDays(1);
        LocalDateTime nextMonth = now.plusMonths(1);
        
        System.out.println("Next Hour: " + nextHour);
        System.out.println("Next Day: " + nextDay);
        System.out.println("Next Month: " + nextMonth);
        
        // Combining LocalDate and LocalTime
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime combined = LocalDateTime.of(date, time);
        System.out.println("Combined DateTime: " + combined);
    }
}
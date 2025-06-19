package com.demo.Date;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeExample {
    public static void main(String[] args) {
        // Current time
        LocalTime now = LocalTime.now();
        System.out.println("Current Time: " + now);
        
        // Specific time
        LocalTime lunchTime = LocalTime.of(13, 30);
        System.out.println("Lunch Time: " + lunchTime);
        
        // Parsing from string
        LocalTime parsedTime = LocalTime.parse("15:45:30");
        System.out.println("Parsed Time: " + parsedTime);
        
        // Formatting time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = now.format(formatter);
        System.out.println("Formatted Time: " + formattedTime);
        
        // Time arithmetic
        LocalTime inOneHour = now.plusHours(1);
        LocalTime in30Minutes = now.plusMinutes(30);
        LocalTime in45Seconds = now.plusSeconds(45);
        
        System.out.println("In one hour: " + inOneHour);
        System.out.println("In 30 minutes: " + in30Minutes);
        System.out.println("In 45 seconds: " + in45Seconds);
        
        // Comparing times
        System.out.println("Is now before lunch? " + now.isBefore(lunchTime));
    }
}
package com.demo.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateExample {
    public static void main(String[] args) {
        // Current date
        LocalDate today = LocalDate.now();
        System.out.println("Current Date: " + today);
        
        // Specific date
        LocalDate independenceDay = LocalDate.of(1947, 8, 15);
        System.out.println("Independence Day: " + independenceDay);
      
        // Formatting dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = today.format(formatter);
        System.out.println("Formatted Date: " + formattedDate);
        
        // Date arithmetic
        LocalDate tomorrow = today.plusDays(1);
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate nextYear = today.plusYears(1);
        
        System.out.println("Tomorrow: " + tomorrow);
        System.out.println("Next Week: " + nextWeek);
        System.out.println("Next Month: " + nextMonth);
        System.out.println("Next Year: " + nextYear);
        
        // Comparing dates
        System.out.println("Is today after tomorrow? " + today.isAfter(tomorrow));
        System.out.println("Is today before tomorrow? " + today.isBefore(tomorrow));
    }
}

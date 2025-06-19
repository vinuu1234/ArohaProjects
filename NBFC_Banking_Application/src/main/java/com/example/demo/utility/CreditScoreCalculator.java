package com.example.demo.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import com.example.demo.entity.Customer;

public class CreditScoreCalculator {
    
    public static int calculateBasicCreditScore(Customer customer) {
        int score = 600; // Base score
        
        // 1. Age factor (older customers get higher scores)
        if (customer.getDob() != null) {
            int age = Period.between(customer.getDob(), LocalDate.now()).getYears();
            score += Math.min(age, 30); // Max +30 for age
        }
        
        // 2. Occupation stability
        if (customer.getOccupation() != null) {
            if (customer.getOccupation().matches("(Government|Doctor|Engineer)")) {
                score += 50;
            }
        }
        
          
        // 3. Credit history length
        if (customer.getCreatedAt() != null) {
            long monthsWithAccount = ChronoUnit.MONTHS.between(
                customer.getCreatedAt(), 
                LocalDateTime.now()
            );
            score += Math.min(monthsWithAccount, 60); // Max +60 for history
        }
        
        // Ensure score stays within bounds
        return Math.min(Math.max(score, 300), 900);
    }
}
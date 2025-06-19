package com.example.demo.dto.LoanProcessDTO;

import java.time.LocalDate;

import lombok.Data;
@Data
public class LoanUpdateDTO {
    private Double approvedAmount; 
    private Integer tenureMonths;
    private Double emiAmount;
    private Double interestRate;    
    private LocalDate endDate;
    private LocalDate nextPaymentDate;
    
}

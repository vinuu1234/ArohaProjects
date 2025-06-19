package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoanApplicationUpdateDTO {
    
    //private Long applicationId;

    @Positive(message = "Requested amount must be positive")
   
    private Double requestedAmount;
    
    @Positive(message = "Tenure must be positive (in months)")
    private Integer requestedTenureMonths;
    
    @Size(max = 500, message = "Purpose cannot exceed 500 characters")
    private String purpose;

   
}
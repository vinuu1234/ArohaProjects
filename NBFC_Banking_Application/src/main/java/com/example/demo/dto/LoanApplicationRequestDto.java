package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanApplicationRequestDto {
    private Double requestedAmount;
    private Integer requestedTenureMonths;
    private String purpose;
    private Long customerId;
    private Long loanProductId;
    
}

package com.example.demo.dto;

import lombok.Data;

@Data
public class LoanProductBasicDTO {
    private Long loanProductId;
    private String productName;
    private Double interestRate;
    
}
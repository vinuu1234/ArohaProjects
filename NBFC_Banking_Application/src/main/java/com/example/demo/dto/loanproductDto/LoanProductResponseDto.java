package com.example.demo.dto.loanproductDto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class LoanProductResponseDto {
    private Long loanProductId;
    private String loanProductName;
    private String description;
    private double minAmount;
    private double maxAmount;
    private int minTenureMonths;
    private int maxTenureMonths;
    private double baseInterestRate;
    private List<String> requiredDocuments;
    private List<String> features;
}
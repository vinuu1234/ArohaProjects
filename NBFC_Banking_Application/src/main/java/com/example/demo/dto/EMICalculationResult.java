package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EMICalculationResult {
    private Double monthlyEMI;
    private Double totalPayment;
    private Double totalInterest;
    private Integer tenureMonths;
}
package com.example.demo.dto;

import java.time.LocalDateTime;
import com.example.demo.entity.Status;
import lombok.Data;

@Data
public class LoanApplicationResponseDTO {
    private Long applicationId;
    private Double requestedAmount;
    private Integer requestedTenureMonths;
    private String purpose;
    private LocalDateTime applicationDate=LocalDateTime.now();
    private Status status;
    private CustomerBasicDTO customer;
    private LoanProductBasicDTO loanProduct;
    
}
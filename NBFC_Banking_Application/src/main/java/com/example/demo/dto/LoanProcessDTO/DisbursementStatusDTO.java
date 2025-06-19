package com.example.demo.dto.LoanProcessDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DisbursementStatusDTO {
    private String transactionId;
    private String status; // "PROCESSING", "COMPLETED", "FAILED"
    private LocalDateTime lastUpdated;
    private String details;
}
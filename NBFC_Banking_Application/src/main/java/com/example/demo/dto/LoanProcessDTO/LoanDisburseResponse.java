package com.example.demo.dto.LoanProcessDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanDisburseResponse {
   
	private Long loanId;
    private Double disbursedAmount;
    private String transactionId;
    private String message;
    private LocalDateTime timestamp;
}


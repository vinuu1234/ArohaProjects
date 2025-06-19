package com.example.demo.dto.LoanProcessDTO;


import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class LoanResponseDTO {
    private Long loanId;
    private String loanAccountNumber;
    private Long applicationId;
    private LocalDate disbursementDate;
    private Double approvedAmount;
    private Integer tenureMonths;
    private Double emiAmount;
    private Double interestRate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // Active, Closed, Defaulted, Preclosed
    private Double remainingBalance;
    private LocalDate lastPaymentDate;
    private LocalDate nextPaymentDate;
    //private List<PaymentDTO> payments;
    
}
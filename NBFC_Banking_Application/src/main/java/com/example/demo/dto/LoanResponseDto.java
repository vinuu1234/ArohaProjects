package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.Status;

import lombok.Data;

@Data
public class LoanResponseDto {

	private Long applicationId;
	private Double requestedAmount;
	private Integer requestedTenureMonths;
	private String purpose;
	private LocalDateTime applicationDate;
	private Status status;
	private Long customerId;
	private String loanProductId;
	

}

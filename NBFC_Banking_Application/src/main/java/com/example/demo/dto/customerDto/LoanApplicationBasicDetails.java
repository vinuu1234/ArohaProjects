package com.example.demo.dto.customerDto;

import lombok.Data;
@Data
public class LoanApplicationBasicDetails {
	private Long applicationId;
	private String loanProductName;
	private String status;
}

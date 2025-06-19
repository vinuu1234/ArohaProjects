package com.example.demo.dto.customerDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.Gender;
import com.example.demo.entity.LoanApplication;

import lombok.Data;

@Data
public class CustomerResponseDto {

	private Long customerId;
	private String customerName;
	private String customerAddress;
	private String email;
	private String phoneNumber;
	private LocalDate dob;
	private Gender gender;
	private String occupation;
	private Boolean isActive;
	private LocalDateTime createdAt;

	private List<LoanApplicationBasicDetails> loanApplications;
}


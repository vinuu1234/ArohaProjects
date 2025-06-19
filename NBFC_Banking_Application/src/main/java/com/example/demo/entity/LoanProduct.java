package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class LoanProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanProductId;
	private String loanProductName;
	private String description;
	private double minAmount;
	private double maxAmount;
	private int minTenureMonths;
	private int maxTenureMonths;
	private double baseInterestRate;
	private LocalDateTime createdDate = LocalDateTime.now();
	private LocalDateTime lastUpdated;
	
	@ElementCollection
    private List<String> requiredDocuments;

	@ElementCollection
	private List<String> features;
	
	@OneToMany(mappedBy = "loanProduct")
	private List<LoanApplication> loanApplication;
}

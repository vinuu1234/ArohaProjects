package com.example.demo.dto.loanproductDto;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LoanProductRequestDto {
    
    @NotBlank(message = "Product name is required")
    private String loanProductName;
    
    private String description;
    
    @Positive(message = "Minimum amount must be positive")
    @NotNull(message = "Minimum amount is required")
    private Double minAmount;
    
    @Positive(message = "Maximum amount must be positive")
    @NotNull(message = "Maximum amount is required")
    private Double maxAmount;
    
    @Positive(message = "Minimum tenure must be positive")
    @NotNull(message = "Minimum tenure is required")
    private Integer minTenureMonths;
    
    @Positive(message = "Maximum tenure must be positive")
    @NotNull(message = "Maximum tenure is required")
    private Integer maxTenureMonths;
    
    @Positive(message = "Interest rate must be positive")
    @NotNull(message = "Interest rate is required")
    private Double baseInterestRate;
    private LocalDateTime createdDate = LocalDateTime.now();
	private LocalDateTime lastUpdated;
    private Boolean isActive;
    private List<String> requiredDocuments;
    private List<String> features;
}
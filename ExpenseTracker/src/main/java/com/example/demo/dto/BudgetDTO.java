package com.example.demo.dto;


import org.antlr.v4.runtime.misc.NotNull;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//BudgetDTO.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDTO {
private Long id;

@NotBlank(message = "Month/Year is required")
@Pattern(regexp = "^\\d{4}-\\d{2}$", message = "Format must be YYYY-MM")
private String monthYear;

@NotBlank(message = "Amount is required")
@Positive(message = "Amount must be positive")
private Double amount;
}


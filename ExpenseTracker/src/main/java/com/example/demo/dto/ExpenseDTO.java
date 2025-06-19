package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//ExpenseDTO.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
	private Long id;

	@NotNull(message = "Date is required")
	private LocalDate date;

	@NotBlank(message = "Category is required")
	private String category;

	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must be positive")
	private Double amount;

	private String description;
}

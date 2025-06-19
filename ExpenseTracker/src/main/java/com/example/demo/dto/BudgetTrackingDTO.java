package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//BudgetTrackingDTO.java
@Data
@NoArgsConstructor

@AllArgsConstructor
public class BudgetTrackingDTO {
private Double budgetAmount;
private Double totalExpenses;
private Double remaining;
private Boolean exceeded;
}
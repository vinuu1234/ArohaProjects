package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BudgetDTO;
import com.example.demo.dto.BudgetTrackingDTO;
import com.example.demo.services.BudgetService;

import jakarta.validation.Valid;

//BudgetController.java
@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    
    @Autowired
    private BudgetService budgetService;
    
    @PostMapping
    public ResponseEntity<BudgetDTO> setBudget(@Valid @RequestBody BudgetDTO budgetDTO,
                                             Authentication authentication) {
        return ResponseEntity.ok(budgetService.setBudget(budgetDTO, authentication.getName()));
    }
    
    @GetMapping("/track")
    public ResponseEntity<BudgetTrackingDTO> trackBudget(
            @RequestParam String monthYear,
            Authentication authentication) {
        return ResponseEntity.ok(budgetService.trackBudget(monthYear, authentication.getName()));
    }
}

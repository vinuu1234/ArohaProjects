package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


import com.example.demo.dto.ExpenseDTO;
import com.example.demo.services.ExpenseService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;
    
    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@Valid @RequestBody ExpenseDTO expenseDTO,
                                               Authentication authentication) {
        return ResponseEntity.ok(expenseService.addExpense(expenseDTO, authentication.getName()));
    }
    
    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses(
            @RequestParam(required = false) String monthYear,
            Authentication authentication) {
        return ResponseEntity.ok(expenseService.getAllExpenses(authentication.getName(), monthYear));
    }
    
    @GetMapping("/export")
    public void exportToCsv(HttpServletResponse response,
                          Authentication authentication) throws IOException {
        expenseService.exportToCsv(authentication.getName(), response);
    }
}
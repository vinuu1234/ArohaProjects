package com.example.demo.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Budget;
import com.example.demo.entity.Expense;
import com.example.demo.service.ExpenseTrackerService;

@RestController
@RequestMapping("/api")
public class ExpenseTrackerController {
    
    @Autowired
    private ExpenseTrackerService expenseTrackerService;
    
    @PostMapping("/expenses")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseTrackerService.addExpense(expense));
    }
    
    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseTrackerService.getAllExpenses());
    }
    
    @GetMapping("/expenses/category/{category}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(expenseTrackerService.getExpensesByCategory(category));
    }
    
    @GetMapping("/expenses/date-range")
    public ResponseEntity<List<Expense>> getExpensesByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return ResponseEntity.ok(expenseTrackerService.getExpensesByDateRange(
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)));
    }
    
    @PostMapping("/budget")
    public ResponseEntity<Budget> setMonthlyBudget(
            @RequestParam String month, // Format: "2023-10"
            @RequestParam Double amount) {
        return ResponseEntity.ok(expenseTrackerService.setMonthlyBudget(
                YearMonth.parse(month),
                amount));
    }
    
    @GetMapping("/budget/status")
    public ResponseEntity<String> getBudgetStatus(@RequestParam String month) {
        return ResponseEntity.ok(expenseTrackerService.getBudgetStatus(YearMonth.parse(month)));
    }
    
    @GetMapping("/expenses/monthly-total")
    public ResponseEntity<Double> getTotalExpensesForMonth(@RequestParam String month) {
        return ResponseEntity.ok(expenseTrackerService.getTotalExpensesForMonth(YearMonth.parse(month)));
    }
}
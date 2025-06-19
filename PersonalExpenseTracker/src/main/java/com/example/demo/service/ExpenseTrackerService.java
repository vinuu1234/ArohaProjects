package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Budget;
import com.example.demo.entity.Expense;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.repository.ExpenseRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class ExpenseTrackerService {
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Autowired
    private BudgetRepository budgetRepository;
    
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }
    
    public List<Expense> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByDateBetween(startDate, endDate);
    }
    
    public Budget setMonthlyBudget(YearMonth month, Double amount) {
        Budget budget = budgetRepository.findByMonth(month)
            .orElse(new Budget(month, amount));
        budget.setMonth(month);
        budget.setAmount(amount);
        return budgetRepository.save(budget);
    }
    
    public String getBudgetStatus(YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();
        
        List<Expense> expenses = expenseRepository.findByDateBetween(startDate, endDate);
        Double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        
        Budget budget = budgetRepository.findByMonth(month).orElse(null);
        
        if (budget == null) {
            return "No budget set for " + month;
        }
        
        if (totalExpenses > budget.getAmount()) {
            return String.format("You have exceeded your budget by %.2f", (totalExpenses - budget.getAmount()));
        } else {
            return String.format("You have %.2f left for %s", (budget.getAmount() - totalExpenses), month);
        }
    }
    
    public Double getTotalExpensesForMonth(YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();
        List<Expense> expenses = expenseRepository.findByDateBetween(startDate, endDate);
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }
}

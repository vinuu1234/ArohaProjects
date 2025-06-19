package com.example.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BudgetDTO;
import com.example.demo.dto.BudgetTrackingDTO;
import com.example.demo.entity.Budget;
import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BudgetDTO setBudget(BudgetDTO budgetDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        // Check if budget already exists for this month
        Optional<Budget> existingBudget = budgetRepository.findByUserAndMonthYear(user, budgetDTO.getMonthYear());
        
        Budget budget;
        if (existingBudget.isPresent()) {
            budget = existingBudget.get();
            budget.setAmount(budgetDTO.getAmount());
        } else {
            budget = modelMapper.map(budgetDTO, Budget.class);
            budget.setUser(user);
        }

        Budget savedBudget = budgetRepository.save(budget);
        return modelMapper.map(savedBudget, BudgetDTO.class);
    }

    
    public BudgetTrackingDTO trackBudget(String monthYear, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        Budget budget = budgetRepository.findByUserAndMonthYear(user, monthYear)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not set for " + monthYear));

        List<Expense> expenses = expenseRepository.findByUserAndMonthYear(user, monthYear);
        double totalExpenses = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        double remaining = budget.getAmount() - totalExpenses;

        BudgetTrackingDTO trackingDTO = new BudgetTrackingDTO();
        trackingDTO.setBudgetAmount(budget.getAmount());
        trackingDTO.setTotalExpenses(totalExpenses);
        trackingDTO.setRemaining(remaining);
        trackingDTO.setExceeded(remaining < 0);

        return trackingDTO;
    }
}
package com.example.demo.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ExpenseDTO;
import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CsvExportUtil csvExportUtil;

    
    public ExpenseDTO addExpense(ExpenseDTO expenseDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        Expense expense = modelMapper.map(expenseDTO, Expense.class);
        expense.setUser(user);
        Expense savedExpense = expenseRepository.save(expense);
        
        return modelMapper.map(savedExpense, ExpenseDTO.class);
    }

    public List<ExpenseDTO> getAllExpenses(String username, String monthYear) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        List<Expense> expenses;
        if (monthYear != null && !monthYear.isEmpty()) {
            expenses = expenseRepository.findByUserAndMonthYear(user, monthYear);
        } else {
            expenses = expenseRepository.findByUser(user);
        }

        return expenses.stream()
                .map(expense -> modelMapper.map(expense, ExpenseDTO.class))
                .collect(Collectors.toList());
    }

    public void exportToCsv(String username, HttpServletResponse response) throws IOException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        List<ExpenseDTO> expenses = getAllExpenses(username, null);
        csvExportUtil.exportToCsv(expenses, response);
    }
}
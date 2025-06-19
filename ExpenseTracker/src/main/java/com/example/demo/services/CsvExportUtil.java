package com.example.demo.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ExpenseDTO;

import jakarta.servlet.http.HttpServletResponse;

//CsvExportUtil.java
@Component
public class CsvExportUtil {
 
 public void exportToCsv(List<ExpenseDTO> expenses, HttpServletResponse response) throws IOException {
     response.setContentType("text/csv");
     response.setHeader("Content-Disposition", "attachment; filename=expenses.csv");
     
     try (PrintWriter writer = response.getWriter()) {
         // Write CSV header
         writer.println("Date,Category,Amount,Description");
         
         // Write data
         for (ExpenseDTO expense : expenses) {
             writer.println(String.format("\"%s\",\"%s\",%.2f,\"%s\"",
                 expense.getDate(),
                 expense.getCategory(),
                 expense.getAmount(),
                 expense.getDescription() != null ? expense.getDescription() : ""));
         }
     }
 }
}
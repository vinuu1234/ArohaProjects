package com.example.demo.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ExpenseDTO;

//FileStorageService.java
@Service
public class FileStorageService {
 
 @Value("${file.upload-dir}")
 private String uploadDir;
 
 public void saveExpensesToCsv(List<ExpenseDTO> expenses, String filename) {
     Path path = Paths.get(uploadDir).resolve(filename);
     
     try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
          CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                  .withHeader("Date", "Category", "Amount", "Description"))) {
         
         for (ExpenseDTO expense : expenses) {
             csvPrinter.printRecord(
                     expense.getDate(),
                     expense.getCategory(),
                     expense.getAmount(),
                     expense.getDescription());
         }
         
         csvPrinter.flush();
     } catch (IOException e) {
         throw new RuntimeException("Failed to save expenses to CSV", e);
     }
 }
 
 public List<ExpenseDTO> loadExpensesFromCsv(String filename) {
     Path path = Paths.get(uploadDir).resolve(filename);
     List<ExpenseDTO> expenses = new ArrayList();
     
     try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
          CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                  .withFirstRecordAsHeader()
                  .withIgnoreHeaderCase()
                  .withTrim())) {
         
         for (CSVRecord record : csvParser) {
             ExpenseDTO expense = new ExpenseDTO();
             expense.setDate(LocalDate.parse(record.get("Date")));
             expense.setCategory(record.get("Category"));
             //expense.setAmount(record.get("Amount")));
             expense.setAmount(Double.parseDouble(record.get("Amount")));
             expense.setDescription(record.get("Description"));
             expenses.add(expense);
         }
         
     } catch (IOException e) {
         throw new RuntimeException("Failed to load expenses from CSV", e);
     }
     
     return expenses;
 }
}
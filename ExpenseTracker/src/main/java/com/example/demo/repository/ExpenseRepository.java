package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Expense;
import com.example.demo.entity.User;

//ExpenseRepository.java
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
 List<Expense> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);
 
 @Query("SELECT e FROM Expense e WHERE e.user = :user AND FUNCTION('DATE_FORMAT', e.date, '%Y-%m') = :monthYear")
 List<Expense> findByUserAndMonthYear(@Param("user") User user, @Param("monthYear") String monthYear);

List<Expense> findByUser(User user);
}





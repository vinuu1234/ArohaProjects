package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Budget;
import com.example.demo.entity.User;

//BudgetRepository.java
public interface BudgetRepository extends JpaRepository<Budget, Long> {
	Optional<Budget> findByUserAndMonthYear(User user, String monthYear);
}

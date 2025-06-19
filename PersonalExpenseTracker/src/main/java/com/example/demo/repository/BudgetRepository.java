package com.example.demo.repository;

import java.time.YearMonth;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Budget;
@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByMonth(YearMonth month);


}

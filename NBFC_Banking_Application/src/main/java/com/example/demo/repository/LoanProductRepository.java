package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LoanProduct;

@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {

	 LoanProduct findFirstByLoanProductName(String loanType);

}

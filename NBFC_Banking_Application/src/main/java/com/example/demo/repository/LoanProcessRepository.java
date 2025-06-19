package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Loan;
import com.example.demo.entity.Loan.LoanStatus;
import com.example.demo.entity.LoanApplication;
@Repository
public interface LoanProcessRepository extends JpaRepository<Loan, Long>{

	List<Loan> findByStatus(LoanStatus status);
    boolean existsByLoanId(Long loanId);

    boolean existsByLoanApplication(LoanApplication loanApplication);
	boolean existsByLoanApplication_ApplicationId(Long applicationId);
	List<Loan> findByStatusAndStartDateBetween(LoanStatus status, LocalDate startDate, LocalDate endDate);
	List<Loan> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
	Loan findByLoanApplication_ApplicationId(Long applicationId);
	

}

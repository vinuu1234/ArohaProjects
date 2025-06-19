
package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.LoanApplication;
import com.example.demo.entity.Status;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

	List<LoanApplication> findByStatus(Status status);
	List<LoanApplication> findByCustomer_CustomerId(Long customerId);
	List<LoanApplication> findByApplicationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	
    //List<LoanApplication> findByLoanProduct_LoanProductId(Long loanProductId);
   // List<LoanApplication> findByCustomer_CustomerIdAndStatus(Long customerId, Status status);


	/*  
	 * @Query("SELECT la FROM LoanApplication la WHERE " +
	 * "(:status IS NULL OR la.status = :status) AND " +
	 * "(:customerId IS NULL OR la.customer.customerId = :customer_id) AND " +
	 * "(:loanProductId IS NULL OR la.loanProduct.loanProductId = :loan_type_Id)")
	 * List<LoanApplication> findWithFilters(
	 * 
	 * @Param("status") Status status,
	 * 
	 * @Param("customer_id") Long customerId,
	 * 
	 * @Param("loan_type_Id") Long loanProductId);
	 */
}

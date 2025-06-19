package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	boolean existsByAadharNumber(String aadharNumber);

	boolean existsByPanNumber(String panNumber);

	Customer findByEmail(String email);

	List<Customer> findByPhoneNumber(String phoneNumber);

}

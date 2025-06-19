package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.customerDto.CustomerRequestDto;
import com.example.demo.dto.customerDto.CustomerResponseDto;
import com.example.demo.dto.customerDto.updateCustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping("/register")
	public ResponseEntity<CustomerResponseDto> registerCustomer(@Valid @RequestBody CustomerRequestDto request) {
		CustomerResponseDto createdCustomer = customerService.registerCustomer(request);
		return ResponseEntity.ok(createdCustomer);
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
		List<CustomerResponseDto> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
		CustomerResponseDto customer = customerService.getCustomerById(id);
		return ResponseEntity.ok(customer);
	}
	
	@GetMapping("/byemail")
	public ResponseEntity<CustomerResponseDto> getCustomerByEmail(@RequestParam String email) {
		CustomerResponseDto customer = customerService.getCustomerByEmail(email);
		return ResponseEntity.ok(customer);
	}
	@GetMapping("/byPhoneNumber")
	public ResponseEntity<List<CustomerResponseDto>> getCustomerByPhoneNumber(@RequestParam String phoneNumber) {
		List<CustomerResponseDto> customer = customerService.getCustomerByPhoneNumber(phoneNumber);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long id, @RequestBody @Valid  updateCustomerDto updatedCustomer) {
		CustomerResponseDto customer = customerService.updateCustomer(id, updatedCustomer);
		return ResponseEntity.ok(customer);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deactivateCustomer(@PathVariable Long id) {
		customerService.deactivateCustomer(id);
		return ResponseEntity.ok("Customer account deactivated successfully.");
	}
	

	

}

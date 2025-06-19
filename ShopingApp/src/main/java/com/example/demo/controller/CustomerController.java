package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.customerdto.CustomerDto;
import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("api/Customer")
public class CustomerController {


	@Autowired
	private CustomerService customerService;

	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.addCustomer(customerDto));
	}
	@GetMapping("/getCustomer")
	public ResponseEntity<List<Customer>> getCustomerDetails() {
		return ResponseEntity.ok(customerService.getCustomerDeatails());
	}

}

package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.CustomerService;
import com.example.demo.entities.Customer;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
		Customer createdCustomer = customerService.registerCustomer(customer);
		return ResponseEntity.ok(createdCustomer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		Customer customer = customerService.getCustomerById(id);
		return ResponseEntity.ok(customer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
		Customer customer = customerService.updateCustomer(id, updatedCustomer);
		return ResponseEntity.ok(customer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deactivateCustomer(@PathVariable Long id) {
		customerService.deactivateCustomer(id);
		return ResponseEntity.ok("Customer account deactivated successfully.");
	}

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
	}


}

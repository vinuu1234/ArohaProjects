package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Repositories.CustomerRepository;
import com.example.demo.entities.Customer;

public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer registerCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer getCustomerById(Long id) {
	Customer customer= customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
	return customer;
	}

	public Customer updateCustomer(Long id, Customer updatedCustomer) {
		Customer customer= customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

		customer.setCustomerName(updatedCustomer.getCustomerName());
		customer.setCustomerAddress(updatedCustomer.getCustomerAddress());
		customer.setDob(updatedCustomer.getDob());
		customer.setEmail(updatedCustomer.getEmail());
		customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
		return customerRepository.save(customer);
	}

	public void deactivateCustomer(Long id) {
		 Customer customer = getCustomerById(id);
	        customer.setIsActive(false);
	        customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

}

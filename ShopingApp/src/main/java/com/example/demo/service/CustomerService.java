package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.customerdto.CustomerDto;
import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
    //Adding the customer data to the database
	public Customer addCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setGender(customerDto.getGender());
		customer.setPassword(customerDto.getPassword());
		customer.setCity(customerDto.getCity());
		customer.setState(customerDto.getState());
		customer.setCountry(customerDto.getCountry());
		customer.setPostalCode(customerDto.getPostalCode());

		try {
			return customerRepository.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;

	}
     //Gets the Records from the Repository
	public List<Customer> getCustomerDeatails() {
		return customerRepository.findAll();
	}

}

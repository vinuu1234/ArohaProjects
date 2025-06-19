package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.dto.customerDto.CustomerRequestDto;
import com.example.demo.dto.customerDto.CustomerResponseDto;
import com.example.demo.dto.customerDto.updateCustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	// Customer Onboarding
	public CustomerResponseDto registerCustomer(CustomerRequestDto request) {

		if (customerRepository.existsByEmail(request.getEmail())) {
			throw new DataIntegrityViolationException(request.getEmail() + "Already exists");
		}
		if (customerRepository.existsByPhoneNumber(request.getPhoneNumber())) {
			throw new DataIntegrityViolationException(request.getPhoneNumber() + " Data already exists: ");
		}

		if (customerRepository.existsByAadharNumber(request.getAadharNumber())) {
			throw new DataIntegrityViolationException(request.getAadharNumber() + " Data already exists: ");
		}
		if (customerRepository.existsByPanNumber(request.getPanNumber())) {
			throw new DataIntegrityViolationException(request.getPanNumber() + " Data already exists: ");
		}

		Customer customer = modelMapper.map(request, Customer.class);
		try {
			Customer savedCustomer = customerRepository.save(customer);
			return modelMapper.map(savedCustomer, CustomerResponseDto.class);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Duplicate Entry found");
		}

	}

	// Get customer by ID
	public CustomerResponseDto getCustomerById(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		return modelMapper.map(customer, CustomerResponseDto.class);
	}

	// Update customer
	public CustomerResponseDto updateCustomer(Long id, updateCustomerDto updatedCustomer) {
		Customer existingCustomer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		existingCustomer.setCustomerName(updatedCustomer.getCustomerName());
		existingCustomer.setDob(updatedCustomer.getDob());
		existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
		existingCustomer.setEmail(updatedCustomer.getEmail());
		LocalDateTime date = LocalDateTime.now();
		existingCustomer.setUpdatedAt(date);
		// existingCustomer.setCreditScore(updatedCustomer.getCreditScore());
		Customer savedCustomer = customerRepository.save(existingCustomer);

		return modelMapper.map(savedCustomer, CustomerResponseDto.class);
	}

	// Deactivate customer
	public void deactivateCustomer(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
		customer.setIsActive(false);
		customerRepository.save(customer);
	}

	// Get all customers
	public List<CustomerResponseDto> getAllCustomers() {
		try {
			List<Customer> customers = customerRepository.findAll();
			// return customers.stream().map(this::mapToDto).collect(Collectors.toList());
			return customers.stream().map(customer -> modelMapper.map(customers, CustomerResponseDto.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new CustomerNotFoundException("Failed to fetch Customer ");
		}

	}

	public CustomerResponseDto getCustomerByEmail(String email) {
		Customer customer = customerRepository.findByEmail(email);
		return modelMapper.map(customer, CustomerResponseDto.class);
	}

	public List<CustomerResponseDto> getCustomerByPhoneNumber(String phoneNumber) {
		List<Customer> customers = customerRepository.findByPhoneNumber(phoneNumber);
		// return modelMapper.map(customer, CustomerResponseDto.class);
		return customers.stream().map(Customer -> modelMapper.map(Customer, CustomerResponseDto.class))
				.collect(Collectors.toList());
	}

	/*
	 * private CustomerResponseDto mapToDto(Customer customer) { CustomerResponseDto
	 * dto = new CustomerResponseDto(); dto.setCustomerId(customer.getCustomerId());
	 * dto.setCustomerName(customer.getCustomerName());
	 * dto.setCustomerAddress(customer.getCustomerAddress());
	 * dto.setCreatedAt(customer.getCreatedAt()); dto.setEmail(customer.getEmail());
	 * dto.setDob(customer.getDob()); dto.setGender(customer.getGender());
	 * dto.setIsActive(customer.getIsActive());
	 * dto.setOccupation(customer.getOccupation());
	 * dto.setPhoneNumber(customer.getPhoneNumber());
	 * 
	 * return dto; }
	 */
}

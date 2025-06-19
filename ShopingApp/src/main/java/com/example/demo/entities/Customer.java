package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private String gender;
	private String street;
	private String city;
	private String state;
	private String postalCode;
	private String country;

}

package com.example.demo.dto.customerDto;

import java.time.LocalDate;

import com.example.demo.entity.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerRequestDto {
	//private Long customerId;
	@NotBlank
	private String customerName;
	@NotBlank
	private String customerAddress;
	@Email(message = "Invalid email address")
	@NotBlank
	private String email;
	@Pattern(regexp = "^[0-9]{10}$")
	private String phoneNumber;
	@Past
	private LocalDate dob;
	@Pattern(regexp = "^[0-9]{12}$" , message = "Aadhar number must be a 12-digit number")
	private String aadharNumber;
	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$")
	private String panNumber;
	@Min(300)
	@Max(900)
	private Integer creditScore;
	private Gender gender;
	private String occupation;
}
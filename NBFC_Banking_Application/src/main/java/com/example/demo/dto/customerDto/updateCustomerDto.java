package com.example.demo.dto.customerDto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class updateCustomerDto {
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


}

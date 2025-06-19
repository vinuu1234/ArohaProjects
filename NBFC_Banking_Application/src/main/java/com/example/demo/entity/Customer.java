package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;

	@NotBlank(message = "Customer name is required")
	private String customerName;

	@NotBlank(message = "Customer address is required")
	private String customerAddress;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	@Column(unique = true)
	private String email;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	@Column(unique = true)
	private String phoneNumber;

	@Past(message = "Date of birth must be in the past")
	private LocalDate dob;

	@Pattern(regexp = "^[0-9]{12}$")
	@Column(unique = true)
	private String aadharNumber;

	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$")
	@Column(unique = true)
	private String panNumber;
	@Min(300)
	@Max(900)
	private Integer creditScore;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	private String occupation;

	private Boolean isActive = true;

	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<LoanApplication> loanApplications;

	@OneToMany(mappedBy = "customer")
	private List<CustomerDocument> uploadDocuments;
	
	

}

package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;

	@ManyToOne
	@JoinColumn(name = "loan_id", nullable = false)
	private Loan loan;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@Column(name = "amount_paid", nullable = false)
	private double amountPaid;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_mode", columnDefinition = "ENUM('CASH','CARD','BANK_TRANSFER','UPI','WALLET')")
	private PaymentMode paymentMode;
	@Column(name = "receipt_number", unique = true, length = 50)
	private String receiptNumber;

	@Column(name = "transaction_reference", length = 100)
	private String transactionReference;

	@Column(name = "principal_component", nullable = false)
	private double principalComponent;

	@Column(name = "interest_component", nullable = false)
	private double interestComponent;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status", columnDefinition = "ENUM('Success','Failed','Pending') DEFAULT 'Success'")
	private PaymentStatus paymentStatus;

	@Column(name = "remarks", columnDefinition = "TEXT")
	private String remarks;

	@Column(name = "gateway_response", columnDefinition = "TEXT")
	private String gatewayResponse; // Raw response from payment gateway

	@Column(name = "is_disbursement")
	private boolean isDisbursement = false; // Flag for disbursement vs EMI payments

	public enum PaymentMode {
		CASH, CARD, BANK_TRANSFER, UPI, WALLET
	}

	public enum PaymentStatus {
		Success, Failed, Pending
	}
}
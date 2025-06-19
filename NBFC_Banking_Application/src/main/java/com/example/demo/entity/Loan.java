package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;
 
    private String loanAccountNumber;
    @OneToOne
    @JoinColumn(name = "application_id", nullable = false)
    private LoanApplication loanApplication;

    @Column(name = "disbursement_date", nullable = false)
    private LocalDate disbursementDate;

    @Column(name = "principal_amount", nullable = false)
    private Double approvedAmount;

    @Column(name = "tenure_months", nullable = false)
    private Integer tenureMonths;

    @Column(name = "emi_amount", nullable = false)
    private Double emiAmount;

    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LoanStatus status ;

    @Column(name = "remaining_balance")
    private Double remainingBalance;

    @Column(name = "last_payment_date")
    private LocalDate lastPaymentDate;

    @Column(name = "next_payment_date", nullable = false)
    private LocalDate nextPaymentDate;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @Column(name = "disbursement_transaction_id")
    private String disbursementTransactionId; // Gateway transaction ID

    @Enumerated(EnumType.STRING)
    @Column(name = "disbursement_status")
    private DisbursementStatus disbursementStatus = DisbursementStatus.PENDING;

    public enum DisbursementStatus {
        PENDING, PROCESSING, COMPLETED, FAILED
    }

	/*
	 * @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL) private
	 * List<Transaction> transactions;
	 */
    public enum LoanStatus {
        Disbursed,Active, Closed, Defaulted, Preclosed
    }
}
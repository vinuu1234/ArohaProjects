package com.example.demo.entities;

import java.math.BigDecimal;
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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(name = "payment_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(name = "amount_paid", nullable = false, precision = 12, scale = 2)
    private double amountPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode", nullable = false, columnDefinition = "ENUM('Cash','Cheque','Bank Transfer','UPI','Debit Card','Credit Card')")
    private PaymentMode paymentMode;

    @Column(name = "receipt_number", nullable = false, unique = true, length = 50)
    private String receiptNumber;

    @Column(name = "transaction_reference", length = 100)
    private String transactionReference;

    @Column(name = "principal_component", nullable = false, precision = 12, scale = 2)
    private double principalComponent;

    @Column(name = "interest_component", nullable = false, precision = 12, scale = 2)
    private double interestComponent;

      @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", columnDefinition = "ENUM('Success','Failed','Pending') DEFAULT 'Success'")
    private PaymentStatus paymentStatus = PaymentStatus.Success;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    // Getters and Setters
    public enum PaymentMode {
        Cash, Cheque, Bank_Transfer, UPI, Debit_Card, Credit_Card
    }

    public enum PaymentStatus {
        Success, Failed, Pending
    }
}
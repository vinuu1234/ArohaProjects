package com.example.demo.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "loan_types")
public class LoanType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_type_id")
    private Integer loanTypeId;

    @Column(name = "type_name", nullable = false, unique = true, length = 50)
    private String typeName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "min_amount", nullable = false, precision = 12, scale = 2)
    private Double minAmount;

    @Column(name = "max_amount", nullable = false, precision = 12, scale = 2)
    private Double maxAmount;

    @Column(name = "min_tenure_months", nullable = false)
    private Integer minTenureMonths;

    @Column(name = "max_tenure_months", nullable = false)
    private Integer maxTenureMonths;

    @Column(name = "base_interest_rate", nullable = false, precision = 5, scale = 2)
    private Double baseInterestRate;

    @OneToMany(mappedBy = "loanType", cascade = CascadeType.ALL)
    private List<LoanApplication> loanApplications;

    // Getters and Setters
}
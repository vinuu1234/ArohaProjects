package com.example.demo.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "aadhar_number")
    private String aadharNumber;

    @Column(name = "pan_number", unique = true)
    private String panNumber;

    @Column(name = "credit_score")
    private Integer creditScore ;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<LoanApplication> loanApplications;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<GroupMember> groupMemberships;

    public enum Gender {
        Male, Female, Other
    }
}
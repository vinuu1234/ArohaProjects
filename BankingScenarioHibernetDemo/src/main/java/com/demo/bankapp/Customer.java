package com.demo.bankapp;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custID")
    private int customerId;

    private String name;
    private String emailID;
    private String mobileNo;
    private char acctType;
    private LocalDate dateOfOpening;

    // Default constructor (required by Hibernate)
    public Customer() {
    }

    public Customer(String name, String emailID, String mobileNo, char acctType, LocalDate dateOfOpening) {
        this.name = name;
        this.emailID = emailID;
        this.mobileNo = mobileNo;
        this.acctType = acctType;
        this.dateOfOpening = dateOfOpening;
    }

    // Getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public char getAcctType() {
        return acctType;
    }

    public void setAcctType(char acctType) {
        this.acctType = acctType;
    }

    public LocalDate getDateOfOpening() {
        return dateOfOpening;
    }

    public void setDateOfOpening(LocalDate dateOfOpening) {
        this.dateOfOpening = dateOfOpening;
    }

    @Override
    public String toString() {
        return "CustomerID: " + customerId +
               ", Name: " + name +
               ", Email: " + emailID +
               ", Mobile: " + mobileNo +
               ", Account Type: " + acctType +
               ", Opened On: " + dateOfOpening;
    }
}


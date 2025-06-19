package com.example.demo.dto;

import com.example.demo.entity.Status;

import lombok.Data;

@Data
public class LoanApplicationStatusUpdateDTO {
   // private Long applicationId;
    private Status status;
    
}
package com.example.demo.dto;

import com.example.demo.entity.VerificationStatus;

import lombok.Data;

@Data
public class DocumentVerificationDTO {
    private VerificationStatus status;
    private String remarks;
}

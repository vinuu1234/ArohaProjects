package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.DocumentType;
import com.example.demo.entity.VerificationStatus;

import lombok.Data;

@Data
public class DocumentResponseDTO {
    private Long id;
    private Long customerId;
    private DocumentType documentType;
    private String documentNumber;
    private String filePath;
    private LocalDateTime uploadedAt;
    private VerificationStatus status;
    private String remarks;
}
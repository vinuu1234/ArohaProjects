package com.example.demo.dto;

import com.example.demo.entity.DocumentType;

import lombok.Data;

@Data
public class DocumentUploadDTO {
    private DocumentType documentType;
    private String documentNumber; // Optional
}

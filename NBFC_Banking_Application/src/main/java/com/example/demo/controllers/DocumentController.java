package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.DocumentResponseDTO;
import com.example.demo.dto.DocumentUploadDTO;
import com.example.demo.dto.DocumentVerificationDTO;
import com.example.demo.entity.CustomerDocument;
import com.example.demo.entity.DocumentType;
import com.example.demo.services.DocumentService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

	@Autowired
    private DocumentService documentService;

   
    @PostMapping
    public ResponseEntity<CustomerDocument> uploadDocument(
            @RequestParam Long customerId,
            @RequestPart MultipartFile file,
            @RequestParam(required = false) DocumentType documentType) throws IOException {
        
    	CustomerDocument document = documentService.uploadDocument(customerId, file, documentType);
        return ResponseEntity.ok(document);
    }
	/*
	 * // Upload document
	 * 
	 * @PostMapping public ResponseEntity<DocumentResponseDTO> uploadDocument(
	 * 
	 * @PathVariable Long customerId,
	 * 
	 * @RequestPart("file") MultipartFile file) { DocumentResponseDTO response =
	 * documentService.uploadDocument(customerId, file); return new
	 * ResponseEntity<>(response, HttpStatus.CREATED); }
	 */

    // Get all documents for a customer
    @GetMapping("getAll/{customerId}")
    public ResponseEntity<List<DocumentResponseDTO>> getCustomerDocuments(@PathVariable Long customerId) {
        List<DocumentResponseDTO> documents = documentService.getCustomerDocuments(customerId);
        return ResponseEntity.ok(documents);
    }

    // Get document by ID
    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentResponseDTO> getDocumentById(
            @PathVariable Long customerId,
            @PathVariable Long documentId) {
        DocumentResponseDTO document = documentService.getDocumentById(customerId, documentId);
        return ResponseEntity.ok(document);
    }

    // Update document verification status
    @PutMapping("/{documentId}/verify")
    public ResponseEntity<DocumentResponseDTO> verifyDocument(
            @PathVariable Long customerId,
            @PathVariable Long documentId,
            @RequestBody DocumentVerificationDTO verificationDTO) {
        DocumentResponseDTO response = documentService.verifyDocument(customerId, documentId, verificationDTO);
        return ResponseEntity.ok(response);
    }

    // Delete document
    @DeleteMapping("/{documentId}")
    public ResponseEntity<Void> deleteDocument(
            @PathVariable Long customerId,
            @PathVariable Long documentId) {
        documentService.deleteDocument(customerId, documentId);
        return ResponseEntity.noContent().build();
    }

    // Get documents by type
    @GetMapping("/type/{documentType}")
    public ResponseEntity<List<DocumentResponseDTO>> getDocumentsByType(
            @PathVariable Long customerId,
            @PathVariable String documentType) {
        List<DocumentResponseDTO> documents = documentService.getDocumentsByType(customerId, documentType);
        return ResponseEntity.ok(documents);
    }
}
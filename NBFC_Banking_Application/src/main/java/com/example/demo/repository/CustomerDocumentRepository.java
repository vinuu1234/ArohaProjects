package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerDocument;

import com.example.demo.entity.DocumentType;
import com.example.demo.entity.VerificationStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerDocumentRepository extends JpaRepository<CustomerDocument, Long> {

	// Find all documents for a customer
	List<CustomerDocument> findByCustomerCustomerId(Long customerId);

	// Find documents by type for a customer
	List<CustomerDocument> findByCustomerCustomerIdAndDocumentType(Long customerId, DocumentType documentType);

	// Find documents by verification status for a customer
	List<CustomerDocument> findByCustomerCustomerIdAndStatus(Long customerId, VerificationStatus status);

	// Count documents by type and status for a customer
	long countByCustomerCustomerIdAndDocumentTypeAndStatus(Long customerId, DocumentType documentType,
			VerificationStatus status);

	// Update verification status for a document
	@Modifying
	@Query("UPDATE CustomerDocument d SET d.status = ?3, d.remarks = ?4 WHERE d.id = ?1 AND d.customer.customerId = ?2")
	int updateVerificationStatus(Long documentId, Long customerId, VerificationStatus status, String remarks);

	// Check if document exists for customer
	boolean existsByIdAndCustomerCustomerId(Long documentId, Long customerId);

	// Find all verified documents for a customer
	@Query("SELECT d FROM CustomerDocument d WHERE d.customer.customerId = ?1 AND d.status = 'VERIFIED'")
	List<CustomerDocument> findAllVerifiedDocuments(Long customerId);

	// Find documents uploaded after a certain date
	List<CustomerDocument> findByCustomerCustomerIdAndUploadedAtAfter(Long customerId, LocalDateTime date);
}
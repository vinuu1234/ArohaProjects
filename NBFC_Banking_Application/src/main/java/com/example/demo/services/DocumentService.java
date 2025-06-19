package com.example.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dto.DocumentResponseDTO;
import com.example.demo.dto.DocumentUploadDTO;
import com.example.demo.dto.DocumentVerificationDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerDocument;
import com.example.demo.entity.DocumentType;
import com.example.demo.entity.VerificationStatus;
import com.example.demo.repository.CustomerDocumentRepository;
import com.example.demo.repository.CustomerRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {

	@Autowired
	private CustomerDocumentRepository documentRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Value("${file.upload-dir}")
	private String uploadDir;

	public CustomerDocument uploadDocument(Long customerId, MultipartFile file, DocumentType documentType)
			throws IOException {
		// Now customerRepository will be properly injected
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

		// Document processing logic
		CustomerDocument document = new CustomerDocument();
		document.setCustomer(customer);
		document.setDocumentType(documentType);
		document.setFilePath(generateFilePath(file)); // Store path
		document.setFileData(file.getBytes());
		document.setRemarks("All documents correct");
		return documentRepository.save(document);
	}

	private String generateFilePath(MultipartFile file) {
		return "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
	}

	/*
	 * public DocumentResponseDTO uploadDocument(Long customerId, MultipartFile
	 * file) { // Validate customer exists
	 * 
	 * Customer customer = customerRepository.findById(customerId) .orElseThrow(()
	 * -> new CustomerNotFoundException("Customer not found"));
	 * 
	 * 
	 * Customer customer = customerRepository.findById(customerId) .orElseThrow(()
	 * -> new ResourceNotFoundException("Customer not found with id: " +
	 * customerId));
	 * 
	 * // Validate file if (file.isEmpty()) { throw new
	 * IllegalArgumentException("File is empty"); }
	 * 
	 * try { // Create upload directory if it doesn't exist Path uploadPath =
	 * Paths.get(uploadDir); if (!Files.exists(uploadPath)) {
	 * Files.createDirectories(uploadPath); }
	 * 
	 * // Generate unique filename String originalFilename =
	 * file.getOriginalFilename(); String fileExtension =
	 * originalFilename.substring(originalFilename.lastIndexOf(".")); String
	 * uniqueFilename = UUID.randomUUID().toString() + fileExtension; Path filePath
	 * = uploadPath.resolve(uniqueFilename);
	 * 
	 * // Save file to disk Files.copy(file.getInputStream(), filePath,
	 * StandardCopyOption.REPLACE_EXISTING);
	 * 
	 * // Create and save document entity CustomerDocument document = new
	 * CustomerDocument(); document.setCustomer(customer); //
	 * document.setDocumentType(documentUploadDTO.getDocumentType()); //
	 * document.setDocumentNumber(documentUploadDTO.getDocumentNumber());
	 * document.setFilePath(filePath.toString());
	 * document.setUploadedAt(LocalDateTime.now());
	 * document.setStatus(VerificationStatus.PENDING);
	 * 
	 * CustomerDocument savedDocument = documentRepository.save(document);
	 * 
	 * // Convert to DTO and return return convertToDto(savedDocument);
	 * 
	 * } catch (IOException ex) { throw new RuntimeException("Failed to store file",
	 * ex); } }
	 */
	public List<DocumentResponseDTO> getCustomerDocuments(Long customerId) {
		// Validate customer exists
		if (!customerRepository.existsById(customerId)) {
			throw new ResourceNotFoundException("Customer not found with id: " + customerId);
		}

		List<CustomerDocument> documents = documentRepository.findByCustomerCustomerId(customerId);
		return documents.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public DocumentResponseDTO getDocumentById(Long customerId, Long documentId) {
		CustomerDocument document = documentRepository.findById(documentId)
				.orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + documentId));

		// Verify document belongs to customer
		if (!document.getCustomer().getCustomerId().equals(customerId)) {
			throw new ResourceNotFoundException("Document not found for customer with id: " + customerId);
		}

		return convertToDto(document);
	}

	public DocumentResponseDTO verifyDocument(Long customerId, Long documentId,
			DocumentVerificationDTO verificationDTO) {
		CustomerDocument document = documentRepository.findById(documentId)
				.orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + documentId));

		// Verify document belongs to customer
		if (!document.getCustomer().getCustomerId().equals(customerId)) {
			throw new ResourceNotFoundException("Document not found for customer with id: " + customerId);
		}

		// Update verification status
		document.setStatus(verificationDTO.getStatus());
		document.setRemarks(verificationDTO.getRemarks());

		CustomerDocument updatedDocument = documentRepository.save(document);

		return convertToDto(updatedDocument);
	}

	public void deleteDocument(Long customerId, Long documentId) {
		CustomerDocument document = documentRepository.findById(documentId)
				.orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + documentId));

		// Verify document belongs to customer
		if (!document.getCustomer().getCustomerId().equals(customerId)) {
			throw new ResourceNotFoundException("Document not found for customer with id: " + customerId);
		}

		try {
			// Delete file from filesystem
			Files.deleteIfExists(Paths.get(document.getFilePath()));

			// Delete from database
			documentRepository.delete(document);
		} catch (IOException ex) {
			throw new RuntimeException("Failed to delete document file", ex);
		}
	}

	public List<DocumentResponseDTO> getDocumentsByType(Long customerId, String documentType) {
		// Validate customer exists
		if (!customerRepository.existsById(customerId)) {
			throw new ResourceNotFoundException("Customer not found with id: " + customerId);
		}

		// Convert string to enum
		DocumentType type;
		try {
			type = DocumentType.valueOf(documentType.toUpperCase());
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Invalid document type: " + documentType);
		}

		List<CustomerDocument> documents = documentRepository.findByCustomerCustomerIdAndDocumentType(customerId, type);
		return documents.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private DocumentResponseDTO convertToDto(CustomerDocument document) {

		DocumentResponseDTO dto = modelMapper.map(document, DocumentResponseDTO.class);
		/*
		 * DocumentResponseDTO dto = new DocumentResponseDTO();
		 * dto.setId(document.getId());
		 * dto.setCustomerId(document.getCustomer().getCustomerId());
		 * dto.setDocumentType(document.getDocumentType());
		 * //dto.setDocumentNumber(document.getDocumentNumber());
		 * dto.setFilePath(document.getFilePath());
		 * dto.setUploadedAt(document.getUploadedAt());
		 * dto.setStatus(document.getStatus()); dto.setRemarks(document.getRemarks());
		 */
		return dto;
	}
}
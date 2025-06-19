package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dto.LoanProcessDTO.LoanCreationDTO;
import com.example.demo.dto.LoanProcessDTO.LoanDisburseResponse;
import com.example.demo.dto.LoanProcessDTO.LoanResponseDTO;
import com.example.demo.dto.LoanProcessDTO.LoanUpdateDTO;
import com.example.demo.entity.Loan;
import com.example.demo.entity.Loan.LoanStatus;
import com.example.demo.entity.Payment;
import com.example.demo.services.LoanProcessService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loanProcess")
public class LoanProcessController {

	@Autowired
	private LoanProcessService loanProcessService;

	@PostMapping("/createloan")
	public ResponseEntity<LoanResponseDTO> createApprovedLoan(@RequestParam Long applicationId) {
		LoanResponseDTO dto = loanProcessService.createApprovedLoan(applicationId);

		return ResponseEntity.ok(dto);
	}

	@GetMapping("/getAllLoans")
	public ResponseEntity<List<LoanResponseDTO>> getAllLoans() {
		List<LoanResponseDTO> dto = loanProcessService.getAllLoans();
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping("/getLoan/{id}")
	public ResponseEntity<LoanResponseDTO> getLoanById(@PathVariable(value = "id") Long loanId)
			throws ResourceNotFoundException {
		LoanResponseDTO loan = loanProcessService.getLoanById(loanId);
		return ResponseEntity.ok().body(loan);
	}

	@GetMapping("/byFilter")
	public ResponseEntity<List<LoanResponseDTO>> getAllLoans(@RequestParam(required = false) LoanStatus status,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		List<LoanResponseDTO> response = loanProcessService.getAllLoans(status, startDate, endDate);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<LoanResponseDTO>> getLoansByStatus(@PathVariable Loan.LoanStatus status) {
		List<LoanResponseDTO> loan = loanProcessService.getLoansByStatus(status);
		return ResponseEntity.ok().body(loan);

	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<LoanResponseDTO> updateLoan(@PathVariable Long id,
			@Valid @RequestBody LoanUpdateDTO updateDTO) {
		return ResponseEntity.ok(loanProcessService.updateLoan(id, updateDTO));
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<LoanResponseDTO> updateLoanStatus(@PathVariable Long id, @RequestParam Loan.LoanStatus status)
			throws ResourceNotFoundException {
		LoanResponseDTO response = loanProcessService.updateLoanStatus(id, status);
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/application/{applicationId}")
	public ResponseEntity<LoanResponseDTO> getLoansByApplicationId(@PathVariable Long applicationId) {
		LoanResponseDTO response = loanProcessService.getLoansByApplicationId(applicationId);
		return ResponseEntity.ok().body(response);
	}


	@PostMapping("/{loanId}/initiate-disbursement")
	public ResponseEntity<LoanDisburseResponse> initiateDisbursement(@PathVariable Long loanId) {
		LoanDisburseResponse response = loanProcessService.initiateDisbursement(loanId);
		return ResponseEntity.ok(response);
	}


	
}


package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.example.demo.NbfcBankingApplication;
import com.example.demo.Exceptions.ApplicationNotFoundException;
import com.example.demo.dto.LoanApplicationResponseDTO;
import com.example.demo.dto.LoanApplicationStatusUpdateDTO;
import com.example.demo.dto.LoanApplicationUpdateDTO;
import com.example.demo.dto.EMICalculationResult;
import com.example.demo.dto.LoanApplicationRequestDto;
import com.example.demo.dto.LoanResponseDto;
import com.example.demo.entity.LoanApplication;
import com.example.demo.entity.LoanProduct;
import com.example.demo.entity.Status;
import com.example.demo.services.EMICalculatorService;
import com.example.demo.services.LoanApplicationServices;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/loanApplication")
public class LoanApplicationController {

	@Autowired
	private LoanApplicationServices loanApplicationServices;
	@Autowired
	private EMICalculatorService emiCalculatorService;

	@PostMapping("/calculate-emi")
	public ResponseEntity<EMICalculationResult> calculateEMI(@RequestParam String productName,
			@RequestParam Double amoutRequested, @RequestParam Integer tenureRequested) {
		
		EMICalculationResult emi=emiCalculatorService.calculateEMI(productName,amoutRequested,tenureRequested);
		//return ResponseEntity.status(HttpStatus.CREATED).body(emi);
		return ResponseEntity.status(HttpStatus.CREATED).body(emi);

	}

	@PostMapping("/register")
	public ResponseEntity<LoanApplicationResponseDTO> createLoanApplication(
			@RequestBody LoanApplicationRequestDto loanRequestDto) {
		LoanApplicationResponseDTO created = loanApplicationServices.createLoanApplication(loanRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping("/getApplications")
	public ResponseEntity<List<LoanApplicationResponseDTO>> getApplications() {
		return ResponseEntity.ok(loanApplicationServices.getApplications());
	}

	@GetMapping("/getApplications/{laid}")
	public ResponseEntity<LoanResponseDto> getApplicationsById(@PathVariable Long laid) {
		return ResponseEntity.ok(loanApplicationServices.getApplicationsById(laid));
	}

	@GetMapping("/getByCustomerId/{cid}")
	public ResponseEntity<List<LoanResponseDto>> getApplicationByCustomerId(@PathVariable Long cid) {

		return ResponseEntity.ok(loanApplicationServices.getApplicationByCustomerId(cid));
	}

	@GetMapping("/status")
	public ResponseEntity<List<LoanApplicationResponseDTO>> getLoanApplicationsByStatus(@RequestParam Status status) {
		List<LoanApplicationResponseDTO> responses = loanApplicationServices.getLoanApplicationsByStatus(status);
		return ResponseEntity.ok(responses);
	}

	@PatchMapping("/status/{applicationId}")
	public ResponseEntity<LoanApplicationResponseDTO> updateLoanApplicationStatus(@PathVariable Long applicationId,
			@RequestBody LoanApplicationStatusUpdateDTO statusUpdateDTO) {
		LoanApplicationResponseDTO response = loanApplicationServices.updateLoanApplicationStatus(applicationId,
				statusUpdateDTO);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/edit/{applicationId}")
	public ResponseEntity<LoanApplicationResponseDTO> updateLoanApplication(@PathVariable Long applicationId,
			@RequestBody @Valid LoanApplicationUpdateDTO loanApplicationUpdateDTO) {
		LoanApplicationResponseDTO response = loanApplicationServices.updateLoanApplication(applicationId,
				loanApplicationUpdateDTO);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{applicationId}")
	public ResponseEntity<Void> deleteLoanApplication(@PathVariable Long applicationId) {
		loanApplicationServices.deleteLoanApplication(applicationId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/by-date-range")
	public ResponseEntity<List<LoanApplicationResponseDTO>> getApplicationsByDateRange(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

		// Default to last 30 days if no dates provided
		if (startDate == null) {
			startDate = LocalDate.now().minusDays(30);
		}
		if (endDate == null) {
			endDate = LocalDate.now();
		}

		LocalDateTime startDateTime = startDate.atStartOfDay();
		LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

		List<LoanApplicationResponseDTO> applications = loanApplicationServices
				.getApplicationsByDateRange(startDateTime, endDateTime);
		return ResponseEntity.ok(applications);
	}

	/*
	 * @GetMapping public ResponseEntity<List<LoanResponseDto>>
	 * getAllLoanApplications(@RequestParam(required = false) Status status,
	 * 
	 * @RequestParam(required = false) Long customerId, @RequestParam(required =
	 * false) Long loanProductId) { List<LoanResponseDto> applications =
	 * loanApplicationServices.getAllLoanApplications(status, customerId,
	 * loanProductId); return ResponseEntity.ok(applications); }
	 */
}

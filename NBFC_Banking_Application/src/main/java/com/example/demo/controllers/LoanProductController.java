package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exceptions.InvalidLoanTypeException;
import com.example.demo.Exceptions.LoanProductNotFoundException;
import com.example.demo.dto.loanproductDto.LoanProductRequestDto;
import com.example.demo.dto.loanproductDto.LoanProductResponseDto;
import com.example.demo.entity.LoanProduct;
import com.example.demo.services.LoanProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loanproduct")
public class LoanProductController {
	@Autowired
	private LoanProductService loanProductService;

	@PostMapping("/addloanproduct")
	public ResponseEntity<LoanProductResponseDto> addLoanProduct(
			@RequestBody @Valid LoanProductRequestDto loanProductDto) {
		return ResponseEntity.ok(loanProductService.addLoanProduct(loanProductDto));

	}

	@GetMapping("/getLoanProducts")
	public ResponseEntity<List<LoanProductResponseDto>> getLoanProducts() {
		return ResponseEntity.ok(loanProductService.getLoanProducts());
	}

	@GetMapping("/getLoanProductById/{loanProductId}")
	public ResponseEntity<LoanProductResponseDto> getLoanProductById(@PathVariable("loanProductId") Long loanProductId) {
		return ResponseEntity.ok(loanProductService.getLoanProductById(loanProductId));
	}

	@GetMapping("/getLoanProductsByname")
	public ResponseEntity<LoanProductResponseDto> getLoanProductByName(
			@RequestParam(required = true) String loanType) {

		try {
			LoanProductResponseDto product = loanProductService.getLoanProductByName(loanType);
			return ResponseEntity.ok(product);
		} catch (InvalidLoanTypeException e) {
			throw e;
		} catch (LoanProductNotFoundException e) {
			throw e;
		}
	}

	@PutMapping("/updateLoanProducts/{lid}")
	public ResponseEntity<LoanProductResponseDto> updateLoanProducts(@PathVariable("lid") Long lid,
			@RequestBody @Valid LoanProductRequestDto updatedLoanProduct) {
		return ResponseEntity.ok(loanProductService.updateLoanProducts(lid, updatedLoanProduct));
	}

	@DeleteMapping("/deleteproduct/{lid}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long lid) {
		loanProductService.deleteProduct(lid);
		return ResponseEntity.ok("Loan Product deactivated successfully.");
	}

}

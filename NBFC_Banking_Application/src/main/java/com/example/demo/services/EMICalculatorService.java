package com.example.demo.services;

import java.util.Optional;
import com.example.demo.repository.LoanApplicationRepository;
import com.example.demo.repository.LoanProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dto.EMICalculationResult;
import com.example.demo.entity.LoanProduct;

@Service
public class EMICalculatorService {
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	@Autowired
	private LoanProductRepository loanProductRepository;

	public EMICalculationResult calculateEMI(String productName, Double amountRequested, Integer tenureRequested) {
		LoanProduct product = loanProductRepository.findFirstByLoanProductName(productName);
		if(product==null) {
			throw new ResourceNotFoundException("This Loan Product Not found !!!!");
		}

		if (amountRequested > product.getMaxAmount() || amountRequested < product.getMinAmount()) {
			throw new IllegalArgumentException("Requested amount Not In the range");
		}
		if (tenureRequested > product.getMaxTenureMonths()
				|| tenureRequested < product.getMinTenureMonths()) {
			throw new IllegalArgumentException("Requested Tenure Not In the range");
		}

		if (product==null) {
			throw new IllegalArgumentException("Loan product not found: " + productName);
		}

		Double interestRate = product.getBaseInterestRate();

		// Validate inputs
		if (amountRequested == null || interestRate == null || tenureRequested == null) {
			throw new IllegalArgumentException("Parameters cannot be null");
		}
		if (amountRequested <= 0 || interestRate <= 0 || tenureRequested <= 0) {
			throw new IllegalArgumentException("Principal amount, interest rate, and tenure must be positive values");
		}

		// Convert annual rate to monthly and percentage to decimal
		double monthlyInterestRate = interestRate / 12 / 100;

		// Calculate EMI using standard formula
		double temp = Math.pow(1 + monthlyInterestRate, tenureRequested);
		double emi = (amountRequested * monthlyInterestRate * temp) / (temp - 1);

		// Calculate total payment and total interest
		double totalPayment = emi * tenureRequested;
		double totalInterest = totalPayment - amountRequested;

		return new EMICalculationResult(roundToTwoDecimals(emi), roundToTwoDecimals(totalPayment),
				roundToTwoDecimals(totalInterest), tenureRequested);
	}

	private double roundToTwoDecimals(double value) {
		return Math.round(value * 100.0) / 100.0;
	}
}
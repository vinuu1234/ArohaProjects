package com.example.demo.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.example.demo.Exceptions.BusinessException;
import com.example.demo.Exceptions.LoanAlreadyExistsException;
import com.example.demo.Exceptions.PaymentNotCompletedException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dto.EMICalculationResult;
import com.example.demo.dto.LoanProcessDTO.LoanCreationDTO;
import com.example.demo.dto.LoanProcessDTO.LoanDisburseResponse;
import com.example.demo.dto.LoanProcessDTO.LoanResponseDTO;
import com.example.demo.dto.LoanProcessDTO.LoanUpdateDTO;
import com.example.demo.entity.Loan;
import com.example.demo.entity.Loan.LoanStatus;
import com.example.demo.entity.LoanApplication;
import com.example.demo.entity.Payment;
import com.example.demo.entity.Payment.PaymentMode;
import com.example.demo.entity.Status;
import com.example.demo.repository.LoanApplicationRepository;
import com.example.demo.repository.LoanProcessRepository;
import com.example.demo.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.validation.Valid;

@Service
public class LoanProcessService {

	@Autowired
	private LoanProcessRepository loanProcessRepository;
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	@Autowired
	private LoanApplicationServices loanApplicationServices;
	@Autowired
	private EMICalculatorService emiCalculatorService;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RazorpayClient razorpayClient;

	public LoanResponseDTO createApprovedLoan(Long applicationId) {

		if (loanProcessRepository.existsByLoanApplication_ApplicationId(applicationId)) {
			throw new BusinessException("A loan already exists for this application");
		}

		LoanApplication application = loanApplicationRepository.findById(applicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Application not found"));

		if (!application.getStatus().equals(Status.Approved)) {
			throw new BusinessException("Only approved applications can be converted to loans");
		}

		Loan loan = new Loan();

		loan.setLoanApplication(application);
		LocalDate disbursedDate = LocalDate.now();
		loan.setDisbursementDate(disbursedDate);
		loan.setApprovedAmount(application.getRequestedAmount());
		loan.setInterestRate(application.getLoanProduct().getBaseInterestRate());
		loan.setTenureMonths(application.getRequestedTenureMonths());
		loan.setStartDate(loan.getDisbursementDate());
		/*
		 * LocalDate startDate = calculateStartDate(loan.getDisbursementDate());
		 * loan.setStartDate(startDate);
		 */
		loan.setEndDate(loan.getStartDate().plusMonths(application.getRequestedTenureMonths()));

		EMICalculationResult emi = emiCalculatorService.calculateEMI(application.getLoanProduct().getLoanProductName(),
				application.getRequestedAmount(), application.getRequestedTenureMonths());
		loan.setEmiAmount(emi.getMonthlyEMI());
		loan.setNextPaymentDate(loan.getStartDate().plusMonths(1));
		loan.setStatus(Loan.LoanStatus.Disbursed);
		loan.setRemainingBalance(loan.getApprovedAmount());
		String accountNumber = "LN-" + loan.getDisbursementDate().getYear() + "-" + application.getApplicationId();
		loan.setLoanAccountNumber(accountNumber);

		Loan savedLoan = loanProcessRepository.save(loan);
		// return mapToResponseDTO(savedLoan);
		return modelMapper.map(savedLoan, LoanResponseDTO.class);
	}

	
	public LoanResponseDTO getLoanById(Long loanId) {
		Loan loan = loanProcessRepository.findById(loanId)
				.orElseThrow(() -> new ResourceNotFoundException("Loan not found for this id " + loanId));

		return modelMapper.map(loan, LoanResponseDTO.class);

	}

	public List<LoanResponseDTO> getAllLoans() {
		List<Loan> loans = loanProcessRepository.findAll();

		if (loans == null || loans.isEmpty()) {
			throw new ResourceNotFoundException("Loans Not Found !!!");
		}
		return loans.stream().map(loan -> modelMapper.map(loan, LoanResponseDTO.class)).collect(Collectors.toList());
	}

	public List<LoanResponseDTO> getAllLoans(LoanStatus status, LocalDate startDate, LocalDate endDate) {
		if (status != null && startDate != null && endDate != null) {
			List<Loan> loans = loanProcessRepository.findByStatusAndStartDateBetween(status, startDate, endDate);
			if (loans == null || loans.isEmpty()) {
				throw new ResourceNotFoundException("Loans Not Found !!!");
			}
			return loans.stream().map(loan -> modelMapper.map(loan, LoanResponseDTO.class))
					.collect(Collectors.toList());
		} else if (status != null) {
			List<Loan> loans = loanProcessRepository.findByStatus(status);
			if (loans == null || loans.isEmpty()) {
				throw new ResourceNotFoundException("Loans Not Found !!!");
			}
			return loans.stream().map(loan -> modelMapper.map(loan, LoanResponseDTO.class))
					.collect(Collectors.toList());
		} else if (startDate != null && endDate != null) {
			List<Loan> loans = loanProcessRepository.findByStartDateBetween(startDate, endDate);
			if (loans == null || loans.isEmpty()) {
				throw new ResourceNotFoundException("Loans Not Found !!!");
			}
			return loans.stream().map(loan -> modelMapper.map(loan, LoanResponseDTO.class))
					.collect(Collectors.toList());

		}
		List<Loan> loans = loanProcessRepository.findAll();
		if (loans == null || loans.isEmpty()) {
			throw new ResourceNotFoundException("Loans Not Found !!!");
		}
		return loans.stream().map(loan -> modelMapper.map(loan, LoanResponseDTO.class)).collect(Collectors.toList());
	}

	public LoanResponseDTO updateLoan(Long id, LoanUpdateDTO updateDTO) {
		Loan loan = loanProcessRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Loan not found !!!!"));

		if (updateDTO.getApprovedAmount() != null) {
			loan.setApprovedAmount(updateDTO.getApprovedAmount());
		}
		if (updateDTO.getTenureMonths() != null) {
			loan.setTenureMonths(updateDTO.getTenureMonths());
			// Recalculate EMI and end date
			loan.setEndDate(loan.getStartDate().plusMonths(updateDTO.getTenureMonths()));
			loan.setEmiAmount(calculateEMI(loan));
		}
		if (updateDTO.getInterestRate() != null) {
			loan.setInterestRate(updateDTO.getInterestRate());
			loan.setEmiAmount(calculateEMI(loan));
		}
		if (updateDTO.getNextPaymentDate() != null) {
			loan.setNextPaymentDate(updateDTO.getNextPaymentDate());
		}

		Loan updatedLoan = loanProcessRepository.save(loan);
		return modelMapper.map(updatedLoan, LoanResponseDTO.class);
	}

	private Double calculateEMI(Loan loan) {
		// P × r × (1+r)^n / ((1+r)^n−1)
		double monthlyRate = loan.getInterestRate() / 100 / 12;
		return (loan.getApprovedAmount() * monthlyRate * Math.pow(1 + monthlyRate, loan.getTenureMonths()))
				/ (Math.pow(1 + monthlyRate, loan.getTenureMonths()) - 1);
	}

	public List<LoanResponseDTO> getLoansByStatus(LoanStatus status) {

		List<Loan> loans = loanProcessRepository.findByStatus(status);

		return loans.stream().map(loan -> modelMapper.map(loan, LoanResponseDTO.class)).collect(Collectors.toList());
	}

	public LoanResponseDTO updateLoanStatus(Long id, LoanStatus status) {
		Loan loan = loanProcessRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Loans not found for this :" + id));
		// loan.setStatus(status);
		if (loan.getRemainingBalance() == 0) {
			loan.setStatus(status);

		}

		if (loan.getRemainingBalance() == 0 && LocalDate.now().isBefore(loan.getEndDate())) {
			loan.setStatus(status);
		}

		LocalDate dueDate = loan.getNextPaymentDate(); // Assume this is stored
		long daysOverdue = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
		if (daysOverdue > 90) {
			loan.setStatus(status);
		}

		System.out.println("Loan not completed");

		return modelMapper.map(loan, LoanResponseDTO.class);
	}

	public LoanResponseDTO getLoansByApplicationId(Long applicationId) {
		Loan loan = loanProcessRepository.findByLoanApplication_ApplicationId(applicationId);
		if (loan == null) {
			throw new ResourceNotFoundException("Loans not found for this :" + applicationId);
		}
		return modelMapper.map(loan, LoanResponseDTO.class);
	}

	public LoanDisburseResponse initiateDisbursement(Long loanId) {
        Loan loan = loanProcessRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        // Validate loan is ready for disbursement
        if (!loan.getLoanApplication().getStatus().equals(loan.getLoanApplication().getStatus().Approved)) {
            throw new IllegalStateException("Loan must be APPROVED before disbursement");
        }

        try {
            // 1. Create payout in Razorpay
            JSONObject payoutRequest = new JSONObject();
            //payoutRequest.put("account_number", loan.getLoanApplication().getCustomer().getAadharNumber());
            payoutRequest.put("amount", loan.getApprovedAmount() * 100); // in paise
            payoutRequest.put("currency", "INR");
            //payoutRequest.put("reference_id", "loan_" + loanId);

            Order payout = razorpayClient.orders.create(payoutRequest); 

            // 2. Save payment record
            Payment payment = new Payment();
            payment.setLoan(loan);
            payment.setAmountPaid(loan.getApprovedAmount());
            payment.setPaymentMode(PaymentMode.UPI);
            payment.setAmountPaid(loan.getApprovedAmount());
            payment.setPaymentDate(LocalDateTime.now());
            payment.setReceiptNumber("LN-RCPT-2023-0042");
            payment.setTransactionReference(payout.get("id"));
            payment.setDisbursement(true);
            paymentRepository.save(payment);

            // 3. Update loan status
            loan.setDisbursementStatus(Loan.DisbursementStatus.PROCESSING);
            loan.setDisbursementTransactionId(payout.get("id"));
            loanProcessRepository.save(loan);

            return new LoanDisburseResponse(
                loanId,
                loan.getApprovedAmount(),
                payout.get("id"),
                "Disbursement initiated",
                LocalDateTime.now()
            );

        } catch (RazorpayException e) {
            throw new RuntimeException("Disbursement failed: " + e.getMessage());
        }
    }
}








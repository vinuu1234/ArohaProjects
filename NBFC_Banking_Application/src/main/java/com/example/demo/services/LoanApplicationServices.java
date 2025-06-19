
package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.AmountExceededException;
import com.example.demo.Exceptions.ApplicationNotFoundException;
import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.Exceptions.LoanProductNotFoundException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Mapper.LoanApplicationMapper;
import com.example.demo.dto.LoanApplicationResponseDTO;
import com.example.demo.dto.LoanApplicationStatusUpdateDTO;
import com.example.demo.dto.LoanApplicationUpdateDTO;
import com.example.demo.dto.LoanApplicationRequestDto;
import com.example.demo.dto.LoanResponseDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.LoanApplication;
import com.example.demo.entity.LoanProduct;
import com.example.demo.entity.Status;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.LoanApplicationRepository;
import com.example.demo.repository.LoanProductRepository;
import com.example.demo.utility.LoanEmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanApplicationServices {

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	@Autowired
	private LoanProductRepository loanProductRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ModelMapper modelMapper;
	// private LoanApplicationMapper loanApplicationMapper;
	@Autowired
	private final LoanApplicationMapper loanApplicationMapper;

	@Autowired
	private LoanEmailService loanEmailService;

	public LoanApplicationResponseDTO createLoanApplication(LoanApplicationRequestDto application) {

		Customer customer = customerRepository.findById(application.getCustomerId())
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found Please register first"));

		LoanProduct loanProduct = loanProductRepository.findById(application.getLoanProductId())
				.orElseThrow(() -> new LoanProductNotFoundException("Loan type not found !!!"));

		// Validate
		if (application.getRequestedAmount() <= 0 || application.getRequestedAmount() > loanProduct.getMaxAmount()
				|| application.getRequestedAmount() < loanProduct.getMinAmount()) {
			throw new IllegalArgumentException("Requested amount Not In the range");
		}

		if (application.getRequestedTenureMonths() <= 0
				|| application.getRequestedTenureMonths() > loanProduct.getMaxTenureMonths()
				|| application.getRequestedTenureMonths() < loanProduct.getMinTenureMonths()) {
			throw new IllegalArgumentException("Tenure must in the range for this loan");
		}

		// Map to entity
		LoanApplication loanEntity = loanApplicationMapper.toEntity(application);
		loanEntity.setCustomer(customer);
		loanEntity.setLoanProduct(loanProduct);

		loanApplicationRepository.save(loanEntity);

		return loanApplicationMapper.toDto(loanEntity);

		/*
		 * LoanApplication loanApplication = new LoanApplication();
		 * loanApplication.setRequestedAmount(application.getRequestedAmount());
		 * loanApplication.setRequestedTenureMonths(application.getRequestedTenureMonths
		 * ()); loanApplication.setPurpose(application.getPurpose());
		 * loanApplication.setApplicationDate(LocalDateTime.now());
		 * loanApplication.setStatus(Status.Pending);
		 * loanApplication.setCustomer(customer);
		 * loanApplication.setLoanProduct(loanProduct);
		 */

		/*
		 * LoanApplication loanApplication = modelMapper.map(application,
		 * LoanApplication.class); loanApplication.setCustomer(customer);
		 * loanApplication.setLoanProduct(loanProduct);
		 */

		// LoanApplication savedApplication =
		// loanApplicationRepository.save(loanApplication);
		// return convertToResponseDTO(savedApplication);

	}

	public List<LoanApplicationResponseDTO> getApplications() {
		List<LoanApplication> loanApplication = loanApplicationRepository.findAll();
		if (loanApplication.isEmpty()) {
			throw new ApplicationNotFoundException("Application Not Found !!!!");
		}

		return loanApplication.stream().map(loanApplicationMapper::toDto).collect(Collectors.toList());
	}

	public List<LoanResponseDto> getApplicationByCustomerId(Long cid) {

		List<LoanApplication> loanApplication = loanApplicationRepository.findByCustomer_CustomerId(cid);

		if (!loanApplication.isEmpty()) {
			return loanApplication.stream().map(this::convertToResponseDTO).collect(Collectors.toList());

		} else {
			throw new ApplicationNotFoundException("Application not found for this customer");
		}

	}

	

	

	private LoanResponseDto convertToResponseDTO(LoanApplication application) {
		LoanResponseDto dto = modelMapper.map(application, LoanResponseDto.class);
		return dto;
	}
	/*
	 * LoanResponseDto dto = new LoanResponseDto();
	 * dto.setApplicationId(application.getApplicationId());
	 * dto.setCustomerId(application.getCustomer().getCustomerId());
	 * dto.setLoanProductId(application.getLoanProduct().getLid());
	 * dto.setApplicationDate(LocalDateTime.now());
	 * dto.setPurpose(application.getPurpose());
	 * dto.setRequestedAmount(application.getRequestedAmount());
	 * dto.setRequestedTenureMonths(application.getRequestedTenureMonths());
	 * dto.setStatus(application.getStatus());
	 * 
	 */

	public void deleteLoanApplication(Long applicationId) {

		LoanApplication application = loanApplicationRepository.findById(applicationId)
				.orElseThrow(() -> new ApplicationNotFoundException("Applicaion not found "));

		loanApplicationRepository.delete(application);

	}

	public List<LoanApplication> getApplicationByStatus(Status status) {

		if (status == null) {
			throw new IllegalArgumentException("enter the status you need to check");
		}

		try {
			List<LoanApplication> application = loanApplicationRepository.findByStatus(status);

			if (application.isEmpty()) {
				throw new ApplicationNotFoundException("No applications found with status: " + status);
			}
			return application;

		} catch (ApplicationNotFoundException ex) {
			throw new ApplicationNotFoundException("Error while fetching applications from database");
		}

	}

	public LoanResponseDto getApplicationsById(Long id) {
		LoanApplication applications = loanApplicationRepository.findById(id)
				.orElseThrow(() -> new ApplicationNotFoundException("Applications not found for this :" + id));
		return convertToResponseDTO(applications);
	}

	public LoanApplicationResponseDTO updateLoanApplicationStatus(Long applicationId,
			LoanApplicationStatusUpdateDTO statusUpdateDTO) {
		LoanApplication loanApplication = loanApplicationRepository.findById(applicationId)
				.orElseThrow(() -> new ResourceNotFoundException("Loan application not found"));

		Status oldStatus=loanApplication.getStatus();
		loanApplication.setStatus(statusUpdateDTO.getStatus());

		LoanApplication updatedApplication = loanApplicationRepository.save(loanApplication);
		if(!statusUpdateDTO.getStatus().equals(oldStatus)) {
			//Optional<Customer> customer=customerRepository.findById(applicationId);
			loanEmailService.sendStatusUpdateEmail(loanApplication, loanApplication.getCustomer());
			System.out.println("Email sent successfully!!!");

		}
		return loanApplicationMapper.toDto(updatedApplication);
		// return updatedApplication;
	}

	public List<LoanApplicationResponseDTO> getLoanApplicationsByStatus(Status status) {
		// Status enumStatus = Status.valueOf(status.toUpperCase());
		return loanApplicationRepository.findByStatus(status).stream().map(loanApplicationMapper::toDto)
				.collect(Collectors.toList());
	}

	public List<LoanApplicationResponseDTO> getApplicationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		return loanApplicationRepository.findByApplicationDateBetween(startDate, endDate).stream()
				.map(loanApplicationMapper::toDto).collect(Collectors.toList());
	}

	public LoanApplicationResponseDTO updateLoanApplication(Long id,
			LoanApplicationUpdateDTO loanApplicationUpdateDTO) {
		LoanApplication loanApplication = loanApplicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Loan application not found"));

		// Validation
		if (loanApplicationUpdateDTO.getRequestedAmount() <= loanApplication.getLoanProduct().getMaxAmount()
				&& loanApplicationUpdateDTO.getRequestedAmount() >= loanApplication.getLoanProduct().getMinAmount()) {
			loanApplication.setRequestedAmount(loanApplicationUpdateDTO.getRequestedAmount());
		} else {
			throw new IllegalArgumentException("Requested amount Not In the range");
		}

		if (loanApplicationUpdateDTO.getRequestedTenureMonths() >= loanApplication.getLoanProduct().getMinTenureMonths()
				&& loanApplicationUpdateDTO.getRequestedTenureMonths() <= loanApplication.getLoanProduct()
						.getMaxTenureMonths()) {
			loanApplication.setRequestedTenureMonths(loanApplicationUpdateDTO.getRequestedTenureMonths());
		} else {
			throw new IllegalArgumentException("Requested tenure Not In the range");
		}

		if (loanApplication.getPurpose() != null && !loanApplication.getPurpose().isEmpty()) {
			loanApplication.setPurpose(loanApplicationUpdateDTO.getPurpose());
		}

		LoanApplication updatedApplication = loanApplicationRepository.save(loanApplication);
		return loanApplicationMapper.toDto(updatedApplication);

	}
	
	/*
	 * public List<LoanResponseDto> getAllLoanApplications(Status status, Long
	 * customerId, Long loanProductId) { List<LoanApplication> applications =
	 * loanApplicationRepository.findWithFilters(status, customerId, loanProductId);
	 * 
	 * return
	 * applications.stream().map(this::convertToResponseDTO).collect(Collectors.
	 * toList()); }
	 */

}

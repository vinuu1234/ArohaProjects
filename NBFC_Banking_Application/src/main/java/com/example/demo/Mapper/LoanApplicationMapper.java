package com.example.demo.Mapper;

import com.example.demo.dto.CustomerBasicDTO;
import com.example.demo.dto.LoanApplicationRequestDto;
import com.example.demo.dto.LoanApplicationResponseDTO;
import com.example.demo.dto.LoanProductBasicDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.LoanApplication;
import com.example.demo.entity.LoanProduct;
import com.example.demo.entity.Status;

import java.io.ObjectInputFilter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanApplicationMapper {
	//LoanApplicationMapper INSTANCE = Mappers.getMapper(LoanApplicationMapper.class);

	@Mapping(source = "customer", target = "customer", qualifiedByName = "mapCustomerToBasicDto")
	@Mapping(source = "loanProduct", target = "loanProduct", qualifiedByName = "mapLoanProductToBasicDto")
	@Mapping(source = "status", target = "status")
	LoanApplicationResponseDTO toDto(LoanApplication loanApplication);
    
	@Named("mapCustomerToBasicDto")
	default CustomerBasicDTO mapCustomerToBasicDto(Customer customer) {
		if (customer == null) {
			return null;
		}
		CustomerBasicDTO dto = new CustomerBasicDTO();
		dto.setCustomerId(customer.getCustomerId());
		dto.setCustomerName(customer.getCustomerName());
		dto.setEmail(customer.getEmail());
		return dto;
	}

	@Named("mapLoanProductToBasicDto")
	default LoanProductBasicDTO mapLoanProductToBasicDto(LoanProduct loanProduct) {
		if (loanProduct == null) {
			return null;
		}
		LoanProductBasicDTO dto = new LoanProductBasicDTO();
		dto.setLoanProductId(loanProduct.getLoanProductId());
		dto.setProductName(loanProduct.getLoanProductName());
		dto.setInterestRate(loanProduct.getBaseInterestRate());
		return dto;
	}
	
	@Mapping(source = "customerId", target="customer.customerId")
	@Mapping(source="loanProductId",target="loanProduct.loanProductId")
	LoanApplication toEntity(LoanApplicationRequestDto loanApplicationRequestDto);
}
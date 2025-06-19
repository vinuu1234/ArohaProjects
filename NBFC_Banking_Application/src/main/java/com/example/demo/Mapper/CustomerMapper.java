package com.example.demo.Mapper;

import com.example.demo.dto.customerDto.CustomerRequestDto;
import com.example.demo.dto.customerDto.CustomerResponseDto;
import com.example.demo.dto.customerDto.LoanApplicationBasicDetails;
import com.example.demo.dto.customerDto.updateCustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.LoanApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface CustomerMapper {

    // Map from CustomerRequestDto to Customer entity
    @Mapping(target = "loanApplications", ignore = true)   
    @Mapping(target = "uploadDocuments", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Customer toEntity(CustomerRequestDto dto);

    // Map from Customer entity to CustomerResponseDto
    @Mapping(target = "loanApplications", source = "loanApplications", qualifiedByName = "mapLoanApplications")
    CustomerResponseDto toDto(Customer customer);

    // Map from updateCustomerDto to Customer entity (for updates)
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "aadharNumber", ignore = true)
    @Mapping(target = "panNumber", ignore = true)
    @Mapping(target = "creditScore", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "occupation", ignore = true)
    @Mapping(target = "loanApplications", ignore = true)
    @Mapping(target = "uploadDocuments", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Customer updateFromDto(updateCustomerDto dto, @org.mapstruct.MappingTarget Customer customer);

    // Custom mapping for loan applications to loan application summaries
    @Named("mapLoanApplications")
    default List<LoanApplicationBasicDetails> mapLoanApplications(List<LoanApplication> loanApplications) {
        if (loanApplications == null) {
            return null;
        }
        return loanApplications.stream()
                .map(this::mapToLoanApplicationSummary)
                .collect(Collectors.toList());
    }

    default LoanApplicationBasicDetails mapToLoanApplicationSummary(LoanApplication loanApplication) {
        if (loanApplication == null) {
            return null;
        }
        LoanApplicationBasicDetails summary = new LoanApplicationBasicDetails();
        summary.setApplicationId(loanApplication.getApplicationId());
        summary.setLoanProductName(loanApplication.getLoanProduct() != null ? 
                loanApplication.getLoanProduct().getLoanProductName() : null);
        summary.setStatus(loanApplication.getStatus() != null ? 
                loanApplication.getStatus().name() : null);
        return summary;
    }
}
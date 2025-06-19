package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.InvalidLoanTypeException;
import com.example.demo.Exceptions.LoanProductNotFoundException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dto.loanproductDto.LoanProductRequestDto;
import com.example.demo.dto.loanproductDto.LoanProductResponseDto;
import com.example.demo.entity.LoanProduct;
import com.example.demo.repository.LoanProductRepository;

@Service
public class LoanProductService {

	@Autowired
	private LoanProductRepository loanProductRepository;
	@Autowired
	private ModelMapper modelMapper;

	public LoanProductResponseDto addLoanProduct(LoanProductRequestDto loanProductDto) {

		LoanProduct loanProduct = modelMapper.map(loanProductDto, LoanProduct.class);
		loanProductRepository.save(loanProduct);
		return modelMapper.map(loanProduct, LoanProductResponseDto.class);
	}

	public List<LoanProductResponseDto> getLoanProducts() {
		return loanProductRepository.findAll().stream().map(lp -> modelMapper.map(lp, LoanProductResponseDto.class))
				.collect(Collectors.toList());
	}

	public LoanProductResponseDto getLoanProductByName(String loanType) {

		if (loanType == null || loanType.trim().isEmpty()) {
			throw new InvalidLoanTypeException("Invalid Loan type");
		}
		LoanProduct product = loanProductRepository.findFirstByLoanProductName(loanType);
		/*
		 * return product.stream().map(lp -> modelMapper.map(lp,
		 * LoanProductResponseDto.class)) .collect(Collectors.toList());
		 */
		return modelMapper.map(product, LoanProductResponseDto.class);
	}

	public LoanProductResponseDto updateLoanProducts(Long lid, LoanProductRequestDto updatedLoanProduct) {

		LoanProduct product = loanProductRepository.findById(lid)
				.orElseThrow(() -> new LoanProductNotFoundException("Loan Product not found for id :" + lid));

		product.setBaseInterestRate(updatedLoanProduct.getBaseInterestRate());
		product.setMaxAmount(updatedLoanProduct.getMaxAmount());
		product.setMinAmount(updatedLoanProduct.getMinAmount());
		product.setMaxTenureMonths(updatedLoanProduct.getMaxTenureMonths());
		product.setMinTenureMonths(updatedLoanProduct.getMinTenureMonths());
		product.setFeatures(updatedLoanProduct.getFeatures());
		product.setRequiredDocuments(updatedLoanProduct.getRequiredDocuments());
		LocalDateTime date = LocalDateTime.now();
		product.setLastUpdated(date);
		loanProductRepository.save(product);
		
		return modelMapper.map(product, LoanProductResponseDto.class);
	}

	public void deleteProduct(Long lid) {
		LoanProduct product = loanProductRepository.findById(lid)
				.orElseThrow(() -> new LoanProductNotFoundException("Loan Product not found for id :" + lid));

		
		loanProductRepository.delete(product);
	}

	public LoanProductResponseDto getLoanProductById(Long id) {
		LoanProduct product =loanProductRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product is not found for this :" +id));
		return modelMapper.map(product, LoanProductResponseDto.class);
	}

}

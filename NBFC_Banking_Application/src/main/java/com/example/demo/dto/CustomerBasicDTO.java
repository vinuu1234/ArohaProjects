package com.example.demo.dto;

import lombok.Data;

@Data
public class CustomerBasicDTO {
    private Long customerId;
	private String customerName;
    private String email;
    
}
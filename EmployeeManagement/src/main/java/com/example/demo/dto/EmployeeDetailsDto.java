package com.example.demo.dto;

import java.util.List;

import com.example.demo.Employee;

import lombok.Data;

@Data
public class EmployeeDetailsDto {
	
	private String DepartmentName;
	private String DepatmentHeadName;
	private int countOfEmployees;
	private List<String> employees;

}

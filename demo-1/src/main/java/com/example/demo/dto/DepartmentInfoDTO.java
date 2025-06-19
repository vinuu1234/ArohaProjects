package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentInfoDTO {
	private String departmentName;
	private String headName;
	private Long employeeCount;
	private List<EmployeeDTO> employees;
}
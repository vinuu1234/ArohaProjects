package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class DepartmentSummaryDTO {
    private String deptName;
    private String deptHeadName;
    private int employeeCount;
    private List<String> employeeNames;

}

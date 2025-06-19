package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentInfoDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentInfoDTO getDepartmentInfo(String deptname) {
		/*
		 * Department department = departmentRepository.findById(deptId) .orElseThrow(()
		 * -> new RuntimeException("Department not found"));
		 */
		/*
		 * Department department = departmentRepository.findByDeptname(deptname)
		 * .orElseThrow(() -> new RuntimeException("Department not found"));
		 */
        Department department = departmentRepository.findByDeptname(deptname);
        if (department == null) {
            throw new RuntimeException("Department not found");
        }

     
     
        String headName = "No head assigned";
        if (department.getDepartmentHead() != null && 
            department.getDepartmentHead().getHead() != null) {
            headName = department.getDepartmentHead().getHead().getName();
        }

        List<EmployeeDTO> employeeDTOs = department.getEmployees().stream()
                .map(emp -> new EmployeeDTO(emp.getId(), emp.getName()))
                .collect(Collectors.toList());

        return new DepartmentInfoDTO(
                department.getDeptname(),
                headName,
                (long) employeeDTOs.size(),
                employeeDTOs
        );
    }
}
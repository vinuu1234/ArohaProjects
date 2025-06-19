package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentSummaryDTO;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentSummaryDTO> getDepartmentSummaries() {
        List<Object[]> results = departmentRepository.fetchDepartmentSummaries();
        List<DepartmentSummaryDTO> summaries = new ArrayList();

        for (Object[] row : results) {
            DepartmentSummaryDTO dto = new DepartmentSummaryDTO();
            dto.setDeptName((String) row[0]);
            dto.setDeptHeadName((String) row[1]);
            dto.setEmployeeCount(((Number) row[2]).intValue());

            String names = (String) row[3];
            dto.setEmployeeNames(Arrays.asList(names.split(",")));

            summaries.add(dto);
        }

        return summaries;
    }
}

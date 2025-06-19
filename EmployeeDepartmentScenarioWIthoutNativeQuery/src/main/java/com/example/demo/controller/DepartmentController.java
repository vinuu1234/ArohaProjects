package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DepartmentInfoDTO;
import com.example.demo.services.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/name/{deptname}")
    public ResponseEntity<DepartmentInfoDTO> getDepartmentInfo(@RequestParam("deptname") String deptname) {

        return ResponseEntity.ok(departmentService.getDepartmentInfo(deptname));
    }
}
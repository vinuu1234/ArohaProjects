package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "department")
@Data
public class Department {
    @Id
    private Long deptid;

    private String deptname;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @OneToOne(mappedBy = "department")
    private DepartmentHead departmentHead;
}
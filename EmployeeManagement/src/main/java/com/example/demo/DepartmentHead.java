package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "department_head")
@Data
public class DepartmentHead {
    @Id
    private Long deptid;

    @OneToOne
    @JoinColumn(name = "deptid", insertable = false, updatable = false)
    private Department department;

    @OneToOne
    @JoinColumn(name = "headid")
    private Employee head;
}

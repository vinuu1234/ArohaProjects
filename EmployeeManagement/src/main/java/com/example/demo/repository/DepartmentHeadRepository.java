package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.DepartmentHead;

@Repository
public interface DepartmentHeadRepository extends JpaRepository<DepartmentHead, Integer> {

}

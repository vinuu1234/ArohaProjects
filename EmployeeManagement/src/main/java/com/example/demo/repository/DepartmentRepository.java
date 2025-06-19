package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Department;

@Repository

public interface DepartmentRepository extends JpaRepository<Department, Integer> {


	@Query(value = """
			    SELECT d.deptname, h.name AS deptHeadName, COUNT(e.id) AS employeeCount,
			           GROUP_CONCAT(e.name ORDER BY e.name) AS employeeNames
			    FROM department d
			    JOIN department_head dh ON d.deptid = dh.deptid
			    JOIN employee h ON dh.headid = h.id
			    JOIN employee e ON d.deptid = e.deptid
			    GROUP BY d.deptid, d.deptname, h.name
			""", nativeQuery = true)
	List<Object[]> fetchDepartmentSummaries();

}

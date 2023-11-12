package com.sunbeaminfo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeaminfo.pojos.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
//select e from Employee e where e.selectedDept.id=:id
	List<Employee> findBySelectedDeptId(Long deptId);
}

package com.sunbeaminfo.service;

import java.util.List;

import com.sunbeaminfo.pojos.Employee;

public interface EmployeeService {
//add a method to list all emps form specified dept
	List<Employee> getAllEmpsByDeprtment(Long deptId);
}

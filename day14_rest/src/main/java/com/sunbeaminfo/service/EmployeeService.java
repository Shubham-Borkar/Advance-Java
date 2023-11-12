package com.sunbeaminfo.service;

import java.util.List;

import com.sunbeaminfo.entities.Employee;

public interface EmployeeService {
	List<Employee> getAllEmpList();
	Employee addEmployeeDetails(Employee emp);
    Employee getEmpDetails(Long empId);
	String deleteEmpDetails(Long empId);
	//String updateEmpDetails(Long empId);

}

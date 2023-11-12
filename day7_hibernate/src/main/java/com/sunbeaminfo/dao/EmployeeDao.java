package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Employee;

public interface EmployeeDao {
//add a method for saving emp dtls
	String addNewEmpDetails(Employee newEmp);
	//add a method to get emp details by id
	Employee getEmpDetails(Long empId);
}

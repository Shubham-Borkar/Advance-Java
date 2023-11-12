package com.sunbeaminfo.dao;

import java.time.LocalDate;
import java.util.List;

import com.sunbeaminfo.pojos.Employee;
import com.sunbeaminfo.pojos.EmploymentType;

public interface EmployeeDao {
//add a method for saving emp dtls
	String addNewEmpDetails(Employee newEmp);

	// add a method for saving emp dtls , using get curnt sesison
	String addNewEmpDetailsGetCurntSession(Employee newEmp);

	// add a method to get emp details by id
	Employee getEmpDetails(Long empId);

	// add a method to get list of all emps
	List<Employee> getAllEmps();

	/*
	 * Display all employees joined within date range n drawing sal > specific
	 * salary
	 */
	List<Employee> getSelectedEmps(LocalDate start, LocalDate end, double minSal);

	/*
	 * Objective : Display all emp last names of a specific employment type
	 * 
	 */
	List<String> getLastNamesByEmpType(EmploymentType type);

	/*
	 * Objective : Display fn,ln,salary of all emps of a specific employment type
	 * 
	 */
	List<Employee> testCtorExpression(EmploymentType type);

// Update salary of the emp
	String updateEmpSalary(String email, String pwd, double salIncr);
	//bulk updation
	String bulkUpdateSalary(LocalDate date,double salIncrement);
}

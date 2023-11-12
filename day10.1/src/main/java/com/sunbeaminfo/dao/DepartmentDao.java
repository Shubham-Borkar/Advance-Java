package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Department;

public interface DepartmentDao {
	// add new dept details
	String addNewDepartment(Department dept);

	// delete dept details
	String deleteDepartmentDetails(String deptName);

	// get dept details
	Department getDepartmentDetails(String deptName);

	// get dept n emp details
	Department getDepartmentAndEmpDetails(String deptName);
}

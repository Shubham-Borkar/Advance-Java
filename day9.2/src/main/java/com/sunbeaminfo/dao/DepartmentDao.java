package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Department;

public interface DepartmentDao {
	// add new dept details
	String addNewDepartment(Department dept);
	String deleteDept(String dname);
}

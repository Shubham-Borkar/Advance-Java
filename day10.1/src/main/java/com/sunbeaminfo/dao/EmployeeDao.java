package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Employee;

public interface EmployeeDao {
	//adding the emp to existing dept
	String addEmpToDept(Employee newEmp, Long deptId);
}

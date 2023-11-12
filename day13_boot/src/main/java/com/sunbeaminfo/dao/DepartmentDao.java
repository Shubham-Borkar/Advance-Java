package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeaminfo.pojos.Department;

public interface DepartmentDao extends JpaRepository<Department,Long>{

	
	//get all depts 
//	List<Department> getAllDepartments();
}

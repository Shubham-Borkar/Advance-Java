package com.sunbeaminfo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunbeaminfo.entities.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
//how to get all emps ?  use inherited API : findAll
	//save emp : save : inherited  method
	//delete emp details : findById , delete(T entity)
	//for emp signin : finder method
	Optional<Employee> findByEmailAndPassword(String em,String pass);
//	@Query("Select new com.sunbeaminfo.entities(firstName,lastName) from Employee e where e.salary>:min")
//	List<Employee> fetchNameBySal(@Param("min") double minsal);
	@Query("Select new com.sunbeaminfo.entities.Employee(firstName,lastName) from Employee e where e.salary>?1")
	List<Employee> fetchNameBySal(double minsal);
}

package com.sunbeaminfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sunbeaminfo.entities.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}

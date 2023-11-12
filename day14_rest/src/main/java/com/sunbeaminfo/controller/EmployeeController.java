package com.sunbeaminfo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeaminfo.entities.Employee;
import com.sunbeaminfo.service.EmployeeService;

@RestController //mandatory
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	public EmployeeController() {
		System.out.println("Inside constructor"+getClass());
	}
	
	@Autowired
	public EmployeeService empService;
	
	//get all emp
	@GetMapping
	public List<Employee> getAllEmployee() {
		return empService.getAllEmpList();
	}
	@PostMapping
	public Employee saveEmployeeDetails(@RequestBody Employee emp) {
		System.out.println("Save Employee called for"+emp.getFirstName()+emp.getLastName());
		return empService.addEmployeeDetails(emp);
	}
	@DeleteMapping("/{empId}")
	public String deleteEmpDetails(@PathVariable Long empId) {
		return empService.deleteEmpDetails(empId);	
	}
	@GetMapping("/{id}")
	public Employee getEmpDetailsById(@PathVariable Long id) {
		System.out.println("Get employee by id called for id="+id);
		return empService.getEmpDetails(id);
	}
	@PutMapping
	public Employee updateEmp(@RequestBody Employee detachedEmp) {
		System.out.println("In Update Emp for Emp id" + detachedEmp.getId());
		empService.getEmpDetails(detachedEmp.getId());
		return empService.addEmployeeDetails(detachedEmp);		
	}

}

package com.sunbeaminfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeaminfo.dao.EmployeeDao;
import com.sunbeaminfo.entities.Employee;
import com.sunbeaminfo.exception.ResourceNotFound;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  public EmployeeDao empDao;
	@Override
	public List<Employee> getAllEmpList() {
		return empDao.findAll();
	}
	@Override
	public Employee addEmployeeDetails(Employee emp) {
		System.out.println("add emp details save() for"+emp.getFirstName()+emp.getLastName());
		return empDao.save(emp);
	}
	@Override
	public Employee getEmpDetails(Long empId) {
		System.out.println("get emp details by empid"+empId);
		return empDao.findById(empId).orElseThrow(()->new ResourceNotFound("Emp not found"));
	}
	@Override
	public String deleteEmpDetails(Long empId) {
		try {
			empDao.findById(empId).orElseThrow(()->new ResourceNotFound("Emp not found"));
			empDao.deleteById(empId);
		}catch (RuntimeException e) {
			System.out.println(e);
			return "Emp not their";
		}
		return "Emp deleted";
	}

}

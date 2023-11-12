package com.sunbeaminfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeaminfo.dao.EmployeeDao;
import com.sunbeaminfo.pojos.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	//dep : emp dao i/f
	@Autowired
	private EmployeeDao empDao;

	@Override
	public List<Employee> getAllEmpsByDeprtment(Long deptId) {
		// TODO Auto-generated method stub
		return null;
	}

}

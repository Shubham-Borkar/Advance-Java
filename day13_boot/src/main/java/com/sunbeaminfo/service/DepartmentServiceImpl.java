package com.sunbeaminfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeaminfo.dao.DepartmentDao;
import com.sunbeaminfo.pojos.Department;

@Service //mandatory cls level anno fro service layer (B.L layer) spring bean
@Transactional //mandatory anno for auto tx management
public class DepartmentServiceImpl implements DepartmentService {
	//dependency : dao layer i/f
	@Autowired
	private DepartmentDao deptDao;

	@Override

	public List<Department> getAllDepts() {
		// TODO Auto-generated method stub
		return deptDao.findAll();
	}

}

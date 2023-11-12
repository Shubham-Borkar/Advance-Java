package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Department;

import java.util.List;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //mandatory cls level anno to tell SC following is a spring bean 
//containing DAL (data access logic)
public class DepartmentDaoImpl implements DepartmentDao {
	//dependency
	@Autowired //autowire=byType : Field level DI
	private SessionFactory factory;

	@Override
	public List<Department> getAllDepartments() {
		String jpql="select d from Department d";
		return factory.getCurrentSession()
				.createQuery(jpql, Department.class)
				.getResultList();
	}
	
	
	

}

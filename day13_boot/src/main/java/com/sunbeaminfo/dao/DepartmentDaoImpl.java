//package com.sunbeaminfo.dao;
//
//import com.sunbeaminfo.pojos.Department;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository //mandatory cls level anno to tell SC following is a spring bean 
////containing DAL (data access logic)
//public class DepartmentDaoImpl implements DepartmentDao {
//	//dependency
//	@Autowired //autowire=byType : Field level DI OR @PersistenceContext
//	private EntityManager mgr;
//
//	@Override
//	public List<Department> getAllDepartments() {
//		String jpql="select d from Department d";
//		return mgr
//				.createQuery(jpql, Department.class)
//				.getResultList();
//	}
//	
//	
//	
//
//}

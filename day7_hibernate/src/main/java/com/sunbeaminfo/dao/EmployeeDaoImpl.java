package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Employee;
import org.hibernate.*;
import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.io.Serializable;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public String addNewEmpDetails(Employee newEmp) {
		String mesg = "Adding emp details failed !!!!!";
		// 1. get Session from SF
		Session session = getFactory().openSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			Serializable empId = session.save(newEmp);
			tx.commit();
			mesg = "Added new emp details with ID=" + empId;
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exc to the caller
			throw e;
		} finally {
			if (session != null)
				session.close();// L1 cache is destroyed , db cn rets to DBCP
		}
		return mesg;
	}

	@Override
	public Employee getEmpDetails(Long empId) {
		Employee emp=null;
		// 1. get session from SF
		Session session = getFactory().openSession();
		// 2. Tx
		Transaction tx = session.beginTransaction();
		try {
			emp=session.get(Employee.class, empId);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exc to the caller
			throw e;
		} finally {
			if (session != null)
				session.close();// L1 cache is destroyed , db cn rets to DBCP
		}
		return emp;
	}

}

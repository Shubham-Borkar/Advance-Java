package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Department;
import com.sunbeaminfo.pojos.Employee;

import org.hibernate.*;
import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public String addNewDepartment(Department dept) {
		System.out.println("Add new dept called");
		String mesg = "Adding new dept failed";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin a tx
		Transaction tx = session.beginTransaction();
		try {
			session.persist(dept);// dept : persistent
			tx.commit();/*
						 * session.flush() --> dept : new entity --> inserts a rec in parent table
						 * --chks for cascade : ALL --insert cascades insert operation to child table
						 * (emps recs will be inserted)
						 * 
						 */
			mesg = "Added new dept with id=" + dept.getId();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public String deleteDept(String dname) {
		String mesg = "Deleting dept failed";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin a tx
		Transaction tx = session.beginTransaction();
		String jpql="select d from Department d where d.name=:nm ";
		try {
			Department dept = session.createQuery(jpql, Department.class).setParameter("nm", dname).getSingleResult();
			session.delete(dept);// dept : persistent
			tx.commit();/*
						 * session.flush() --> dept : new entity --> inserts a rec in parent table
						 * --chks for cascade : ALL --insert cascades insert operation to child table
						 * (emps recs will be inserted)
						 * 
						 */
			mesg = "dept with name=" + dept.getName()+" Deleted";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}

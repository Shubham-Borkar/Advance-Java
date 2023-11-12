package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Department;
import org.hibernate.*;
import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public String addNewDepartment(Department dept) {
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
	public String deleteDepartmentDetails(String deptName) {
		String mesg="Deleting dept details failed";
		String jpql="select d from Department d where d.name=:nm";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			//get dept by it's name
			Department department=session.createQuery(jpql, Department.class)
					.setParameter("nm", deptName)
					.getSingleResult();
			//no exc => valid dept id , department : persistent
			session.delete(department);//department : marked for removal : Removed
			tx.commit(); //hib performs dirty chking --cascadeType.ALL : includes REMOVE
			//so cascades remove operation to child entities n then delete parent rec.
			mesg="Deleted dept n emp details ....";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}

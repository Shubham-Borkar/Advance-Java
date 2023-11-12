package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Employee;
import com.sunbeaminfo.pojos.EmploymentType;

import org.apache.commons.io.FileUtils;
import org.hibernate.*;
import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public String addNewEmpDetails(Employee newEmp) {
		String mesg = "Adding emp details failed !!!!!";
		// 1. get Session from SF
		Session session = getFactory().openSession();
		Session session2 = getFactory().openSession();
		System.out.println(session == session2);// false
		System.out.println("session is open " + session.isOpen() + " is connected " + session.isConnected());// t t

		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			Serializable empId = session.save(newEmp);
			tx.commit();
			System.out.println("session is open " + session.isOpen() + " is connected " + session.isConnected());// t t
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
		System.out.println("session is open " + session.isOpen() + " is connected " + session.isConnected());// f f
		return mesg;
	}

	@Override
	public String addNewEmpDetailsGetCurntSession(Employee newEmp) {
		// newEmp : TRANSIENT , exists only in java heap
		String mesg = "Adding emp details failed !!!!!";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		Session session2 = getFactory().getCurrentSession();
		System.out.println(session == session2);// true

		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		System.out.println("session is open " + session.isOpen() + " is connected " + session.isConnected());// t t
		try {
			Serializable empId = session.save(newEmp);
			// newEmp : PERSISTENT (is it associated with L1 cache : YES , not yet part of
			// DB
			tx.commit();// hibernate performs auto dirty checking upon commit :1. session.flush() -->
						// hib synchs up the state of L1 cache with DB, finds a new persistent entity
// fires insert query , session.close() --> L1 cache is destroyed n pooled out db cn rets to DB CP. 
			System.out.println("session is open " + session.isOpen() + " is connected " + session.isConnected());// f f
			// newEmp : DETACHED (de coupled from L1 cache)
			mesg = "Added new emp details with ID=" + empId;
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("session is open " + session.isOpen() + " is connected " + session.isConnected());// f f
			// re throw the exc to the caller
			throw e;
		}

		return mesg;
	}

	@Override
	public Employee getEmpDetails(Long empId) {
		Employee emp = null;// emp : does not exist !
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. Tx
		Transaction tx = session.beginTransaction();
		try {
			// uses a cache -- chks if entity exists in cache
			// no --> select query --RST --valid id
			emp = session.get(Employee.class, empId);
			// hib lifts entity dtls from cache .
			emp = session.get(Employee.class, empId);
			emp = session.get(Employee.class, empId);
			// valid id emp : PERSISTENT

			tx.commit();// session.flush --> dirty chking --> no dmls --> session.close()
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the exc to the caller
			throw e;
		}
		return emp;// emp : DETACHED
	}

	@Override
	public List<Employee> getAllEmps() {
		List<Employee> emps = null;
		String jpql = "select e from Employee e";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			emps = session.createQuery(jpql, Employee.class).getResultList();
			// emps : list of PERSISTENT entities
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return emps;// emps : list of DETACHED entities
	}

	@Override
	public List<Employee> getSelectedEmps(LocalDate start, LocalDate end1, double minSal) {
		List<Employee> emps = null;
		String jpql = "select e from Employee e where e.joinDate between :begin and :end and e.salary > :min";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			emps = session.createQuery(jpql, Employee.class).setParameter("begin", start).setParameter("end", end1)
					.setParameter("min", minSal).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return emps;
	}

	@Override
	public List<String> getLastNamesByEmpType(EmploymentType type1) {
		List<String> lastNames = null;
		String jpql = "select e.lastName from Employee e where e.empType=:type";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			lastNames = session.createQuery(jpql, String.class).setParameter("type", type1).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return lastNames;
	}

	@Override
	public List<Employee> testCtorExpression(EmploymentType type) {
		List<Employee> emps = null;
		String jpql = "select new com.sunbeaminfo.pojos.Employee(firstName,lastName,salary) from Employee e where e.empType=:ty";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			emps = session.createQuery(jpql, Employee.class).setParameter("ty", type).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return emps;
	}

	@Override
	public String updateEmpSalary(String email1, String pwd1, double salIncr) {
		String mesg = "updation failed !!!!";
		Employee emp = null;
		String jpql = "select e from Employee e where e.email=:em and e.password=:pass";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			emp = session.createQuery(jpql, Employee.class).setParameter("em", email1).setParameter("pass", pwd1)
					.getSingleResult();
			// => emp : PERSISTENT
			// setter
			emp.setSalary(emp.getSalary() + salIncr);// modifying state of the persistent entity
			// session.evict(emp);//emp : DETACHED
			tx.commit();// hib session.flush() --> dirty checking --> DML : update , session.close()
			mesg = "emp sal updated !";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		// modifying the state of DETACHED entity -- changes WON'T be tracked !!!!
		emp.setSalary(emp.getSalary() + salIncr);
		return mesg;
	}

	@Override
	public String bulkUpdateSalary(LocalDate date, double salIncrement) {
		String mesg = "bulk updations failed !!!!";
		String updateJpql = "update Employee e set e.salary=e.salary+:incr where e.joinDate < :dt";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			int updateCount = session.createQuery(updateJpql).setParameter("incr", salIncrement)
					.setParameter("dt", date).executeUpdate();
			tx.commit();
			mesg = "updated " + updateCount + " emps !";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public String deleteEmpDetails(Long empId) {
		String mesg = "Deletion of emp details failed !!!!";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin a tx
		Transaction tx = session.beginTransaction();
		try {
			// get persistent emp
			Employee employee = session.get(Employee.class, empId);
			if (employee != null) {
				// delete emp dtls
				session.delete(employee);
				mesg = "Emp details deleted !";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public String storeImage(Long empId, String imagePath) throws IOException {
		String mesg = "Storing Image failed !";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin a tx
		Transaction tx = session.beginTransaction();
		try {
			// get emp details
			Employee emp = session.get(Employee.class, empId);
			if (emp != null) {
				// file validation
				File imgFile = new File(imagePath);
				if (imgFile.isFile() && imgFile.canRead()) {
					// emp found n valid file
					emp.setImage(FileUtils.readFileToByteArray(imgFile));
					mesg = "saved emp image successfully !";
				} else
					mesg = mesg + " Invalid File ";
			} else
				mesg = mesg + " Invalid Emp id ";
			tx.commit();// update
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public String restoreImage(Long empId, String imagePath) throws IOException {
		String mesg = "Restoring Image failed !";
		// get Session from SF
		Session session = getFactory().getCurrentSession();
		// begin a tx
		Transaction tx = session.beginTransaction();
		try {
			// get emp details
			Employee emp = session.get(Employee.class, empId);
			if (emp != null) {
				//read byte[] : img contents from DB
				byte[] imgContents=emp.getImage();
				if(imgContents != null)
				{
					FileUtils.writeByteArrayToFile(new File(imagePath), imgContents);
					mesg="Restoring Image successful !";
				} 
				else 
					mesg="Restoring Image failed ! : no image assigned yet !";
			}
			else 
				mesg="Restoring Image failed ! : invalid emp id";
			tx.commit();//no DML !
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}

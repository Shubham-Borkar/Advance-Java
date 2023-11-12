package com.sunbeaminfo.dao;

import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunbeaminfo.pojos.User;

public class UserDaoImpl implements UserDao {

	@Override
	public String registerNewUser(User newUser) {
		String mesg = "Adding emp details failed !!!!!";
		// 1. get Session from SF
		Session session = getFactory().openSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			Serializable empId = session.save(newUser);
			tx.commit();
			mesg = "Added new User details with ID=" + empId;
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

}

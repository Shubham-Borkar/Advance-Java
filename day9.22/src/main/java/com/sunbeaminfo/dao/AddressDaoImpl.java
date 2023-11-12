package com.sunbeaminfo.dao;

import com.sunbeaminfo.pojos.Address;
import com.sunbeaminfo.pojos.Employee;

import org.hibernate.*;
import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

public class AddressDaoImpl implements AddressDao {

	@Override
	public String assignAddress(Long empId, Address address) {
		String mesg="Assigning adr failed !";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			//get emp details from it's id
			Employee emp=session.get(Employee.class, empId);
			if(emp != null) {
				//set up uni dir link
				address.setOwner(emp);
				session.save(address);
				mesg="Assigned addr to emp "+emp.getFirstName();
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return mesg;
	}

}

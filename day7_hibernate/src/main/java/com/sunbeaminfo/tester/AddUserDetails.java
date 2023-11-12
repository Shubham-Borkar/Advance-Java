package com.sunbeaminfo.tester;

import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeaminfo.dao.EmployeeDaoImpl;
import com.sunbeaminfo.dao.UserDaoImpl;
import com.sunbeaminfo.pojos.Employee;
import com.sunbeaminfo.pojos.EmploymentType;
import com.sunbeaminfo.pojos.User;
import com.sunbeaminfo.pojos.UserRole;

public class AddUserDetails {

	public static void main(String[] args) {
		try (SessionFactory factory = getFactory();
				// cls load --static init block --SF : DBCP will be created!
				Scanner sc = new Scanner(System.in);) {
			//create emp dao instance
			UserDaoImpl dao=new UserDaoImpl();
	
			// create transient emp object
			User user= new User("aaa","aaa","aaa","aaa", UserRole.valueOf(sc.next().toUpperCase()),LocalDate.parse(sc.next()),5000.0);
					
			System.out.println(dao.registerNewUser(user));
		} // JVM : factory.close() => cn pool will be cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}

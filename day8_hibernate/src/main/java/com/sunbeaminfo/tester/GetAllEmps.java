6package com.sunbeaminfo.tester;

import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeaminfo.dao.EmployeeDaoImpl;
import com.sunbeaminfo.pojos.Employee;
import com.sunbeaminfo.pojos.EmploymentType;

public class GetAllEmps {

	public static void main(String[] args) {
		try (SessionFactory factory = getFactory();
				// cls load --static init block --SF : DBCP will be created!
				) {
			// create emp dao instance
			EmployeeDaoImpl dao = new EmployeeDaoImpl();
			System.out.println("All emps : ");
			dao.getAllEmps().forEach(System.out::println);
		} // JVM : factory.close() => cn pool will be cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}

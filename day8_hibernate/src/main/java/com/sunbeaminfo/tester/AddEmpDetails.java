package com.sunbeaminfo.tester;

import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeaminfo.dao.EmployeeDaoImpl;
import com.sunbeaminfo.pojos.Employee;
import com.sunbeaminfo.pojos.EmploymentType;

public class AddEmpDetails {

	public static void main(String[] args) {
		try (SessionFactory factory = getFactory();
				// cls load --static init block --SF : DBCP will be created!
				Scanner sc = new Scanner(System.in);) {
			//create emp dao instance
			EmployeeDaoImpl dao=new EmployeeDaoImpl();
			System.out.println(
					"Enter emp details : firstName, String lastName, String email, String password, String confirmPassword,\n"
							+ "			LocalDate joinDate, EmploymentType empType, double salary");
			// create transient emp object
			Employee emp = new Employee(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(),
					LocalDate.parse(sc.next()), EmploymentType.valueOf(sc.next().toUpperCase()), sc.nextDouble());
			System.out.println(dao.addNewEmpDetails(emp));
		} // JVM : factory.close() => cn pool will be cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}

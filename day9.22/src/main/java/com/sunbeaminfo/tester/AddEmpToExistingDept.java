package com.sunbeaminfo.tester;

import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeaminfo.dao.EmployeeDaoImpl;
import com.sunbeaminfo.pojos.Employee;
import com.sunbeaminfo.pojos.EmploymentType;

public class AddEmpToExistingDept {

	public static void main(String[] args) {
		try (SessionFactory factory = getFactory(); // cls load --static init block --SF : DBCP will be created!
				Scanner sc = new Scanner(System.in);) {
			//emp dao
			EmployeeDaoImpl dao=new EmployeeDaoImpl();
			System.out.println(
					"Enter emp details : firstName,  lastName,  email,  " + "password joinDate,  empType,  salary");
			// create transient emp object
			Employee emp = new Employee(sc.next(), sc.next(), sc.next(), sc.next(), LocalDate.parse(sc.next()),
					EmploymentType.valueOf(sc.next().toUpperCase()), sc.nextDouble());
			System.out.println("Enter dept id");
			System.out.println(dao.addEmpToDept(emp, sc.nextLong()));
		} // JVM : factory.close() => cn pool will be cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}

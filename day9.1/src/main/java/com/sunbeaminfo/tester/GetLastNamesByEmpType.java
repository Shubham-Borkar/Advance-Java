package com.sunbeaminfo.tester;

import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeaminfo.dao.EmployeeDaoImpl;
import com.sunbeaminfo.pojos.Employee;
import com.sunbeaminfo.pojos.EmploymentType;
import static java.time.LocalDate.parse;
public class GetLastNamesByEmpType {

	public static void main(String[] args) {
		try (SessionFactory factory = getFactory();
				// cls load --static init block --SF : DBCP will be created!
				Scanner sc = new Scanner(System.in);) {
			// create emp dao instance
			EmployeeDaoImpl dao = new EmployeeDaoImpl();
			System.out.println("Enter employment type ");
			dao.getLastNamesByEmpType(EmploymentType.valueOf(sc.next().toUpperCase()))
			.forEach(System.out::println);
		} // JVM : factory.close() => cn pool will be cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}

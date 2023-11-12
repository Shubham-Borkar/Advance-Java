package com.sunbeaminfo.tester;

import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeaminfo.dao.AddressDaoImpl;
import com.sunbeaminfo.dao.EmployeeDaoImpl;
import com.sunbeaminfo.pojos.Address;
import com.sunbeaminfo.pojos.Employee;
import com.sunbeaminfo.pojos.EmploymentType;

public class AssignEmpAddress {

	public static void main(String[] args) {
		try (SessionFactory factory = getFactory(); // cls load --static init block --SF : DBCP will be created!
				Scanner sc = new Scanner(System.in);) {
			// adr dao
			AddressDaoImpl dao = new AddressDaoImpl();
			System.out.println("Enter emp id");
			Long empId = sc.nextLong();
			System.out.println("Enter adr details : adrLine1,  adrLine2,  city,  state,  country,  zipCode");
			System.out.println(dao.assignAddress(empId,
					new Address(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next())));
		} // JVM : factory.close() => cn pool will be cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}

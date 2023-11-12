package com.sunbeaminfo.tester;

import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeaminfo.dao.DepartmentDaoImpl;
import com.sunbeaminfo.pojos.Department;

public class DisplayDepartmentDetails {

	public static void main(String[] args) {
		try (SessionFactory factory = getFactory();
				// cls load --static init block --SF : DBCP will be created!
				Scanner sc = new Scanner(System.in)) {
			// dept dao
			DepartmentDaoImpl dao = new DepartmentDaoImpl();
			System.out.println("Enter dept name to display details ");
			Department department = dao.getDepartmentDetails(sc.next());
			System.out.println("Dept details");
			System.out.println(department);
			
		} // JVM : factory.close() => cn pool will be cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}

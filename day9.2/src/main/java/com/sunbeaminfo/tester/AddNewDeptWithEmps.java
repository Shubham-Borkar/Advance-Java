package com.sunbeaminfo.tester;

import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeaminfo.dao.DepartmentDaoImpl;
import com.sunbeaminfo.pojos.Department;
import com.sunbeaminfo.pojos.Employee;
import com.sunbeaminfo.pojos.EmploymentType;

public class AddNewDeptWithEmps {

	public static void main(String[] args) {
		try (SessionFactory factory = getFactory();
				// cls load --static init block --SF : DBCP will be created!
				Scanner sc = new Scanner(System.in)) {
			// dept dao
			DepartmentDaoImpl dao = new DepartmentDaoImpl();
			System.out.println("Enter dept name n location ");
			Department dept = new Department(sc.next(), sc.next());
			for (int i = 0; i < 3; i++) {
				System.out.println("Adding Employee"+ i);
				System.out.println(
						"Enter emp details : firstName,  lastName,  email,  " + "password joinDate,  empType,  salary");
				// create transient emp object
				Employee emp = new Employee(sc.next(), sc.next(), sc.next(), sc.next(), LocalDate.parse(sc.next()),
						EmploymentType.valueOf(sc.next().toUpperCase()), sc.nextDouble());
				//establish bi dir link
				dept.addEmployee(emp);
			}
			System.out.println(dao.addNewDepartment(dept));
		} // JVM : factory.close() => cn pool will be cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}

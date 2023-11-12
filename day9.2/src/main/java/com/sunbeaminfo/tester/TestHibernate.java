package com.sunbeaminfo.tester;
import static com.sunbeaminfo.utils.HibernateUtils.getFactory;

import org.hibernate.SessionFactory;

public class TestHibernate {

	public static void main(String[] args) {
		try(SessionFactory factory=getFactory() //cls load --static init block --SF : DBCP will be created!
				) {
			System.out.println("hibernate up n running !!!!"+factory);
		}  //JVM : factory.close() => cn pool will be cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}

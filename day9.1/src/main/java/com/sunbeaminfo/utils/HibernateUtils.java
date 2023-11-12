package com.sunbeaminfo.utils;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory factory;
	static {
		System.out.println("in static init block");
		factory=new Configuration() //creates EMPTY config instance
				.configure() //loads all the props n mappings from hibernate.cfg.xml
				.buildSessionFactory();
	}
	//getter
	public static SessionFactory getFactory() {
		return factory;
	}
	
}

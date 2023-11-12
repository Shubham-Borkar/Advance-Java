package com.sunbeaminfo.utils;

import java.sql.*;

public class DBUtils {
	private static Connection cn;

	// add a method to open fixed cn to DB
	public static Connection openConnection(String url,String userName,String pwd) throws SQLException {
		// Loading n registering JDBC driver : not required since JDBC 4.x (JDK 11)
		// version
	//	String url = "jdbc:mysql://localhost:3306/advjava?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
		cn = DriverManager.getConnection(url,userName,pwd);
		return cn;
	}

	// add a method to close db cn
	public static void closeConnection() throws SQLException {
		if (cn != null)
			cn.close();
	}

}

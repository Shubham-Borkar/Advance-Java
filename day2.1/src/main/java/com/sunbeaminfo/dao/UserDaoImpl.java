package com.sunbeaminfo.dao;

import java.sql.*;
import java.time.LocalDate;

import com.sunbeaminfo.pojos.User;
//import ALL static members of DBUtils class
import static com.sunbeaminfo.utils.DBUtils.*;

public class UserDaoImpl implements UserDao {
	// fields
	private Connection connection;
	private PreparedStatement pst1;
	private PreparedStatement pst2;
	private PreparedStatement pst3;
	public UserDaoImpl() throws SQLException {
		// open cn
		connection = openConnection();
		// pst1
		pst1 = connection.prepareStatement("select * from users where email=? and password=?");
		System.out.println("user dao created !");
		//insert into users (first_name,last_name,email,password,dob,status,role)values('Shubham','Borkar','s','s','2020/01/01',0,'admin');
		pst2 = connection.prepareStatement("insert into users (first_name,last_name,email,password,dob,status,role)values(?,?,?,?,?,0,'voter')");
		pst3 = connection.prepareStatement("select * from candidates");
	}

	@Override
	public User autheticateUser(String email, String password) throws SQLException {
		// 1. set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
			/*
			 * int userId, String firstName, String lastName, String email, String password,
			 * Date dob, boolean status, String role
			 */
			if (rst.next())
				return new User(rst.getInt(1), 
						rst.getString(2), rst.getString(3), 
						email, password, rst.getDate(6),
						rst.getBoolean(7), rst.getString(8));
		}
		return null;
	}

	// clean up
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2!=null)
			pst2.close();
		if (pst3!=null)
			pst3.close();
		closeConnection();
		System.out.println("user dao cleaned up !");

	}

	@Override
	public String register(User u) throws SQLException{
		
		 int diff=LocalDate.now().getYear()-u.getDob().toLocalDate().getYear();
		 if(diff<21) 
			 return "Not a Valid Age for Voter Registration";
		 System.out.println(diff);
		
		pst2.setString(1, u.getFirstName());
		pst2.setString(2, u.getLastName());
		pst2.setString(3, u.getEmail());
		pst2.setString(4, u.getPassword());
		pst2.setDate(5, u.getDob());
		int cnt=pst2.executeUpdate();
		if(cnt>0)
			return "Sucessful Voter Registered";
		 
	
		return null;
	}

	@Override
	public void candlist() throws SQLException {
		ResultSet rs = pst3.executeQuery();
		while(rs.next()) {
			System.out.println( rs.getInt(1)+"-"+
			rs.getString(2)+"-"+
			rs.getString(3)+"-"+
			rs.getInt(4));
			
		}
	}

}

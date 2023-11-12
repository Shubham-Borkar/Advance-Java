package com.sunbeaminfo.dao;

import java.sql.*;
import java.time.LocalDate;

import com.sunbeaminfo.pojos.User;
//import ALL static members of DBUtils class
import static com.sunbeaminfo.utils.DBUtils.*;

public class UserDaoImpl implements UserDao {
	// fields
	private Connection connection;
	private PreparedStatement pst1,pst2,pst3;

	public UserDaoImpl() throws SQLException {
		// open cn
		connection = openConnection();
		// pst1
		pst1 = connection.prepareStatement("select * from users where email=? and password=?");
		pst2=connection.prepareStatement("update users set status=? where id=?");
		System.out.println("user dao created !");
		pst3 = connection.prepareStatement("insert into users (first_name,last_name,email,password,dob,status,role)values(?,?,?,?,?,0,'voter')");
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
	

	@Override
	public String updateVotingStatus(int voterId) throws SQLException {
		// set IN params
		pst2.setBoolean(1, true);
		pst2.setInt(2, voterId);
		int updateCount=pst2.executeUpdate();		
		return "Updated voting status !";
	}

	// clean up
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		closeConnection();
		System.out.println("user dao cleaned up !");

	}
	
	@Override
	public String register(User u) throws SQLException{
		
		 int diff=LocalDate.now().getYear()-u.getDob().toLocalDate().getYear();
		 if(diff<21) 
			 return "Not a Valid Age for Voter Registration";
		 System.out.println(diff);
		
		pst3.setString(1, u.getFirstName());
		pst3.setString(2, u.getLastName());
		pst3.setString(3, u.getEmail());
		pst3.setString(4, u.getPassword());
		pst3.setDate(5, u.getDob());
		int cnt=pst3.executeUpdate();
		if(cnt>0)
			return "Sucessful Voter Registered";
		 
	
		return null;
	}

}

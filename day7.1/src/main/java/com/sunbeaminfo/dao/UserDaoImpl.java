package com.sunbeaminfo.dao;

import java.sql.*;

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
		pst3=connection.prepareStatement(
				"insert into users values(default,?,?,?,?,?,?,?)");
		System.out.println("user dao created !");
	}

	@Override
	public User autheticateUser(String email, String password) throws SQLException {
		// 1. set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
			System.out.println("executed select query");
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
	

	@Override
	public String registerNewVoter(User voter) throws SQLException{
		// set IN params
		//first_name | last_name | email             | 
				//password | dob        | status | role  
		pst3.setString(1,voter.getFirstName());
		pst3.setString(2,voter.getLastName());
		pst3.setString(3,voter.getEmail());
		pst3.setString(4,voter.getPassword());
		pst3.setDate(5, voter.getDob());
		pst3.setBoolean(6, false);
		pst3.setString(7, "voter");
		int rowCount=pst3.executeUpdate();
		return "New voter registered .....";
	}

	// clean up
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		closeConnection();
		System.out.println("user dao cleaned up !");

	}

}

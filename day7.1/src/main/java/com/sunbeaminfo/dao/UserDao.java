package com.sunbeaminfo.dao;

import java.sql.SQLException;

import com.sunbeaminfo.pojos.User;

public interface UserDao {
//add a method for user authentication   
	User autheticateUser(String email, String password) throws SQLException;
	//add a method to change voting status
	String updateVotingStatus(int voterId)throws SQLException;
	//add a method for voter registration
	String registerNewVoter(User voter) throws SQLException;
}

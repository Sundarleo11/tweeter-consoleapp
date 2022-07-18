package com.tweetapp.dao;

import java.sql.Date;

import com.tweetapp.exception.UserException;

public interface RegisterDAO {
	
	void registerUser(String userID,String Password,String gender, Date dateOfBirth, String emailID, String password2, int status) throws UserException;

}

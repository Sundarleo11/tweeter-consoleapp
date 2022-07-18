package com.tweetapp.service;

import java.sql.Date;

import com.tweetapp.exception.UserException;

public interface RegisterService{
	
	void enterUserDetails() throws UserException;
	
	void registerUserService(String firstName,String lastName,String gender,Date dateOfBirth,String emailID,String password) throws UserException;
	
	boolean validateUser(String firstName,String lastName,String gender,Date dateOfBirth,String emailID,String password) throws UserException;

}

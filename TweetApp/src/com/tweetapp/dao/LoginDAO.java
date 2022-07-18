package com.tweetapp.dao;

public interface LoginDAO {
	
	boolean login(String userID,String password);
	
	void logOff(String UserID);

}

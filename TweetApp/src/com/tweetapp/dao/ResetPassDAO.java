package com.tweetapp.dao;

public interface ResetPassDAO {
	
	boolean resetPassword(String userID,String password);
	
	boolean checkCurrentPass(String userID,String password);

}

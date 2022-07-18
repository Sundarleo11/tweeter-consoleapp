package com.tweetapp.service;

import com.tweetapp.exception.UserException;

public interface TweetService {
	
	void enterTweet(String userID) throws UserException;
	
	void viewMyTweet(String userID);
	
	void viewAllTweets();
	
	void viewAllUsers(String userID);

}

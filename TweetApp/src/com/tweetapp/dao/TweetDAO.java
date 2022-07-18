package com.tweetapp.dao;

import java.sql.Date;
import java.util.List;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

public interface TweetDAO {

	void enterTweet(String userID,String tweet,Date date);
	
	List<Tweet> viewMyTweet(String userID);
	
	List<Tweet> viewAllTweets();
	
	List<User> viewAllUsers(String userID);
}

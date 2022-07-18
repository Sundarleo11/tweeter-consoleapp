package com.tweetapp.model;

import java.sql.Date;

public class Tweet {
	
	private String userId;
	private String tweetDesc;
	private Date enteredDate;
	
	
	public Tweet() {
		super();
	}

	public Tweet(String userId, String tweetDesc, Date enteredDate) {
		super();
		this.userId = userId;
		this.tweetDesc = tweetDesc;
		this.enteredDate = enteredDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTweetDesc() {
		return tweetDesc;
	}

	public void setTweetDesc(String tweet) {
		this.tweetDesc = tweet;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	@Override
	public String toString() {
		return "Tweet [userId=" + userId + ", tweet=" + tweetDesc + ", enteredDate=" + enteredDate + "]";
	}
	
	
	
	

}

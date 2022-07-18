package com.tweetapp.service.impl;

import com.tweetapp.dao.TweetDAO;
import com.tweetapp.dao.impl.TweetDAOImpl;
import com.tweetapp.exception.UserException;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetService;
import java.util.*;
import java.sql.Date;
public class TweetServiceImpl implements TweetService{

	static Scanner sc = new Scanner(System.in);
	static TweetDAO tweetDAO = new TweetDAOImpl();
	@Override
	public void enterTweet(String UserID) throws UserException {
		
		System.out.println("Type your Tweet here!");;
		String myTweet = sc.nextLine();
		
		boolean valid = validTweet(myTweet);
		
		Tweet tweet = new Tweet();
		tweet.setUserId(UserID);
		tweet.setTweetDesc(myTweet);
        tweet.setEnteredDate(new Date(Calendar.getInstance().getTime().getTime()));	
        
        tweetDAO.enterTweet(tweet.getUserId(), tweet.getTweetDesc(), tweet.getEnteredDate());
        System.out.println("Date "+tweet.getEnteredDate());
			
	}
	

	public boolean validTweet(String tweet) throws UserException {
		
		if(tweet == null || tweet.isEmpty()) {
			throw new UserException("Tweet should not be empty!");
		}
		
		if(tweet.length()<20 || tweet.length()>50) {
			System.out.println("length "+tweet.length());
			throw new UserException("Tweet length should be between 50 to 100 characters!...");
		}
		return true;
	}


	@Override
	public void viewMyTweet(String userID) {

		List<Tweet> tweets=tweetDAO.viewMyTweet(userID);
		if(tweets.isEmpty()) {
			System.out.println("You have no tweets!...");
		}
		else {
		System.out.println("UserID - "+userID+"\n");
		
		tweets.forEach(i->{
			
			System.out.println("Tweet -> "+i.getTweetDesc());
			System.out.println("Date -> "+i.getEnteredDate()+"\n");
			
		});
		
		
	}
	}


	@Override
	public void viewAllTweets() {
		List<Tweet> allTweets =tweetDAO.viewAllTweets();
		if(allTweets.isEmpty()) {
			System.out.println("No Tweets so far !...");
		}
		else {
			allTweets.forEach(i->{
				System.out.println("\nUserID -> "+i.getUserId());
				System.out.println("Tweet -> "+i.getTweetDesc());
				System.out.println("Date -> "+i.getEnteredDate()+"\n");
				
			});
		}
		
	}


	@Override
	public void viewAllUsers(String userID) {
		
		List<User> allUsers = tweetDAO.viewAllUsers(userID);
		
		if(allUsers.isEmpty()) {
			System.out.println("No users have logged in except you!...");
		} else {
			allUsers.forEach(i->{
				System.out.println("***************************");
				System.out.println("Name -> "+i.getFirstName()+" "+i.getLastName());
				System.out.println("Gender -> "+i.getGender());
				System.out.println("D.O.B -> "+i.getDob());
				System.out.println("EmailID -> "+i.getEmailID());
				System.out.println("***************************");
			});
		}
		
	}

}

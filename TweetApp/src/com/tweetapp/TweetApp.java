package com.tweetapp;

import java.util.*;


import com.tweetapp.exception.UserException;
import com.tweetapp.service.ForgetPasswordService;
import com.tweetapp.service.LoginService;
import com.tweetapp.service.RegisterService;
import com.tweetapp.service.ResetPasswordService;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.impl.ForgetPasswordServiceImpl;
import com.tweetapp.service.impl.LoginServiceImpl;
import com.tweetapp.service.impl.RegisterServiceImpl;
import com.tweetapp.service.impl.ResetPasswordServiceImpl;
import com.tweetapp.service.impl.TweetServiceImpl;
public class TweetApp {
	
	static Scanner sc = new Scanner(System.in);
	static RegisterService regService = new RegisterServiceImpl();
	static LoginService login = new LoginServiceImpl();
	static TweetService tweet = new TweetServiceImpl();
	static ResetPasswordService resetPasswordService= new ResetPasswordServiceImpl();
	static ForgetPasswordService forgetPass = new ForgetPasswordServiceImpl();

	public static void login(String userID){
		while(true)
		{
			try {

			System.out.println();
			System.out.println("2.View my tweets");
			System.out.println("3.View all tweets");
			System.out.println("4.View All Users");
			System.out.println("5.Reset Password");
			System.out.println("6.Logout\n");
			
			int ch = sc.nextInt();
			
			switch(ch) {
			
			case 1:
				
				try {
				System.out.println("1.Post a tweet");
				tweet.enterTweet(userID);
				break;
				}catch(UserException ex) {
					System.err.println(ex.getMessage());
					break;
				}
				
			
			case 2:
				System.out.println("2.View my tweets");
				tweet.viewMyTweet(userID);
				break;
				
			case 3:
				System.out.println("3.View all tweets");
				tweet.viewAllTweets();
				break;
				
			case 4:
				System.out.println("4.View All Users");
				tweet.viewAllUsers(userID);
				break;
				
			case 5:
				System.out.println("5.Reset Password");
				resetPasswordService.requestForResetPass(userID);
				break;
				
			case 6:
				System.out.println("6.Logout");
				login.logoff(userID);
				register();
				break;
				
			default:
				System.err.println("Please enter only above choices!...");
			}
		}catch(InputMismatchException  ex) {
    	    System.err.println("Please enter a number! " + ex.getMessage());
    	    sc.next(); 
    	    continue; 
		}
		}
	}
	
	public static void register() {
		
		while(true) {
			
			try {

			System.out.println("\n1.Register");
			System.out.println("2.Login");
			System.out.println("3.Forget Password");
			System.out.println("4.quit\n");
	        int choice = sc.nextInt();
			
			
			switch(choice) {
			case 1:				
				try {

				regService.enterUserDetails();
				break;
				
				}catch(UserException ex) {
					System.out.println(ex.getMessage());
					break;
				}
				
			case 2:				
				String userID = login.login();
				if(userID!=null)
				{
					System.out.println("Logged In!");
					login(userID);
				}
				else {
					System.err.println("Please check your credentials!");
				}		
				break;
				
			case 3:
				System.out.println("3.Forget Password");
				forgetPass.checkUserID();
				break;
			
			case 4:				
				System.exit(0);
				
			default:
				System.err.println("Please enter only above choices!...");
			}
           }catch(InputMismatchException  ex) {
        	    System.err.println("Please enter a number! " + ex.getMessage());
        	    sc.next(); 
        	    continue; 
			}
	}
}
	public static void main(String args[])
	{
		
		System.out.println("Welcome to Tweet App Application!");		
		TweetApp.register();		
		
	}
}


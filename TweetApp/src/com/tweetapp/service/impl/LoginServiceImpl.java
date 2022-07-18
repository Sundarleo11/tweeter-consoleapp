package com.tweetapp.service.impl;

import com.tweetapp.dao.LoginDAO;
import com.tweetapp.dao.impl.LoginDAOImpl;
import com.tweetapp.model.User;
import com.tweetapp.service.LoginService;

import java.util.*;

public class LoginServiceImpl implements LoginService{
	
	LoginDAO loginDAO = new LoginDAOImpl();
	Scanner sc = new Scanner(System.in);

	@Override
	public String login() {
		
		System.out.println("Enter you UserID:");
		String email = sc.next();
		System.out.println("Enter your password:");
		String password = sc.next();
		
		User user = new User();
		user.setEmailID(email);
		user.setPassword(password);
		
		boolean result = loginDAO.login(user.getEmailID(), user.getPassword());
		
		if(result) {
			return user.getEmailID();
		}
		
		return null;
	}

	@Override
	public void logoff(String userID) {
	
		loginDAO.logOff(userID);
		
	}

}

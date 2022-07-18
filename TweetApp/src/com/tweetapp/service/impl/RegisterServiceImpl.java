package com.tweetapp.service.impl;

import java.util.regex.Pattern;
import java.util.*;
import java.sql.Date;
import com.tweetapp.dao.RegisterDAO;
import com.tweetapp.dao.impl.RegisterDAOImpl;
import com.tweetapp.exception.UserException;
import com.tweetapp.model.User;
import com.tweetapp.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
	
	RegisterDAO registerDAO = new RegisterDAOImpl();
	
	static Scanner sc = new Scanner(System.in);
	
	@Override
	public void enterUserDetails() throws UserException {
		
		try {
		System.out.println("Enter your first name:");
		String firstName = sc.next();
		System.out.println("Enter your last name:");
		String lastName = sc.next();
		System.out.println("Enter your gender:");
		String gender = sc.next();
		System.out.println("Enter you date of birth:yyyy-mm-dd");
		String dob = sc.next();
		Date dateOfBirth =Date.valueOf(dob);
		System.out.println("Enter you emailID:");
		String emailID = sc.next();
		System.out.println("Enter your password:");
		String password = sc.next();
		
		User user = new User(firstName,lastName,gender,dateOfBirth,emailID,password);
		
		boolean valid = validateUser(user.getFirstName(),user.getLastName(),user.getGender(),user.getDob(),user.getEmailID(),user.getPassword());
		
		if(valid) {
			registerUserService(user.getFirstName(),user.getLastName(),user.getGender(),user.getDob(),user.getEmailID(),user.getPassword());
		}
		
	}catch(UserException ex){
		System.out.println(ex.getMessage());
		
	}catch(Exception ex) {
		System.out.println("Please enter a valid details!");
	}
	}

	@Override
	public void registerUserService(String firstName,String lastName,String gender,Date dateOfBirth,String emailID,String password) throws UserException {

	
		registerDAO.registerUser(firstName,lastName, gender,dateOfBirth,emailID,password, 0);
	}


	@Override
	public boolean validateUser(String firstName,String lastName,String gender,Date dateOfBirth,String emailID,String password) throws UserException{
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		         
        Pattern patUser = Pattern.compile(emailRegex);
        
        if (emailID == null || emailID.isEmpty()) {   	
           throw new UserException("emailID should not be empty!...");
        }
        
        boolean validUserID = patUser.matcher(emailID).matches();
        
        if(!validUserID) {
        	throw new UserException("Please enter a valid emailID!.. example -> abc@gmail.com");
        }
        
		if(password.length()<6 || password.length()>10)
		{
			throw new UserException("Passsword should be between 6 to 9 character!...");			
		}
		
		return true;
	}


	

}

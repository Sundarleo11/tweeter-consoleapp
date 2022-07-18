package com.tweetapp.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.util.Scanner;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.tweetapp.dao.ForgetPasswordDAO;
import com.tweetapp.dao.ResetPassDAO;
import com.tweetapp.dao.impl.ForgetPasswordDAOImpl;
import com.tweetapp.dao.impl.ResetPassDAOImpl;
import com.tweetapp.service.ForgetPasswordService;

public class ForgetPasswordServiceImpl implements ForgetPasswordService {

	static Scanner sc = new Scanner(System.in);
	ForgetPasswordDAO forgetPasswordDAO = new ForgetPasswordDAOImpl();
	ResetPassDAO resetPassDAO = new ResetPassDAOImpl();
	@Override
	public void checkUserID() {

		try {
			System.out.println("Enter your user id:");
			String userID = sc.next();
			System.out.println("Enter your Date Of Birth:yyyy-mm-dd");
			String dob = sc.next();
			Date dateOfBirth = Date.valueOf(dob);
			boolean result = forgetPasswordDAO.checkUserID(userID,dateOfBirth);

			if(result) {

				System.out.println("Enter the new Password:");
				String pass1 = sc.next();
				System.out.println("Re enter the password:");
				String pass2 = sc.next();

				ScriptEngine ee = new ScriptEngineManager().getEngineByName("Nashorn");
				ee.eval(new FileReader("C:\\Users\\Elcot\\Downloads\\TwitterConsole-main\\TweetApp\\src\\resources\\Js.js"));

				Invocable invocable = (Invocable)ee;
				boolean validPass = (boolean) invocable.invokeFunction("validate",pass1, pass2);

				if(validPass) {
					boolean reset = resetPassDAO.resetPassword(userID,pass1);
					if(reset) {
						System.out.println("Password has been reseted successfully!...");
					}
				}

			} else {
				System.err.println("Entered user id or date of birth is not correct!...");
			}
		}catch(FileNotFoundException e ) {
			System.err.println("File path is not correct!");
		}catch(ScriptException e) {
			System.err.println("Script Engine error -> "+e.getMessage());
		}catch(Exception e) {
			System.err.println("Something went wrong! "+e.getMessage());
		}
	}

}

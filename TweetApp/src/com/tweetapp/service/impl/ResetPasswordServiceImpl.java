package com.tweetapp.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.tweetapp.dao.ResetPassDAO;
import com.tweetapp.dao.impl.ResetPassDAOImpl;
import com.tweetapp.service.ResetPasswordService;

public class ResetPasswordServiceImpl implements ResetPasswordService {

	Scanner sc = new Scanner(System.in);
	ResetPassDAO resetPassDAO = new ResetPassDAOImpl();


	@Override
	public void requestForResetPass(String userID) {

		try {
			System.out.println("Enter your current password:");
			String current = sc.next();

			boolean validCurrentPass =resetPassDAO.checkCurrentPass(userID, current);
			if(validCurrentPass) {

				System.out.println("Enter the new password:");
				String newPass1 = sc.next();
				System.out.println("Re Enter the password:");
				String newPass2 = sc.next();

				ScriptEngine ee = new ScriptEngineManager().getEngineByName("Nashorn");

				ee.eval(new FileReader("C:\\Users\\Elcot\\Downloads\\TwitterConsole-main\\TweetApp\\src\\resources\\Js.js"));

				Invocable invocable = (Invocable)ee;
				boolean validPass = (boolean) invocable.invokeFunction("validate",newPass1, newPass2);

				if(validPass) {
					boolean reset = resetPassDAO.resetPassword(userID,newPass1);
					if(reset) {
						System.out.println("Password has been reseted successfully!...");
					}
				}

			}
			else {
				System.out.println("Please check your current password!...");
			}
		}catch(FileNotFoundException e ) {
			System.out.println("File path is not correct!");
		}catch(ScriptException e) {
			System.out.println("Script Engine error -> "+e.getMessage());
		}catch(Exception e) {
			System.out.println("Something went wrong! "+e.getMessage());
		}

	}


}

package com.tweetapp.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.tweetapp.dao.RegisterDAO;
import com.tweetapp.exception.UserException;


public class RegisterDAOImpl implements RegisterDAO{

	@Override
	public void registerUser(String firstName, String lastName, String gender, Date dateOfBirth, String emailID,
			String password, int status) throws UserException {

		try {

			String filePath = "C:\\Users\\Elcot\\Downloads\\TwitterConsole-main\\TweetApp\\src\\resources\\db.properties";
			Properties prop = new Properties();
			InputStream input = new FileInputStream(filePath);
			prop.load(input);

			String url = prop.getProperty("DB_CONNECTION");
			String userName = prop.getProperty("DB_USERNAME");
			String pass = prop.getProperty("DB_PASSWORD");

			Class.forName("com.mysql.cj.jdbc.Driver");
			//DriverManager.registerDriver(new OracleDriver());
			Connection conn = null;
			conn = DriverManager.getConnection(url, userName, pass);

			String checkUser = prop.getProperty("CHECK_USER");
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = conn.prepareStatement(checkUser);
			ps.setString(1, emailID);
			rs = ps.executeQuery();

			while(rs.next())
			{
				if(emailID.equalsIgnoreCase(rs.getString("EMAIL")))
				{
					throw new UserException(emailID +" is already available in db!");
				}
			}

			String regQuery = prop.getProperty("REG_QUERY");

			PreparedStatement ps1 = null;

			ps1 = conn.prepareStatement(regQuery);

			ps1.setString(1, firstName);
			ps1.setString(2, lastName);
			ps1.setString(3, gender);
			ps1.setDate(4, dateOfBirth);
			ps1.setString(5, emailID);
			ps1.setString(6, password);
			ps1.setInt(7, status);

			int count = ps1.executeUpdate();

			if(count>0) {
				System.out.println("Registration successfully completed!...");
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
			e.printStackTrace();
		}
	}


}

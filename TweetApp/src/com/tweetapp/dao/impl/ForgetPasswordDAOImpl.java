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

import com.tweetapp.dao.ForgetPasswordDAO;

public class ForgetPasswordDAOImpl implements ForgetPasswordDAO {

	@Override
	public boolean checkUserID(String userID,Date dob) {
		try {
			String filePath = "C:\\Users\\Elcot\\Downloads\\TwitterConsole-main\\TweetApp\\src\\resources\\db.properties";
			Properties prop = new Properties();
			InputStream input = new FileInputStream(filePath);
			prop.load(input);

			String url = prop.getProperty("DB_CONNECTION");
			String userName = prop.getProperty("DB_USERNAME");
			String pass = prop.getProperty("DB_PASSWORD");

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection(url, userName, pass);

			String checkUser = prop.getProperty("CHECK_USER_DOB");
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = conn.prepareStatement(checkUser);
			ps.setString(1, userID);
			ps.setDate(2, dob);
			rs = ps.executeQuery();

			while(rs.next())
			{
				if(userID.equalsIgnoreCase(rs.getString("EMAIL")) && dob.equals(rs.getDate("DOB")))
				{
					return true;
				}
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
		return false;
	}

}

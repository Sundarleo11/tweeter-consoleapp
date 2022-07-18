package com.tweetapp.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.tweetapp.dao.LoginDAO;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public boolean login(String userID, String password) {


		try {

			String filePath ="C:\\Users\\Elcot\\Downloads\\TwitterConsole-main\\TweetApp\\src\\resources\\db.properties";
			Properties prop = new Properties();
			InputStream input = new FileInputStream(filePath);
			prop.load(input);

			String url = prop.getProperty("DB_CONNECTION");
			String userName = prop.getProperty("DB_USERNAME");
			String pass = prop.getProperty("DB_PASSWORD");

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection(url, userName, pass);

			String loginQuery = prop.getProperty("LOGIN_QUERY");

			PreparedStatement ps = null;
			ResultSet  rs = null;

			ps = conn.prepareStatement(loginQuery);

			ps.setString(1, userID);
			ps.setString(2, password);

			rs = ps.executeQuery();

			while(rs.next())
			{
				String loginStatus = prop.getProperty("LOG_STATUS_UPDATE");
				PreparedStatement ps2 = null;

				ps2 = conn.prepareStatement(loginStatus);

				ps2.setString(1,userID);

				ps2.execute();

				return true;
			}


		}catch (FileNotFoundException e) {
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

	@Override
	public void logOff(String UserID) {

		try {

			String filePath = "C:\\Users\\Elcot\\Downloads\\TwitterConsole-main\\TweetApp\\src\\resources\\db.properties";
			Properties prop = new Properties();
			InputStream input = new FileInputStream(filePath);
			prop.load(input);

			String url = prop.getProperty("DB_CONNECTION");
			String userName = prop.getProperty("DB_USERNAME");
			String password = prop.getProperty("DB_PASSWORD");

			Class.forName("com.mysql.cj.jdbc.Driver");
			//DriverManager.registerDriver(new OracleDriver());
			Connection conn = null;
			conn = DriverManager.getConnection(url, userName, password);

			String logoffStatus = prop.getProperty("LOGOFF_STATUS_UPDATE");

			PreparedStatement ps = null;

			ps = conn.prepareStatement(logoffStatus);

			ps.setString(1, UserID);


			ps.execute();

		}catch (FileNotFoundException e) {
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

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

import com.tweetapp.dao.ResetPassDAO;

public class ResetPassDAOImpl implements ResetPassDAO {

	@Override
	public boolean checkCurrentPass(String userID, String password) {
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

			String loginQuery = prop.getProperty("CHECK_CUR_PASS");

			PreparedStatement ps = null;
			ResultSet  rs = null;

			ps = conn.prepareStatement(loginQuery);

			ps.setString(1, userID);
			ps.setString(2, password);

			rs = ps.executeQuery();

			while(rs.next())
			{
				String currentPass = rs.getString("PASSWORD");

				if(currentPass.equals(password)) {
					return true;
				}
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
	public boolean resetPassword(String userID,String password) {

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

			String resetPass = prop.getProperty("RESET_PASS");

			PreparedStatement ps = null;

			ps = conn.prepareStatement(resetPass);
			ps.setString(1, password);
			ps.setString(2, userID);


			int reset = ps.executeUpdate();

			if(reset>0) {
				return true;
			}



		}catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException "+e.getMessage());
		} catch (IOException e) {
			System.err.println("IOException "+e.getMessage());
		} catch (SQLException e) {
			System.err.println("SQLException "+e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException "+e.getMessage());
		}catch(Exception e) {
			System.err.println("Exception occured "+e.getMessage());
		}
		return false;
	}



}

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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.tweetapp.dao.TweetDAO;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

public class TweetDAOImpl implements TweetDAO{

	@Override
	public void enterTweet(String userID, String tweet, Date date) {

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

			String tweetQuery = prop.getProperty("INSERT_TWEET");

			PreparedStatement ps = null;

			ps = conn.prepareStatement(tweetQuery);

			ps.setString(1, userID);
			ps.setString(2, tweet);
			ps.setDate(3, date);

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

	@Override
	public List<Tweet> viewAllTweets() {
		List<Tweet> tweets = new ArrayList<Tweet>();

		try {

			Tweet tweetObj = new Tweet();
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

			String myTweet = prop.getProperty("VIEW_ALL_TWEETS");

			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(myTweet);

			rs =ps.executeQuery();

			while(rs.next()) {
				tweetObj.setUserId(rs.getString("USERID"));
				tweetObj.setTweetDesc(rs.getString("TWEET"));
				tweetObj.setEnteredDate(rs.getDate("DATE"));
				tweets.add(new Tweet(tweetObj.getUserId(),tweetObj.getTweetDesc(),tweetObj.getEnteredDate()));

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
		return tweets;
	}

	@Override
	public List<User> viewAllUsers(String userID) {
		List<User> users = new ArrayList<User>();

		try {

			User user = new User();
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

			String allUsers = prop.getProperty("VIEW_ALL_USERS");

			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(allUsers);
			ps.setString(1, userID);

			rs =ps.executeQuery();

			while(rs.next()) {
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setGender(rs.getString("GENDER"));
				user.setDob(rs.getDate("DOB"));
				user.setEmailID(rs.getString("EMAIL"));

				users.add(new User(user.getFirstName(),user.getLastName(),user.getGender(),user.getDob(),user.getEmailID(),null));
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

		return users;
	}

	@Override
	public List<Tweet> viewMyTweet(String userID) {
		List<Tweet> tweets = new ArrayList<Tweet>();
		try {


			Tweet tweetObj = new Tweet();
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

			String myTweet = prop.getProperty("VIEW_MY_TWEET");

			PreparedStatement ps = null;
			ResultSet rs = null;

			ps = conn.prepareStatement(myTweet);
			ps.setString(1, userID);

			rs =ps.executeQuery();

			while(rs.next()) {
				tweetObj.setUserId(rs.getString("USERID"));
				tweetObj.setTweetDesc(rs.getString("TWEET"));
				tweetObj.setEnteredDate(rs.getDate("DATE"));
				tweets.add(new Tweet(tweetObj.getUserId(),tweetObj.getTweetDesc(),tweetObj.getEnteredDate()));

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
		return tweets;
	}

}

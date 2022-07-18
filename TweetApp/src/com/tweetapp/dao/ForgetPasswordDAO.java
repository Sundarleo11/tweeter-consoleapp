package com.tweetapp.dao;

import java.sql.Date;

public interface ForgetPasswordDAO {

	boolean checkUserID(String userID,Date dob);
}

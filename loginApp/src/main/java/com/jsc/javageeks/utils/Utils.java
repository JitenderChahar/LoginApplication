package com.jsc.javageeks.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsc.javageeks.domain.User;

public class Utils {
	public static User setUserFields(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt("id"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		user.setDate(resultSet.getDate("date"));
		return user;
	}
}

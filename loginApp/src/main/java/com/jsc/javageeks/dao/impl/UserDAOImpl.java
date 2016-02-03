package com.jsc.javageeks.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jsc.javageeks.dao.UserDAO;
import com.jsc.javageeks.domain.User;
import com.jsc.javageeks.utils.DatabaseUtils;
import com.jsc.javageeks.utils.Utils;

public class UserDAOImpl implements UserDAO {

	DatabaseUtils databaseUtils = new DatabaseUtils();
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	public int saveUser(User user) {
		logger.info("Inside saveUser method of UserDAOImpl class");
		Connection con = DatabaseUtils.createConnection();
		String query = "INSERT INTO users VALUES (" + user.getId() + ",'" + user.getEmail() + "','" + user.getPassword()
				+ "'," + user.getDate() + ");";
		int rowInserted = 0;
		try {
			rowInserted = DatabaseUtils.runQuery(con, query);
		} catch (SQLException exception) {
			logger.error(exception.getMessage());
		} finally {
			DatabaseUtils.closeConnection(con);
		}
		return rowInserted;
	}

	public User getUserById(int userId) {
		logger.info("Inside getUserById method of UserDAOImpl class");
		Connection con = DatabaseUtils.createConnection();
		String query = "SELECT * FROM users where id=" + userId + ";";
		User user = null;
		try {
			ResultSet resultSet = DatabaseUtils.getResultSet(con, query);
			if (resultSet.next()) {
				user = Utils.setUserFields(resultSet);
			}
		} catch (SQLException exception) {
			logger.error(exception.getMessage());
		} finally {
			DatabaseUtils.closeConnection(con);
		}
		return user;
	}

	public User getUserByEmail(String email) {
		logger.info("Inside getUserByEmail method of UserDAOImpl class");
		Connection con = DatabaseUtils.createConnection();
		String query = "SELECT * FROM users where email='" + email + "';";
		User user = null;
		try {
			ResultSet resultSet = DatabaseUtils.getResultSet(con, query);
			if (resultSet.next()) {
				user = Utils.setUserFields(resultSet);
			}
		} catch (SQLException exception) {
			logger.error(exception.getMessage());
		} finally {
			DatabaseUtils.closeConnection(con);
		}
		return user;
	}

	public List<User> getAllUsers() {
		logger.info("Inside getAllUsers method of UserDAOImpl class");
		Connection con = DatabaseUtils.createConnection();
		String query = "SELECT * FROM users";
		List<User> users = new ArrayList<User>();
		try {
			ResultSet resultSet = DatabaseUtils.getResultSet(con, query);
			if (resultSet.next()) {
				do {
					User user = Utils.setUserFields(resultSet);
					users.add(user);
				} while (resultSet.next());
			}
		} catch (SQLException exception) {
			logger.error(exception.getMessage());
		} finally {
			DatabaseUtils.closeConnection(con);
		}
		return users;
	}

}

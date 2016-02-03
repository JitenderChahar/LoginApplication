package com.jsc.javageeks.service.impl;

import java.util.List;

import com.jsc.javageeks.application.Constant;
import com.jsc.javageeks.application.StatusCode;
import com.jsc.javageeks.dao.UserDAO;
import com.jsc.javageeks.dao.impl.UserDAOImpl;
import com.jsc.javageeks.domain.Status;
import com.jsc.javageeks.domain.User;
import com.jsc.javageeks.service.DatabaseService;

public class DatabaseServiceImpl implements DatabaseService {
	private UserDAO userDAO = new UserDAOImpl();

	public Status addUser(User user) {
		Status status = new Status();
		int rowEffected = userDAO.saveUser(user);
		if (rowEffected == 0) {
			status.setMessage(Constant.NO_ROW_EFFECTED_MSG);
			status.setStatusCode(StatusCode.NO_ROW_EFFECTED);
		} else {
			status.setMessage(Constant.ROW_INSERTED_MSG);
			status.setStatusCode(StatusCode.ROW_INSERTED);
		}
		return status;
	}

	public User getUser(int userId) {
		return userDAO.getUserById(userId);
	}

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public User authenticateUser(User user) {
		return userDAO.getUserByEmail(user.getEmail());
	}

}

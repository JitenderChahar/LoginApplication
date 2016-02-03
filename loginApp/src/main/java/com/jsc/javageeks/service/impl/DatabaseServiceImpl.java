package com.jsc.javageeks.service.impl;

import java.util.List;

import com.jsc.javageeks.application.Constant;
import com.jsc.javageeks.application.StatusCode;
import com.jsc.javageeks.dao.UserDAO;
import com.jsc.javageeks.dao.impl.UserDAOImpl;
import com.jsc.javageeks.domain.Status;
import com.jsc.javageeks.domain.StatusUserCombined;
import com.jsc.javageeks.domain.User;
import com.jsc.javageeks.exception.GenericException;
import com.jsc.javageeks.service.DatabaseService;

public class DatabaseServiceImpl implements DatabaseService {
	private UserDAO userDAO = new UserDAOImpl();

	public Status addUser(User user) throws GenericException {
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

	public User getUser(int userId) throws GenericException {
		return userDAO.getUserById(userId);
	}

	public List<User> getAllUsers() throws GenericException {
		return userDAO.getAllUsers();
	}

	public StatusUserCombined authenticateUser(User user) throws GenericException {
		User userInDB = userDAO.getUserByEmail(user.getEmail());
		StatusUserCombined statusUserCombined = new StatusUserCombined();
		Status status = new Status();
		if (userInDB == null) {
			status.setMessage(Constant.INVALID_EMAIL_MSG);
			status.setStatusCode(StatusCode.INVALID_EMAIL);
			statusUserCombined.setStatus(status);
		} else {
			if (userInDB.getPassword().equals(user.getPassword())) {
				statusUserCombined.setUser(userInDB);
			} else {
				status.setMessage(Constant.INVALID_PASSWORD_MSG);
				status.setStatusCode(StatusCode.INVALID_PASSWORD);
				statusUserCombined.setStatus(status);
			}
		}
		return statusUserCombined;
	}

}

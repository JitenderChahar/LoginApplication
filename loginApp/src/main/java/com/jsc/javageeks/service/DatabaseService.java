package com.jsc.javageeks.service;

import java.util.List;

import com.jsc.javageeks.domain.Status;
import com.jsc.javageeks.domain.StatusUserCombined;
import com.jsc.javageeks.domain.User;
import com.jsc.javageeks.exception.GenericException;

public interface DatabaseService {
	Status addUser(User user) throws GenericException;

	User getUser(int userId) throws GenericException;

	List<User> getAllUsers() throws GenericException;

	StatusUserCombined authenticateUser(User user) throws GenericException;
}

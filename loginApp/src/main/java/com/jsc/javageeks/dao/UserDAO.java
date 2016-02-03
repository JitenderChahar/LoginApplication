package com.jsc.javageeks.dao;

import java.util.List;

import com.jsc.javageeks.domain.User;
import com.jsc.javageeks.exception.GenericException;

public interface UserDAO {
	int saveUser(User user) throws GenericException;

	User getUserById(int userId) throws GenericException;

	List<User> getAllUsers() throws GenericException;

	User getUserByEmail(String email) throws GenericException;
}

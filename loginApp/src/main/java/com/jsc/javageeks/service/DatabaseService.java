package com.jsc.javageeks.service;

import java.util.List;

import com.jsc.javageeks.domain.Status;
import com.jsc.javageeks.domain.User;

public interface DatabaseService {
	Status addUser(User user);

	User getUser(int userId);
	
	List<User> getAllUsers();

	User authenticateUser(User user);
}

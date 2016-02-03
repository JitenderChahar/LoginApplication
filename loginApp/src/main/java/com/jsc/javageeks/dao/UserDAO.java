package com.jsc.javageeks.dao;

import java.util.List;

import com.jsc.javageeks.domain.User;

public interface UserDAO {
	int saveUser(User user);

	User getUserById(int userId);

	List<User> getAllUsers();

	User getUserByEmail(String email);
}

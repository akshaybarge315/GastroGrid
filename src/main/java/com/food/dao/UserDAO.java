package com.food.dao;

import java.util.List;

import com.food.model.User;

public interface UserDAO {
	void addUser(User user);
	User getUser(int userId);
	User getUserByUsername(String username);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUser();
}

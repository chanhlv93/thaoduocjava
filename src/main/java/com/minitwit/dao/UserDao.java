package com.minitwit.dao;

import com.minitwit.model.User;

public interface UserDao {
	
	User getUserbyUsername(String username);
	
	void registerUser(User user);
}

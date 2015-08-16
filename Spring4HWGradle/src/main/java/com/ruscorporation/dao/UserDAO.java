package com.ruscorporation.dao;

import java.util.List;

import com.ruscorporation.model.User;

public interface UserDAO {
	User getUser(String userName);

	void saveOrUpdate(User user);

	List<User> list();
}

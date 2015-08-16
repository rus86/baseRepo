package com.ruscorporation.service;

import java.util.List;

import com.ruscorporation.exceptions.InvalidUserException;
import com.ruscorporation.model.User;

public interface UserManager {

	User getUser(String userName);

	void saveOrUpdate(User user) throws InvalidUserException;
	
	List <User> list();
}

package com.ruscorporation.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.ruscorporation.dao.impl.UserDaoImpl;
import com.ruscorporation.exceptions.InvalidUserException;
import com.ruscorporation.model.User;
import com.ruscorporation.service.UserManager;

@Service("userManager")
public class UserManagerImpl implements UserManager {

	@Autowired
	@Resource
	private UserDaoImpl userDao;
	
	@PostConstruct
	public void init(){
		System.out.println("========User Manager impl was initialized=========");
	}
	
	@Autowired
	public void doSomething(){
		System.out.println("====Invoke doSomething====");
	}

	public User getUser(String userName) {
		return userDao.getUser(userName);
	}

	public void saveOrUpdate(User user) throws InvalidUserException {
		if (user == null)
			throw new InvalidUserException("Empty user object");
		
		userDao.saveOrUpdate(user);

	}

	public List<User> list() {
		return userDao.list();
	}

}

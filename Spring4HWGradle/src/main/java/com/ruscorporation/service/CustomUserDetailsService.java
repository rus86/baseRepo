package com.ruscorporation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ruscorporation.model.User;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserManager userManager;

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		User user = userManager.getUser(userName);
		
		return user;
		
		
	}

}

package com.ruscorporation.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ruscorporation.dao.UserDAO;
import com.ruscorporation.model.SystemUser;
import com.ruscorporation.model.UserRole;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		SystemUser systemUser = userDAO.getUserByUserName(username);

		List<GrantedAuthority> authorities = buildUserAuthority(systemUser
				.getUserRoles());

		return buildUserForAuthentication(systemUser, authorities);

	}

	// Converts SystemUser user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(SystemUser systemUser,
			List<GrantedAuthority> authorities) {
		return new User(systemUser.getUserName(), systemUser.getPassword(),
				systemUser.isActive(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(
				setAuths);

		return result;
	}

}

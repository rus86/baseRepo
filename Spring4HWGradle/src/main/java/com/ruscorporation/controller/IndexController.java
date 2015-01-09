package com.ruscorporation.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruscorporation.exceptions.InvalidUserException;
import com.ruscorporation.model.Role;
import com.ruscorporation.model.User;
import com.ruscorporation.service.ContactService;
import com.ruscorporation.service.UserManager;

@Controller
public class IndexController {
	
	//TODO: 1. Add user func 2. REST API 3. Create annotation
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private UserManager userManager;

	@RequestMapping("/main")
	public String hello(
			@RequestParam(value = "name", required = false, defaultValue = "Ruslan") String name,
			ModelMap model) {
		
		contactService.list();
		
		model.addAttribute("rusParam", name);
		return "main";
	}
	
	@RequestMapping("/authorize")
	public String authorize(){
//		Role role = new Role();
//		role.setName("ROLE_ADMIN");
//		role.setDescription("Role admin");
		
		Role role2 = new Role();
		role2.setName("ROLE_USER");
		role2.setDescription("Role user");
		
		
		User user = new User();
		user.setUsername("rus");
		user.setPassword("1234567");
		user.setEnabled(true);
		
		HashSet<Role> roles = new HashSet<Role>();
//		roles.add(role);
		roles.add(role2);
		user.setRoles(roles);
		
		try {
			userManager.saveOrUpdate(user);
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
		
		return "authorize";
	}

}

package com.ruscorporation.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ruscorporation.exceptions.InvalidUserException;
import com.ruscorporation.model.User;
import com.ruscorporation.service.UserManager;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private UserManager userManager;

	// @RequestMapping("/admin/index")
	// public String index(){
	// ModelAndView modelAndView = new ModelAndView();
	// modelAndView.setViewName("indexPage");
	// modelAndView.addObject("message", "Hi Rus!");
	// return "/admin/index";
	// }

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/index");
		modelAndView.addObject("message", "Hi Rus!");
		return modelAndView;
	}

	// Users list route
	// @RequestMapping(value="/userList", method=RequestMethod.GET)
	// public ModelAndView userList(){
	// ModelAndView modelAndView = new ModelAndView();
	// List<User> userList = userManager.list();
	// modelAndView.addObject("userList", userList);
	// modelAndView.setViewName("/admin/userList");
	// return modelAndView;
	// }

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String userList(Model model) {
		List<User> userList = userManager.list();
		model.addAttribute("userList", userList);
		return "/admin/userList";
	}

	// @RequestMapping(value = "/userList", method = RequestMethod.GET)
	// public String userList() {
	// return "/admin/userList";
	// }
	//
	// //Get users list
	// @ModelAttribute("userList")
	// public List<User> getUserList() {
	// return userManager.list();
	// }

	// Call add new user
	@RequestMapping(value = "/userCallAdd")
	public String callAddNewUser(Model model) {
		logger.debug("Call add new user");
		model.addAttribute("user", new User());
		return "/admin/userCreate";
	}

	// Save new user
	@RequestMapping(value = "/userSave", method = RequestMethod.POST)
	public String save(@ModelAttribute("user") User user, Model model) {
		logger.debug("Trying to save user: " + user.getUsername());
		try {
			long start = System.currentTimeMillis();
			userManager.saveOrUpdate(user);
			System.out.println("User put operation completed, spent time=" + (System.currentTimeMillis()-start) + " ms.");
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
		model.addAttribute("userList", userManager.list());
		logger.debug("User: " + user.getUsername() + " was successfylly saved.");
		return "/admin/userList";
	}

}

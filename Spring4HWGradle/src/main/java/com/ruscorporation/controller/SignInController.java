package com.ruscorporation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class SignInController {

	private Logger logger = LogManager.getLogger(getClass());

	@RequestMapping("/login")
	public String signin() {
		logger.debug("Call login...");
		return "login";
	}
	
	@RequestMapping("/testAJAX")
	public String testAJAX() {
		logger.debug("Call testAJAX...");
		return "testAJAX";
	}

	@RequestMapping("/signin-failure")
	public String signinFailure(ModelMap model) {
		logger.debug("Signin failure...");
		model.addAttribute("message", "Wrong login or password!");
		return "login";
	}

	@RequestMapping("/access-denied")
	public String accessDenied(ModelMap model) {		
		logger.debug("Access denied...");
		model.addAttribute("message", "Access denied!");
		return "login";
	}

}

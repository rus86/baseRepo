package com.ruscorporation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

@Controller
public class IndexController {
	
	private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(){
		String methodName = "index()";
		logger.debug("Start index method. Called {} method", methodName);
		//Do something
		
		//Print internal state
	    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	    StatusPrinter.print(lc);

		logger.debug("Finish index method");
		return "index";
		
	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(){
		logger.debug("Initiate login");
		return "login";
	}
	
	@RequestMapping("/main")
	public String main(){
		
		return "main";
	}

}

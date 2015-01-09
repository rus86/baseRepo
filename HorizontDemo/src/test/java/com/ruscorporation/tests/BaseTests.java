package com.ruscorporation.tests;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.ruscorporation.config.ApplicationConfig;
import com.ruscorporation.tests.configuration.TestApplicationConfig;

//Set Junit test runner (for init Spring app context)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ApplicationConfig.class, TestApplicationConfig.class})
//mock ServletContext required by your MVC configuration
@WebAppConfiguration
@Transactional
public class BaseTests {
	
	//Used for MockMVC
	@Autowired
    protected WebApplicationContext webApplicationContext;

}

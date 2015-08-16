package com.ruscorporation.tests;

import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

//
/**
 * Base test configuration
 * @author rus
 * We need to import both context configuration file:
 *  -applicationContextConfig(dispatcher-servlet.xml) from application;
 *  -applicationContextConfig for test(dispatcher-servlet-tests.xml) without
 *  	Spring config(only DB settings)
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml",
		"classpath:dispatcher-servlet-tests.xml" })
@Transactional
public class BaseTest {
	
	
}

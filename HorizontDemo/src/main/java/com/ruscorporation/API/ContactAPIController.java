package com.ruscorporation.API;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruscorporation.model.Contact;
import com.ruscorporation.service.ContactService;

@RestController
@RequestMapping(value="/api/contact")
public class ContactAPIController {
	
	@Autowired
	private ContactService contactService; 
	
	private static final Logger logger = LoggerFactory
			.getLogger(ContactAPIController.class);
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Contact> getContactsList(){
		logger.debug("---Get contact list API init---");
		return contactService.list();
	}
	
	//Attribute 'produces' set explicitly response type (JSON/XML)
	//Response type depends from 'Accept' attribute in request header (application/xml/json)
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public Contact getContactById(@PathVariable Integer id){
		logger.debug("---Get contact by ID API init---");
		return contactService.get(id);
	}
	
	@RequestMapping(value="/getByLicense/{licenseId}", method=RequestMethod.GET)
	public Contact getContactByLicenseId(@PathVariable("licenseId") String id){
		logger.debug("---Get contact by driver license ID API init---");
		return contactService.getContactByDriverLicense(id);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createContact(@RequestBody Contact contact){
		logger.debug("---Create contact---");
		contactService.saveOrUpdate(contact);
	}


}

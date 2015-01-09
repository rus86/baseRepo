package com.ruscorporation.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.envers.AuditReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruscorporation.model.Contact;
import com.ruscorporation.model.ContactItem;
import com.ruscorporation.model.ContactValue;
import com.ruscorporation.service.ContactService;
import com.ruscorporation.validation.ContactValidator;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {

	private static final String CONTACT_LIST_ATTR = "contactList";

	private static final Logger logger = LoggerFactory
			.getLogger(ContactController.class);

	@Autowired
	private ContactService contactService;

	/**
	 * Get contact list
	 * 
	 * @return forward to contact list page
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String contactList(Model model) {
		logger.debug("Start contactList() method");

		List<Contact> contactList = contactService.list();
		model.addAttribute(CONTACT_LIST_ATTR, contactList);

		logger.debug("Finish contactList() method");
		return "/contact/list";
	}

	/**
	 * Call add contact page
	 * 
	 * @return contact page
	 */
	@RequestMapping(value = "/callCreate", method = RequestMethod.GET)
	private String callCreateContact(Model model) {
		logger.debug("Call create contact");
		model.addAttribute("contact", new Contact());
		return "/contact/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	private String createContact(
			@ModelAttribute("contact") @Valid Contact contact,
			BindingResult bindingResult, Model model) {
		logger.debug("Create contact");
		// Base (annotation) validation
		if (bindingResult.hasErrors()) {
			logger.debug("Validation error");
			return "/contact/create";
		}

		// Custom validation
		ContactValidator contactValidator = new ContactValidator();
		contactValidator.validate(contact, bindingResult);
		if (bindingResult.hasErrors()) {
			logger.debug("Custom validation error");
			return "/contact/create";
		}
		contactService.saveOrUpdate(contact);

		return "redirect:/contact/list";
	}

	/**
	 * Call update contact page
	 * 
	 * @return contact page
	 */
	@RequestMapping(value = "/callUpdate/{id}", method = RequestMethod.GET)
	private String callUpdateContact(@PathVariable int id, Model model) {
		logger.debug("Call update contact with id={}", id);
		Contact contact = contactService.get(id);
		model.addAttribute("contact", contact);
		return "/contact/update";
	}

	/**
	 * Update contact
	 * 
	 * @param contact
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private String updateContact(@ModelAttribute("contact") Contact contact,
			Model model) {
		logger.debug("Update contact");
		contactService.saveOrUpdate(contact);

		return "redirect:/contact/list";
	}

	/**
	 * Delete contact
	 * 
	 * @return contact page
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	private String deleteContact(@PathVariable int id) {
		logger.debug("Delete  contact with id={}", id);
		Contact contact = contactService.get(id);
		contactService.delete(contact);
		return "redirect:/contact/list";
	}

	/**
	 * We manually throw error
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	private String exceptionContact() {
		if (true) {
			throw new NullPointerException("My NullPointer error");
		}
		return null;
	}

	/**
	 * Handle all NullPointer and IOExceptions
	 * 
	 * @return
	 */
	@ExceptionHandler({ NullPointerException.class, IOException.class })
	private String handleErrors() {
		logger.debug("Handle some error");
		return "redirect:/contact/list";
	}

	//TODO:Refactor this method. Move business logic to service layer
	@RequestMapping(value = "/audit/{userId}", method=RequestMethod.GET)
	private String auditContact(@PathVariable int userId, Model model) {
		
		//Create audit reader
		AuditReader auditReader = contactService.getAuditReader();
		
		//Get revisions numbers
		List<Number> revisions = contactService.getRevisions(userId);
		for (Number number : revisions) {
			System.out.println("Revision number=" + number);
		}
		
		//TODO: Move all business logic to DAO layer

		
//		//Select ALL revisions for 'Contact' entity 
//		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(Contact.class, false,
//				false);
//		//Set some restrictions with AuditEntity
//		auditQuery.add(AuditEntity.revisionNumber().gt(1));
//		
//		//Get result
//		List resultList = auditQuery.getResultList();
		
		return "/contact/audit";
	}
	
	@RequestMapping(value = "/add/contactItem", method = RequestMethod.GET)
	private String createContactItem() {
		
		ContactItem contactItem = new ContactItem();
		contactItem.setAddress("Artema str1");
		contactItem.setAge((short) 24);
		contactItem.setFirstName("Ivan");
		contactItem.setLastName("Petrov");
		contactService.saveOrUpdate(contactItem);
		return null;
	}
	
	@RequestMapping(value = "/add/contactValue", method = RequestMethod.GET)
	private String createContactValue() {
		
		ContactValue contactvalue = new ContactValue();
		contactvalue.setPhone("+380991234567");
		contactvalue.setAge((short) 24);
		contactvalue.setFirstName("Ivan");
		contactvalue.setLastName("Petrov");
		contactService.saveOrUpdate(contactvalue);
		return null;
	}
	

}

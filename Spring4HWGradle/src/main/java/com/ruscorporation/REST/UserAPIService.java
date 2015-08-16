package com.ruscorporation.REST;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruscorporation.model.Contact;
import com.ruscorporation.model.Greeting;
import com.ruscorporation.model.User;
import com.ruscorporation.service.ContactService;
import com.ruscorporation.service.UserManager;

@RestController
public class UserAPIService {

	@Autowired
	private UserManager userManager;

	@Autowired
	private ContactService contactService;

	@RequestMapping("/api/getUser")
	public User getUser(
			@RequestParam(value = "userName", required = true) String userName) {
		User user = userManager.getUser(userName);
		return user;
	}

	@RequestMapping("/api/greeting")
	public Greeting greeeting() {
		return new Greeting();
	}

	@RequestMapping("/api/contact/get")
	public Contact getContact(Integer id, String name) {
		if (id != null)
			return contactService.get(id);
		else
			return contactService.getByName(name);
	}

	@RequestMapping(value = "/api/contact/list/{isTest}", method = RequestMethod.GET)
	public List<Contact> getContactList(@PathVariable("isTest") String isTest) {
		System.out.println("Test items=" + isTest);
		return contactService.list();
	}

	@RequestMapping(value = "/api/execute", method = RequestMethod.GET)
	public String getResult() {
		String result = "Empty result";
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> future = executorService.submit(new ExecuteMethod());
		try {
			if (future.isDone()) {
				System.out.println("Result2=" + future.get());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/api/contact/add", method = RequestMethod.PUT)
	public void addContact(@RequestBody Contact contact) {
		contactService.saveOrUpdate(contact);
	}

	@RequestMapping(value = "/api/contact/create", method = RequestMethod.POST)
	public void createContact(@RequestBody Contact contact) {
		contactService.saveOrUpdate(contact);
	}

	@RequestMapping(value = "/api/contact/remove/{id}", method = RequestMethod.DELETE)
	public void removeContact(@PathVariable("id") Integer id) {
		Contact contact = contactService.get(id);
		contactService.delete(contact);
	}

}

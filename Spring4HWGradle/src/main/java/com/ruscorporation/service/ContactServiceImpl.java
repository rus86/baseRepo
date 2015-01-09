package com.ruscorporation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruscorporation.dao.ContactDAO;
import com.ruscorporation.model.Contact;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO contactDAO;

	public List<Contact> list() {
		return contactDAO.list();
	}

	public void saveOrUpdate(Contact contact) {
		contactDAO.saveOrUpdate(contact);

	}

	public Contact get(Integer id) {
		return contactDAO.get(id);
	}

	public void delete(Contact contact) {
		contactDAO.delete(contact);

	}

	public Contact getByName(String name) {
		return contactDAO.getByName(name);
	}

}

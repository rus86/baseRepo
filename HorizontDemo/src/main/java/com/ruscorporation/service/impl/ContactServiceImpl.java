package com.ruscorporation.service.impl;

import java.util.List;

import org.hibernate.envers.AuditReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruscorporation.dao.ContactDAO;
import com.ruscorporation.model.Contact;
import com.ruscorporation.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO contactDAO;

	public Contact getContactByDriverLicense(String driverLicense) {
		return contactDAO.getContactByDriverLicense(driverLicense);
	}

	public void saveOrUpdate(Contact contact) {
		contactDAO.saveOrUpdate(contact);
	}

	public List<Contact> list() {
		return contactDAO.list();
	}

	public void delete(Contact contact) {
		contactDAO.delete(contact);
	}

	public Contact get(Integer id) {
		return contactDAO.get(id);
	}

	public void merge(Contact contact) {
		contactDAO.merge(contact);

	}

	public AuditReader getAuditReader() {
		return contactDAO.getAuditReader();
	}

	@Override
	public List<Number> getRevisions(Integer userId) {
		return contactDAO.getRevisions(userId);
	}

}

package com.ruscorporation.service;

import java.util.List;

import org.hibernate.envers.AuditReader;

import com.ruscorporation.model.Contact;

public interface ContactService {
	
	void saveOrUpdate(Contact contact);
	
	List<Contact> list();
	
	void delete(Contact contact);

	Contact getContactByDriverLicense(String driverLicense);
	
	Contact get(Integer id);
	
	void merge(Contact contact);
	
	AuditReader getAuditReader();
	
	List<Number> getRevisions(Integer userId);

}

package com.ruscorporation.dao;

import java.util.List;

import com.ruscorporation.model.Contact;

public interface ContactDAO extends BaseDAO<Contact>{

	Contact getContactByDriverLicense(String driverLicense);
	
	List<Number> getRevisions(Integer userId);
	
}

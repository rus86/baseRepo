package com.ruscorporation.dao;

import java.util.List;

import com.ruscorporation.model.Contact;

public interface ContactDAO {
	
	List<Contact> list();
	void saveOrUpdate(Contact contact);
	Contact get(Integer id);
	void delete(Contact contact);
	Contact getByName(String name);

}

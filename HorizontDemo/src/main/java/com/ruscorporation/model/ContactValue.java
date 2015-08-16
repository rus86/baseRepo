package com.ruscorporation.model;

import javax.persistence.Entity;

@Entity
public class ContactValue extends Contact{
	
	private static final long serialVersionUID = 1L;

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}

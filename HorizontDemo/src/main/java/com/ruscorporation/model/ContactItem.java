package com.ruscorporation.model;

import javax.persistence.Entity;

@Entity
public class ContactItem extends Contact {

	private static final long serialVersionUID = 1L;

	private String address;
	private String zip;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}

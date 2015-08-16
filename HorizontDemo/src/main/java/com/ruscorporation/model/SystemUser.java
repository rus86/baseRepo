package com.ruscorporation.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "system_user")
public class SystemUser implements Serializable {

	private static final long serialVersionUID = 4687502825693472792L;

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private Boolean active;
	private Set<UserRole> userRoles = new HashSet<UserRole>();

	public SystemUser() {
		
	}

	@Id
	@Column(name="username", nullable=false, unique=true)
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	@Column(name = "active", columnDefinition = "boolean default true")
	public Boolean isActive() {
		return active;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}

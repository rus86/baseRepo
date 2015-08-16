package com.ruscorporation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole implements Serializable{
	
	private static final long serialVersionUID = 2595855767988398464L;

	private Integer id;
	private String role;
	private SystemUser user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	public String getRole() {
		return role;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="username")
	public SystemUser getUser() {
		return user;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setUser(SystemUser user) {
		this.user = user;
	}

	

}

package com.ruscorporation.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = 2639420604912655724L;

	private Long id;
	private String username;
	private String password;
	private Set<Role> roles = new HashSet<Role>();
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	private boolean enabled;
	
	
	/**
	 * Default constructor
	 */
	public User() {
	}

	/**
	 * Constructor
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public Set<Role> getRoles() {
		return roles;
	}

	@Column
	public boolean isAccountExpired() {
		return accountExpired;
	}

	@Column
	public boolean isAccountLocked() {
		return accountLocked;
	}

	@Column
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	@Transient
	public Set<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
		authorities.addAll(roles);
		return authorities;
	}

	@Column
	public String getPassword() {
		return password;
	}

	@Column(nullable=false, unique=true)
	public String getUsername() {
		return username;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	@Transient
	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return !isCredentialsExpired();
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


}

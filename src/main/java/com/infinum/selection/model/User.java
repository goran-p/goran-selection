package com.infinum.selection.model;

/**
 * Class representing system database table USER.
 * It contains informations about system users.
 */
public class User {

	private Long token;
	private String email;
	private String password;
	
	private User() {
		// empty
	}
	
	public User(final Long token, final String email) {
		this.token=token;
		this.email=email;
	}
	
	public Long getToken() {
		return token;
	}
	public void setToken(Long token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [token=" + token + ", email=" + email + ", password=" + password + "]";
	}
}

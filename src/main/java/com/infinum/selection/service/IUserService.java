package com.infinum.selection.service;

/**
 * Interface containing list of methods for manipulating user data.
 */
public interface IUserService {
	
	/**
	 * Method contains logic for registering new user into the system.
	 * @param email		user email
	 * @param password	user password
	 */
	void register(final String email, final String password);
	
	/**
	 * Method contains logic for logging user into the system.
	 * @param email		user email
	 * @param password	user password
	 * @return			distinct user token id
	 */
	Long login(final String email, final String password);
	
	/**
	 * Method contains logic for encrypting users password before saving it into the system.
	 * @param password	unencrypted user password 
	 * @return			encrypted user password
	 */
	String hashPassword(final String password);
}

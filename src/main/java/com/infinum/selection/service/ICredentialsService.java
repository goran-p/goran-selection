package com.infinum.selection.service;

/**
 * Interface containing list of methods for validation of system user credentials.
 */
public interface ICredentialsService {

	/**
	 * Checking if provided email address have valid format - user@domain.com <br>
	 * For email address to be valid it must consist of three parts: local part, at sign and domain part.
	 * All three parts must be present and in the above mentioned order.
	 * @param email	provided email address
	 */
	void checkEmailValidity(final String email);
	
	/**
	 * Checking if provided password have valid format -
	 * must be at least 8 characters long and must have at least one digit. <br>
	 * @param password	provided password
	 */
	void checkPasswordValidity(final String password);
}

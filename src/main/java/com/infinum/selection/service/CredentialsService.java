package com.infinum.selection.service;

import org.springframework.stereotype.Service;

import com.infinum.selection.exception.InvalidCredentialsException;

/**
 * Service class containing logic for validation of user credentials (email address and password) used for user registration.
 */
@Service
public class CredentialsService implements ICredentialsService {

	
	@Override
	public void checkEmailValidity(final String email) {
		
		final String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		final boolean isEmailValid = email.matches(regex);
		if(!isEmailValid) {
			throw new InvalidCredentialsException("Not valid email format");
		}
	}

	@Override
	public void checkPasswordValidity(final String password) {
		
		final String regex = "^(?=.*[0-9])(?=\\S+$).{8,}$";
		final boolean isPasswordValid = password.matches(regex);
		if(!isPasswordValid) {
			throw new InvalidCredentialsException("Password must containt at least"+
					" 8 characters, and must have at least one number!");
		}
	}
}

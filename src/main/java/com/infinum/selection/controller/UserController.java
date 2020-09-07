package com.infinum.selection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinum.selection.model.User;
import com.infinum.selection.service.ICredentialsService;
import com.infinum.selection.service.IUserService;

/**
 * Controller for API user routes.
 */
@RestController
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICredentialsService credentialsService;
	
	/**
	 * Method contains logic for registering new user into the system.
	 * @param email		user email
	 * @param password	user password
	 * @return			instance of {@link ResponseEntity}
	 */
	@RequestMapping("/register/{email}-{password}")
	public ResponseEntity<Object> registerNewUser(@PathVariable String email, @PathVariable String password) {
		
		credentialsService.checkEmailValidity(email);		
		credentialsService.checkPasswordValidity(password);
		
		final String encryptedPassword = getEncryptedPassword(password);
		userService.register(email, encryptedPassword);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Method contains logic for logging user into the system.
	 * @param email		user email
	 * @param password	user password
	 * @return			instance of {@link User} containing value of user email and user token
	 */
	@RequestMapping("/login/{email}-{password}")
	public User login(@PathVariable String email, @PathVariable String password) {
		
		final String encryptedPassword = getEncryptedPassword(password);		
		final Long token = userService.login(email, encryptedPassword);
		return new User(token, email);
	}
	
	private String getEncryptedPassword(final String password) {
		return userService.hashPassword(password);
	}
}

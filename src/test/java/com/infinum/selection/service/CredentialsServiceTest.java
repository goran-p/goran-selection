package com.infinum.selection.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.infinum.selection.exception.InvalidCredentialsException;

@RunWith(MockitoJUnitRunner.class)
public class CredentialsServiceTest {

	@InjectMocks
	CredentialsService credentialsService;
	
	@Test(expected = InvalidCredentialsException.class)
	public void testEmailWithoutUser() {
		
		final String email = "@domain.com";
		credentialsService.checkEmailValidity(email);
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void testEmailWithoutMonkey() {
		
		final String email = "user.domain.com";
		credentialsService.checkEmailValidity(email);
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void testEmailWithoutDomain() {
		
		final String email = "user@";
		credentialsService.checkEmailValidity(email);
	}
	
	@Test
	public void testValidemail() {
		 
		final String email = "dummy@email.com";
		credentialsService.checkEmailValidity(email);
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void testTooShortPassword() {
		
		final String password = "pswd1";
		credentialsService.checkPasswordValidity(password);
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void testPasswordWithoutNumber() {
		
		final String password = "password";
		credentialsService.checkPasswordValidity(password);
	}
	
	@Test(expected = InvalidCredentialsException.class)
	public void testPasswordWithWitespace() {
		
		final String password = "1 password";
		credentialsService.checkPasswordValidity(password);
	}
	
	@Test
	public void testPasswordWithOnlyNumbers() {
		
		final String password = "01234567";
		credentialsService.checkPasswordValidity(password);
	}
	
	@Test
	public void testValidPAssword() {
		
		final String password = "password123";
		credentialsService.checkPasswordValidity(password);
	}
}

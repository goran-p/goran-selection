package com.infinum.selection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class representing {@link RuntimeException} in case when
 * user credentials are not valid.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 2L;
	
	public InvalidCredentialsException(final String message) {
		super(message);
	}
}

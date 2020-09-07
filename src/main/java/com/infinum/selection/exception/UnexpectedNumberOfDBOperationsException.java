package com.infinum.selection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class representing {@link RuntimeException} in case when
 * unexpected number of database operations is performed.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UnexpectedNumberOfDBOperationsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnexpectedNumberOfDBOperationsException(final String message) {
		super(message);
	}
}

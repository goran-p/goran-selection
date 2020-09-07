package com.infinum.selection.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	protected ResponseEntity<Object> handleNoUserException(EmptyResultDataAccessException empty) {
		
		final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage("No registered user with such credentials");
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(UnexpectedNumberOfDBOperationsException.class)
	protected ResponseEntity<Object>
		handleUnexpectedNumberOfDBOperationsException(final UnexpectedNumberOfDBOperationsException e) {
		
		final ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE);
		apiError.setMessage(e.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(DataAccessException.class)
	protected ResponseEntity<Object> handleDatabaseException(final DataAccessException dae) {
		
		final ApiError apiError = new ApiError(HttpStatus.CONFLICT);
		apiError.setMessage(dae.getLocalizedMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	protected ResponseEntity<Object> handleInvalidCredentialsException(final InvalidCredentialsException ice) {
		
		final ApiError apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE);
		apiError.setMessage(ice.getMessage());
		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}

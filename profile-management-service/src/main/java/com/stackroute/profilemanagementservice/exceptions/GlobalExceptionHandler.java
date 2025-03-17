package com.stackroute.profilemanagementservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {

	@ExceptionHandler(UserProfileNotExistsException.class)
	public ResponseEntity<?> exceptionHandler(UserProfileNotExistsException e){
		ResponseEntity<?> entity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		return entity;
	}
	@ExceptionHandler(UserProfileAlreadyExistsException.class)
	public ResponseEntity<?> exceptionHandler(UserProfileAlreadyExistsException e){
		ResponseEntity<?> entity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		return entity;
	}

}

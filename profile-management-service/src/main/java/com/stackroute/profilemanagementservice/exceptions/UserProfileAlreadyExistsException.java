package com.stackroute.profilemanagementservice.exceptions;

public class UserProfileAlreadyExistsException extends Exception{

	/**
	 * 
	 */
	public UserProfileAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserProfileAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserProfileAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public UserProfileAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public UserProfileAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}

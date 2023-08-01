package com.globallogic.exception;

public class UserNameNotFoundException extends RuntimeException {

	private String message;

	public UserNameNotFoundException() {
		super();
	}

	public UserNameNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}

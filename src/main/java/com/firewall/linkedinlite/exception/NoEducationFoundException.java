package com.firewall.linkedinlite.exception;

public class NoEducationFoundException extends RuntimeException {

	private String message;
	public NoEducationFoundException() {
	}

	public NoEducationFoundException(String message) {
		this.message= message;
		
	}

	@Override
	public String getMessage() {

	return this.message;
	}
	
}

package com.firewall.linkedinlite.exception;

public class NoEmployeeFoundException extends RuntimeException {

	private String message;

	public NoEmployeeFoundException() {
	}

	public NoEmployeeFoundException(String message) {
		this.message = message;

	}

	@Override
	public String getMessage() {
		return message;
	}

}

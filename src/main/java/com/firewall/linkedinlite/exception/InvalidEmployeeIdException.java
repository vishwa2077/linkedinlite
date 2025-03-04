package com.firewall.linkedinlite.exception;

public class InvalidEmployeeIdException extends RuntimeException {

	private String message;

	public InvalidEmployeeIdException() {
	}

	public InvalidEmployeeIdException(String message) {

		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;

	}
}

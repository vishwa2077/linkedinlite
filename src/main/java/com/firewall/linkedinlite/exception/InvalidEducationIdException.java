package com.firewall.linkedinlite.exception;

public class InvalidEducationIdException extends RuntimeException {

	private String message;

	public InvalidEducationIdException() {
	}

	public InvalidEducationIdException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}

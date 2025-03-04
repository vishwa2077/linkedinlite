package com.firewall.linkedinlite.exception;

public class InvalidAddressIdException extends RuntimeException {

	private String message;

	public InvalidAddressIdException() {
	}

	public InvalidAddressIdException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {

		return this.message;

	}
}

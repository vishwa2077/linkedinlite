package com.firewall.linkedinlite.exception;

public class NoAddressFoundException extends RuntimeException {

	private String message;

	public NoAddressFoundException() {
	}

	public NoAddressFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}

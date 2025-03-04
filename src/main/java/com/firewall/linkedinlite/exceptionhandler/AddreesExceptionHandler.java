package com.firewall.linkedinlite.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.firewall.linkedinlite.exception.InvalidAddressIdException;
import com.firewall.linkedinlite.exception.NoAddressFoundException;
import com.firewall.linkedinlite.responseStructure.ResponseStructure;

@RestControllerAdvice
public class AddreesExceptionHandler {

	@ExceptionHandler
	public ResponseStructure<String> invalidAddressException(InvalidAddressIdException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("No Address Found");
		structure.setBody(e.getMessage());

		return structure;
	}

	@ExceptionHandler
	public ResponseStructure<String> noAddressFoundException(NoAddressFoundException e) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("No Address Found");
		structure.setBody(e.getMessage());

		return structure;
	}
}

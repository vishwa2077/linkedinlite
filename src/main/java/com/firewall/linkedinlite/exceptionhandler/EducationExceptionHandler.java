package com.firewall.linkedinlite.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.firewall.linkedinlite.exception.InvalidEducationIdException;
import com.firewall.linkedinlite.exception.NoEducationFoundException;
import com.firewall.linkedinlite.responseStructure.ResponseStructure;

@RestControllerAdvice
public class EducationExceptionHandler {

	@ExceptionHandler
	public ResponseStructure<String> noEducationFoundException(NoEducationFoundException e) {

		ResponseStructure<String> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("No Education Found");
		structure.setBody(e.getMessage());

		return structure;
	}

	@ExceptionHandler
	public ResponseStructure<String> invalidEducationIdException(InvalidEducationIdException e){
		
		ResponseStructure<String> structure = new ResponseStructure<>();

		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("No Education Found");
		structure.setBody(e.getMessage());

		return structure;
		
	}
	
	
}

package com.firewall.linkedinlite.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.firewall.linkedinlite.exception.InvalidEmployeeIdException;
import com.firewall.linkedinlite.exception.NoEmployeeFoundException;
import com.firewall.linkedinlite.responseStructure.ResponseStructure;

@RestControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler
	public ResponseStructure<String> noEmployeeFoundException(NoEmployeeFoundException e){
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("No Employee Found ");
		structure.setBody(e.getMessage());
		
		
		return structure;
	}
	
	@ExceptionHandler
	public ResponseStructure<String> invalidEmployeeIdException(InvalidEmployeeIdException e){
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("No Employee Found");
		structure.setBody(e.getMessage());
		
		return structure;
	}
}

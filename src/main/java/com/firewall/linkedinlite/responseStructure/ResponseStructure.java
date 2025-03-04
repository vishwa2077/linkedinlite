package com.firewall.linkedinlite.responseStructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructure<T> {

	private int status;
	private String message;
	private T body;
}

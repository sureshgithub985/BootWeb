package com.ey.core.util;


public class ValidationErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ValidationErrorException() {
		super();
	}

	public ValidationErrorException(String message) {
		super(message);
	}

	public ValidationErrorException(Throwable t, String message) {
		super(message, t);
	}
}

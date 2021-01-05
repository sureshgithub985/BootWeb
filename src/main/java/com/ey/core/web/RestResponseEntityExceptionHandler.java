package com.ey.core.web;

import java.sql.SQLException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ey.core.util.ValidationErrorException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleExceptionHandler(ConstraintViolationException cve) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<Object>(cve.getMessage(), headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ValidationErrorException.class })
	public ResponseEntity<Object> handleValidationErrorExceptionHandler(ValidationErrorException vee) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<Object>(vee.getMessage(), headers, HttpStatus.BAD_REQUEST);
	}

	// @ExceptionHandler({ SQLException.class })
	public ResponseEntity<Object> handleSQLExceptionHandler(SQLException se) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		System.out.println("coming here in the SQL Exception............");
		return new ResponseEntity<Object>(se.getMessage(), headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handlerequiredHttpRequestMethodNotSupportedException(Exception e) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		if (e instanceof HttpRequestMethodNotSupportedException) {

			System.out.println("coming requets here......");
			return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.METHOD_NOT_ALLOWED);
		}

		return new ResponseEntity<Object>(e.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}

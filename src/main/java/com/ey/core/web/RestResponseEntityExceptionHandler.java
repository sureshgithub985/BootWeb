package com.ey.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ey.core.util.ResourceNotFoundException;
import com.ey.core.util.ValidationErrorException;

//@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String VALIDATION_ERROR_MSG = "<error>\n" + "<error-type>ValidationError</error-type>\n"
			+ "<message>";
	public static final String NOT_FOUND_MSG = "<error>\n" + "<error-type>NotFound</error-type>\n" + "<message>";
	public static final String MESSAGE_AND_ERROR_CLOSE = "</message>\n" + "</error>";
	public static final String ERROR_TYPE = "error-type";
	public static final String MESSAGE = "message";
	public static final String CONTENT_TYPE = "Content-Type";

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleExceptionHandler(Exception ex, HttpServletRequest request) {

		HttpHeaders headers = new HttpHeaders();
		String contentType = request.getContentType();
		if (contentType == null)
			contentType = "application/json";

		headers.add(CONTENT_TYPE, contentType);

		if (ex instanceof ConstraintViolationException || ex instanceof ValidationErrorException) {

			if (contentType.equals("application/xml")) {
				StringBuilder sb = new StringBuilder();
				sb.append(VALIDATION_ERROR_MSG).append(ex.getMessage()).append(MESSAGE_AND_ERROR_CLOSE);
				return new ResponseEntity<>(sb.toString(), headers, HttpStatus.BAD_REQUEST);
			} else {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(ERROR_TYPE, "ValidationError");
				jsonObj.put(MESSAGE, ex.getMessage());
				return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.BAD_REQUEST);
			}

		} else if (ex instanceof ResourceNotFoundException) {
			if (contentType.equals("application/xml")) {
				StringBuilder sb = new StringBuilder();
				sb.append(NOT_FOUND_MSG).append(ex.getMessage()).append(MESSAGE_AND_ERROR_CLOSE);

				return new ResponseEntity<>(sb.toString(), headers, HttpStatus.NOT_FOUND);
			} else {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(ERROR_TYPE, "NotFound");
				jsonObj.put(MESSAGE, ex.getMessage());
				return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.NOT_FOUND);
			}

		} else
			return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String contentType = request.getHeader(CONTENT_TYPE);
		if (contentType == null)
			contentType = "application/json";

		headers.add(CONTENT_TYPE, contentType);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put(ERROR_TYPE, "ValidationError");
		jsonObj.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.BAD_REQUEST);

	}

}

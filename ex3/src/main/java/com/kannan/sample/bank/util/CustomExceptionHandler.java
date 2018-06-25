package com.kannan.sample.bank.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kannan.sample.util.Custom204Exception;
import com.kannan.sample.util.Custom404Exception;
import com.kannan.sample.util.CustomResponseEntity;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Custom404Exception.class)
	public ResponseEntity<?> handleResourceNotFound(Custom404Exception ex) {
		CustomResponseEntity response = new CustomResponseEntity();
		response.setMessage(ex.getMessage());
		response.setData(null);
		return new ResponseEntity<Object>(response, response.getStatus());
	}

	@ExceptionHandler(Custom204Exception.class)
	public ResponseEntity<?> handleNoContent(Custom204Exception ex) {
		CustomResponseEntity response = new CustomResponseEntity();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NO_CONTENT);
		response.setData(null);
		return new ResponseEntity<Object>(response, response.getStatus());
	}

}

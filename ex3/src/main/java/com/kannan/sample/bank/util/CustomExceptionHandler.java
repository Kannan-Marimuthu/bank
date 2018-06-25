package com.kannan.sample.bank.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Custom204Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	CustomResponseEntity handleNoContent(Custom204Exception e) {
		return new CustomResponseEntity(HttpStatus.NO_CONTENT, e.getMessage(), null);
	}

	@ExceptionHandler(Custom404Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	CustomResponseEntity handleNotFound(Custom404Exception e) {
		return new CustomResponseEntity(HttpStatus.NOT_FOUND, e.getMessage(), null);
	}

}

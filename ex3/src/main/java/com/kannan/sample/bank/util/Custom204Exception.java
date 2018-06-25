package com.kannan.sample.bank.util;

import org.springframework.http.HttpStatus;

public class Custom204Exception extends Exception {

	private static final long serialVersionUID = -3101665193361190335L;
	HttpStatus httpStatus;

	public Custom204Exception(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}

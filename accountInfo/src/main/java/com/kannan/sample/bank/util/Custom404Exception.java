package com.kannan.sample.bank.util;

import org.springframework.http.HttpStatus;

public class Custom404Exception extends RuntimeException {

	private static final long serialVersionUID = 5410778579613309441L;

	HttpStatus httpStatus;

	public Custom404Exception(String message) {
		super(message);
	}

	public Custom404Exception(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}

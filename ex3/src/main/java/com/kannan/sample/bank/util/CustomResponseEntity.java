package com.kannan.sample.bank.util;

import org.springframework.http.HttpStatus;

public class CustomResponseEntity {
	private HttpStatus status;
	private String message;
	private Object data;

	public CustomResponseEntity() {
		super();
	}

	public CustomResponseEntity(HttpStatus status) {
		super();
		this.status = status;
	}

	public CustomResponseEntity(HttpStatus status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public CustomResponseEntity(HttpStatus status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

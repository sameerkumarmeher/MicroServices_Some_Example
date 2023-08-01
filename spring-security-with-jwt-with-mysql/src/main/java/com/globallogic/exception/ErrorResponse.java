package com.globallogic.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

	private Date timestamp;
	private String message;
	private String path;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

package com.jsc.javageeks.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Status {
	private String message;
	private String statusCode;

	public Status() {
	}

	public Status(String errorMessage, String statusCode) {
		this.message = errorMessage;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
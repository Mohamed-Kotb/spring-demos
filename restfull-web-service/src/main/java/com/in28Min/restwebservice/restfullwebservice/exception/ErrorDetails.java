package com.in28Min.restwebservice.restfullwebservice.exception;

import java.time.LocalDate;

public class ErrorDetails {

	private LocalDate timeStamp;
	private String message;
	private String details;

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public ErrorDetails(LocalDate timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

}

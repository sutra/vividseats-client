package org.oxerr.vividseats.client.rescu.resource;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import si.mazi.rescu.HttpStatusExceptionSupport;

public class VividSeatsException extends HttpStatusExceptionSupport {

	private static final long serialVersionUID = 2024101201L;

	private final String message;

	private final List<String> errors;

	public VividSeatsException(
		@JsonProperty("success") Boolean success,
		@JsonProperty("message") String message,
		@JsonProperty("errors") List<String> errors
	) {
		super(message);
		this.message = message;
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	@Override
	public String getMessage() {
		return message;
	}

}

package org.oxerr.vividseats.client.rescu.resource;

import java.util.List;

import si.mazi.rescu.ExceptionalReturnContentException;

public class VividSeatsExceptionalReturnContentException extends ExceptionalReturnContentException {

	private static final long serialVersionUID = 2025050701L;

	private final List<String> errors;

	public VividSeatsExceptionalReturnContentException(String message, List<String> errors) {
		super(message);
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	@Override
	public String toString() {
		return String.format("%s: %s, errors: [%s]", getClass().getName(), getMessage(), String.join(", ", errors));
	}

}

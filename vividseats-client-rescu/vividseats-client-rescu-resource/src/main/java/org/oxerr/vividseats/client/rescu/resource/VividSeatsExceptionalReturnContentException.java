package org.oxerr.vividseats.client.rescu.resource;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import si.mazi.rescu.ExceptionalReturnContentException;

public class VividSeatsExceptionalReturnContentException extends ExceptionalReturnContentException {

	private static final long serialVersionUID = 2025050701L;

	@Nullable
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
		return String.format(
			"%s: %s, errors: %s",
			getClass().getName(),
			getMessage(),
			Optional.ofNullable(errors).map(e -> "[" + String.join(", ", e) + "]").orElse("null")
		);
	}

}

package org.oxerr.vividseats.client.cxf.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <a href="https://vividseats.stoplight.io/docs/broker-portal/b064ad41fe925-response">Response</a>.
 */
public class Response implements Serializable {

	private static final long serialVersionUID = 2024092601L;

	private Boolean success;

	private String message;

	private List<String> errors;

	public Response(
		@JsonProperty("success") Boolean success,
		@JsonProperty("message") String message,
		@JsonProperty("errors") List<String> errors
	) {
		this.success = success;
		this.message = message;
		this.errors = errors;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}

package org.oxerr.vividseats.client.model;

import java.io.Serializable;

/**
 * <a href="https://vividseats.stoplight.io/docs/broker-portal/b064ad41fe925-response">Response</a>.
 */
public class Response implements Serializable {

	private static final long serialVersionUID = 2024092601L;

	private Boolean success;

	private String message;

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

}

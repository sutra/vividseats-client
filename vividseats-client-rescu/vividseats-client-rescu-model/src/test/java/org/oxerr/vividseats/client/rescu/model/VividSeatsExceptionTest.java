package org.oxerr.vividseats.client.rescu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class VividSeatsExceptionTest {

	@Test
	void testNoErrors() {
		var exception = new VividSeatsException(Boolean.FALSE, "message", null);
		assert exception.getMessage().equals("message");
		assertEquals(
			"org.oxerr.vividseats.client.rescu.model.VividSeatsException: message, errors: null",
			exception.toString()
		);
	}

	@Test
	void testWithErrors() {
		var exception = new VividSeatsException(Boolean.FALSE, "message", List.of("content"));
		assert exception.getMessage().equals("message");
		assertEquals(
			"org.oxerr.vividseats.client.rescu.model.VividSeatsException: message, errors: [content]",
			exception.toString()
		);
	}

}

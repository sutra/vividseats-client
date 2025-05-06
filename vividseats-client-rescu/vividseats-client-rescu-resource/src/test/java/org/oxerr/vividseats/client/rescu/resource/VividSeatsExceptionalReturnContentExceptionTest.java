package org.oxerr.vividseats.client.rescu.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class VividSeatsExceptionalReturnContentExceptionTest {

	@Test
	void test() {
		var exception = new VividSeatsExceptionalReturnContentException("message", List.of("content"));
		assert exception.getMessage().equals("message");
		assertEquals(
			"org.oxerr.vividseats.client.rescu.resource.VividSeatsExceptionalReturnContentException: message, errors: [content]",
			exception.toString()
		);
	}

}

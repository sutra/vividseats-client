package org.oxerr.vividseats.client.rescu.resource.inventory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;

class ListingResponseTest {

	private static final ObjectMapper objectMapper = ObjectMappers.objectMapper();

	@Test
	void testCreateErrorInvalidSection() {
		var src = this.getClass().getResource("ListingResponse-create-error-InvalidSection.json");
		assertThrows(ValueInstantiationException.class, () -> objectMapper.readValue(src, ListingResponse.class));
	}

	@Test
	void testCreateErrorInvalidSplitType() {
		var src = this.getClass().getResource("ListingResponse-create-error-InvalidSplitType.json");
		assertThrows(ValueInstantiationException.class, () -> objectMapper.readValue(src, ListingResponse.class));
	}

	@Test
	void testCreateSuccess() throws IOException {
		var src = this.getClass().getResource("ListingResponse-create-success.json");
		var listingResponse = objectMapper.readValue(src, ListingResponse.class);
		assertNotNull(listingResponse.getListing());
	}

}

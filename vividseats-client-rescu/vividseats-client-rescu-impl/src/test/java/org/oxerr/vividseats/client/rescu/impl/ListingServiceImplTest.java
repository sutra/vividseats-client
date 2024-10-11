package org.oxerr.vividseats.client.rescu.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ListingServiceImplTest {

	@Test
	void testGet() {
		ResCUVividSeatsClient client = ResCUVividSeatsClientTest.getClient();
		var listingsResponse = client.getListingService().get(null, null, null, null, null, null, null);
		assertNotNull(listingsResponse);
	}

}

package org.oxerr.vividseats.client.rescu.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.oxerr.vividseats.client.model.BrokerListing;
import org.oxerr.vividseats.client.model.SplitType;

class ListingServiceImplTest {

	private static final ResCUVividSeatsClient client = ResCUVividSeatsClientTest.getClient();

	@Test
	void testGet() throws IOException {
		var listingsResponse = client.getListingService().get(null, null, null, null, null, null, null);
		assertNotNull(listingsResponse);
	}

	@Disabled("Create listing.")
	@Test
	void testCreate() throws IOException {
		var brokerListing = new BrokerListing();
		brokerListing.setProductionId(1361816);
		brokerListing.setQuantity(1);
		brokerListing.setSection("335");
		brokerListing.setRow("F");
		brokerListing.setSeatFrom("1");
		brokerListing.setSeatThru("1");
		brokerListing.setPrice(BigDecimal.ONE);
		brokerListing.setSplitType(SplitType.ANY);
		var created = client.getListingService().create(brokerListing);
		assertNotNull(created);
	}

	@Disabled("Update listing.")
	@Test
	void testUpdate() throws IOException {
		var brokerListing = new BrokerListing();
		brokerListing.setId(10845010354L);
		brokerListing.setProductionId(1361816);
		brokerListing.setQuantity(1);
		brokerListing.setSection("335");
		brokerListing.setRow("F");
		brokerListing.setSeatFrom("1");
		brokerListing.setSeatThru("1");
		brokerListing.setPrice(BigDecimal.ONE);
		brokerListing.setSplitType(SplitType.ANY);
		client.getListingService().update(brokerListing);
	}

	@Disabled("Delete listing.")
	@Test
	void testDelete() throws IOException {
		client.getListingService().delete(10845010354L, null);
	}

}

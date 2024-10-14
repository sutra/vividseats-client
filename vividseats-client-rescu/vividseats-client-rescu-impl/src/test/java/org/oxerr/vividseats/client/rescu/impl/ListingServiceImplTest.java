package org.oxerr.vividseats.client.rescu.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.oxerr.vividseats.client.model.BrokerListing;
import org.oxerr.vividseats.client.model.SplitType;

@Disabled("Token is required")
class ListingServiceImplTest {

	private static final ResCUVividSeatsClient client = ResCUVividSeatsClientTest.getClient();

	private final Logger log = LogManager.getLogger();

	@Disabled("Token is required.")
	@Test
	void testGet() throws IOException {
		var listings = client.getListingService().get(null, null, null, null, null, null, null);
		assertNotNull(listings);
		log.info("Listings: {}", listings.size());
		listings.forEach(listing -> log.info("{}", ToStringBuilder.reflectionToString(listing)));
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

		var listing = client.getListingService().get(10845010354L, null, null, null, null, null, null);
		assertEquals(BigDecimal.ONE, listing.get(0).getPrice());
	}

	@Disabled("Delete listing.")
	@Test
	void testDelete() throws IOException {
		client.getListingService().delete(10845010354L, null);

		assertTrue(client.getListingService().get(10845010354L, null, null, null, null, null, null).isEmpty());
	}

}

package org.oxerr.vividseats.client.cxf.impl.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.oxerr.vividseats.client.cxf.impl.CXFVividSeatsClients;
import org.oxerr.vividseats.client.cxf.model.v1.inventory.BrokerListings;
import org.oxerr.vividseats.client.inventory.BrokerListingQuery;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.oxerr.vividseats.client.model.inventory.SplitType;

class ListingServiceImplTest {

	private final Logger log = LogManager.getLogger(this.getClass());

	private ListingServiceImpl listingService;

	@BeforeEach
	void setUp() {
		listingService = CXFVividSeatsClients.getClient().getListingService();
	}

	@Disabled("Need to provide a valid token.")
	@Test
	void testGetBrokerListings() {
		/*
		-Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager
		-Dcom.sun.xml.ws.transport.http.client.HttpTransportPipe.dump=true
		-Dcom.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump=true
		-Dcom.sun.xml.ws.transport.http.HttpAdapter.dump=true
		-Dcom.sun.xml.internal.ws.transport.http.HttpAdapter.dump=true
		*/

		for (int i = 0; i < 5; i++) {
			BrokerListings brokerListings = listingService.getListings(null);
			assertNotNull(brokerListings);
			log.info("brokerListings: {}", brokerListings.getBrokerListings().size());
		}
	}

	@Disabled("Token is required.")
	@Test
	void testGet() {
		var brokerListingQuery = new BrokerListingQuery();
		brokerListingQuery.setFromEventDate(LocalDateTime.parse("2025-02-05T19:30:00"));
		brokerListingQuery.setToEventDate(LocalDateTime.parse("2025-02-05T19:30:00"));
		var listings = listingService.get(brokerListingQuery);
		assertNotNull(listings);
		log.info("Listings: {}", listings.size());
		listings.forEach(listing -> log.info("{}", ToStringBuilder.reflectionToString(listing)));
	}

	@Disabled("Create listing.")
	@Test
	void testCreate() {
		var brokerListing = new BrokerListing();
		brokerListing.setProductionId(1361816);
		brokerListing.setQuantity(1);
		brokerListing.setSection("335");
		brokerListing.setRow("F");
		brokerListing.setSeatFrom("1");
		brokerListing.setSeatThru("1");
		brokerListing.setPrice(BigDecimal.ONE);
		brokerListing.setSplitType(SplitType.ANY);
		var created = listingService.create(brokerListing);
		assertNotNull(created);
	}

	@Disabled("Update listing.")
	@Test
	void testUpdate() {
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
		listingService.update(brokerListing);

		BrokerListingQuery brokerListingQuery = new BrokerListingQuery();
		brokerListingQuery.setListingId(10845010354L);
		var listing = listingService.get(brokerListingQuery);
		assertEquals(BigDecimal.ONE, listing.get(0).getPrice());
	}

	@Disabled("Delete listing.")
	@Test
	void testDelete() {
		listingService.delete(10845010354L, null);

		BrokerListingQuery brokerListingQuery = new BrokerListingQuery();
		brokerListingQuery.setListingId(10845010354L);
		assertTrue(listingService.get(brokerListingQuery).isEmpty());
	}

	@Disabled("Delete listing.")
	@Test
	void testDeleteListing() {
		String ticketId = "1"; // Internal ID
		listingService.deleteListing(ticketId);
		BrokerListingQuery brokerListingQuery = new BrokerListingQuery();
		brokerListingQuery.setHeadlinerId(1);
		assertTrue(listingService.get(brokerListingQuery).isEmpty());
	}

}

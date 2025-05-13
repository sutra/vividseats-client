package org.oxerr.vividseats.client.cxf.impl.inventory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.oxerr.vividseats.client.cxf.impl.CXFVividSeatsClient;
import org.oxerr.vividseats.client.cxf.impl.CXFVividSeatsClients;
import org.oxerr.vividseats.client.cxf.model.v1.inventory.BrokerListings;
import org.oxerr.vividseats.client.inventory.BrokerListingQuery;

class ListingServiceImplTest {

	private final Logger log = LogManager.getLogger(this.getClass());

	private CXFVividSeatsClient client;

	@BeforeEach
	void setUp() {
		client = CXFVividSeatsClients.getClient();
	}

	@Disabled("Need to provide a valid token.")
	@Test
	void testGetBrokerListings() {
		/*
		 * -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager
		 * -Dcom.sun.xml.ws.transport.http.client.HttpTransportPipe.dump=true
		 * -Dcom.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump=true
		 * -Dcom.sun.xml.ws.transport.http.HttpAdapter.dump=true
		 * -Dcom.sun.xml.internal.ws.transport.http.HttpAdapter.dump=true
		 */

		for (int i = 0; i < 5; i++) {
			BrokerListings brokerListings = client.getListingService().getListings(null);
			assertNotNull(brokerListings);
			log.info("brokerListings: {}", brokerListings.getBrokerListings().size());
		}
	}

	@Disabled("Need to provide a valid token.")
	@Test
	void testGet() {
		try {
			var size = client.getListingService().get(new BrokerListingQuery()).size();
			log.info("size: {}", size);
		} catch (Exception e) {
			log.error("Error: ", e);
			fail(e.getMessage());
		}
		log.info("testGet() passed.");
	}

}

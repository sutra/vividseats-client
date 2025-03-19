package org.oxerr.vividseats.client.cxf;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class CXFVividSeatsClientTest {

	private final Logger log = LogManager.getLogger();

	@Test
	void testGetBrokerListings() {
		String token = System.getProperty("vividseats.token");
		log.info("token: {}", token);
		BrokerListings brokerListings = new CXFVividSeatsClient(token).getListings(null);
		assertNotNull(brokerListings);
	}

}

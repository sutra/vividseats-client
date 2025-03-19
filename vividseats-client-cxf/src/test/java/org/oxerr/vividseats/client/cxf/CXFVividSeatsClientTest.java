package org.oxerr.vividseats.client.cxf;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CXFVividSeatsClientTest {

	private final Logger log = LogManager.getLogger();

	@Disabled("Need to provide a valid token.")
	@Test
	void testGetBrokerListings() {
		/*
		 * -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager
		 * -Dcom.sun.xml.ws.transport.http.client.HttpTransportPipe.dump=true
		 * -Dcom.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump=true
		 * -Dcom.sun.xml.ws.transport.http.HttpAdapter.dump=true
		 * -Dcom.sun.xml.internal.ws.transport.http.HttpAdapter.dump=true
		 * -Dvividseats.token=xxx
		 */
		String token = System.getProperty("vividseats.token");
		log.info("token: {}", token);
		BrokerListings brokerListings = new CXFVividSeatsClient(token).getListings(null);
		assertNotNull(brokerListings);
		log.info("brokerListings: {}", brokerListings.getBrokerListings().size());
	}

}

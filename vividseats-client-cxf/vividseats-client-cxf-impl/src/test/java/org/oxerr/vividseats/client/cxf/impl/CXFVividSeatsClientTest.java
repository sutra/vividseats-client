package org.oxerr.vividseats.client.cxf.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.oxerr.vividseats.client.model.v1.inventory.BrokerListings;

import io.github.poshjosh.ratelimiter.store.BandwidthsStore;

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
		 */

		CXFVividSeatsClient vividSeatsClient = getClient();

		for (int i = 0; i < 5; i++) {
			BrokerListings brokerListings = vividSeatsClient.getListingService().getListings(null);
			assertNotNull(brokerListings);
			log.info("brokerListings: {}", brokerListings.getBrokerListings().size());
		}
	}

	public static CXFVividSeatsClient getClient() {
		Properties props = getProps();
		String token = props.getProperty("token");

		BandwidthsStore<String> bandwidthsStore = BandwidthsStore.ofDefaults();
		return new CXFVividSeatsClient(token, bandwidthsStore);
	}

	public static Properties getProps() {
		Properties props = new Properties();
		String name = "/vividseats.properties";
		try (InputStream in = CXFVividSeatsClient.class.getResourceAsStream(name)) {
			if (in != null) {
				props.load(in);
			} else {
				throw new IllegalArgumentException(String.format("No resource found: %s", name));
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Read " + name + " failed.");
		}
		return props;
	}

}

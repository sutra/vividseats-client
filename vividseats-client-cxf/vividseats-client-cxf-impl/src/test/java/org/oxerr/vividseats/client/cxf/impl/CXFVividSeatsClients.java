package org.oxerr.vividseats.client.cxf.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.github.poshjosh.ratelimiter.store.BandwidthsStore;

public final class CXFVividSeatsClients {

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

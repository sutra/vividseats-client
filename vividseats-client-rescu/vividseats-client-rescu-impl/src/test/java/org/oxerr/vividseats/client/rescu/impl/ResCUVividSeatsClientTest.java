package org.oxerr.vividseats.client.rescu.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Token is required")
class ResCUVividSeatsClientTest {

	public static ResCUVividSeatsClient getClient() {
		Properties props = getProps();
		String token = props.getProperty("token");
		return new ResCUVividSeatsClient(token);
	}

	public static Properties getProps() {
		Properties props = new Properties();
		String name = "/vividseats.properties";
		try (InputStream in = ResCUVividSeatsClientTest.class.getResourceAsStream(name)) {
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

	@Test
	void testGetClient() {
		assertNotNull(ResCUVividSeatsClientTest.getClient());
	}

}

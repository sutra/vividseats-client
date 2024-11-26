package org.oxerr.vividseats.client.model.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class BrokerListingTest {

	@Test
	void test() {
		assertEquals(new BrokerListing().hashCode(), new BrokerListing().hashCode());

		boolean equals = new BrokerListing().equals(new BrokerListing());
		assertTrue(equals);

		BrokerListing a = new BrokerListing();
		BrokerListing b = new BrokerListing();
		b.setPrice(new BigDecimal(1));

		assertNotEquals(a.hashCode(), b.hashCode());
		equals = a.equals(b);
		assertFalse(equals);
	}

}

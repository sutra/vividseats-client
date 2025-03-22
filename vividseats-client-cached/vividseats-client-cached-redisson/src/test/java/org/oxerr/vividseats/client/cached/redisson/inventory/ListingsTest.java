package org.oxerr.vividseats.client.cached.redisson.inventory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;

class ListingsTest {

	@Test
	void testIsSame() {
		BrokerListing a = new BrokerListing();
		BrokerListing b = new BrokerListing();
		assertTrue(Listings.isSame(a, b));

		a.setPrice(new BigDecimal("1.23"));
		b.setPrice(new BigDecimal("1.230"));
		assertTrue(Listings.isSame(a, b));

		a.setPrice(new BigDecimal("1.23"));
		b.setPrice(new BigDecimal("1.231"));
		assertFalse(Listings.isSame(a, b));
	}

}

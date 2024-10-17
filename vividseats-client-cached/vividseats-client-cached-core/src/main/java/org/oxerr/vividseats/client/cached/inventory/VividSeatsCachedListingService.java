package org.oxerr.vividseats.client.cached.inventory;

import org.oxerr.ticket.inventory.support.cached.CachedListingService;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;

public interface VividSeatsCachedListingService
	extends CachedListingService<String, String, BrokerListing, VividSeatsListing, VividSeatsEvent> {
}

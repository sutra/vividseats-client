package org.oxerr.vividseats.client.cached.inventory;

import org.oxerr.ticket.inventory.support.cached.CachedListingService;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;

public interface VividSeatsCachedListingService
	extends CachedListingService<String, String, BrokerListing, VividSeatsListing, VividSeatsEvent> {

	/**
	 * Check all listings and delete which not in cache.
	 */
	void check();

	/**
	 * Check all listings and delete which not in cache.
	 *
	 * @param options the check options.
	 */
	void check(CheckOptions options);

}

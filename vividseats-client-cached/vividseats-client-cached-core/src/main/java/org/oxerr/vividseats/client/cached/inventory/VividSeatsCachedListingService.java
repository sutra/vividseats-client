package org.oxerr.vividseats.client.cached.inventory;

import java.io.UncheckedIOException;

import org.oxerr.ticket.inventory.support.cached.CachedListingService;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;

public interface VividSeatsCachedListingService
	extends CachedListingService<String, String, BrokerListing, VividSeatsListing, VividSeatsEvent> {

	/**
	 * Check all listings and delete which not in cache.
	 *
	 * @throws UncheckedIOException if an I/O error occurs.
	 */
	void check() throws UncheckedIOException;

	/**
	 * Check all listings and delete which not in cache.
	 *
	 * @param options the check options.
	 * @throws UncheckedIOException if an I/O error occurs.
	 */
	void check(CheckOptions options) throws UncheckedIOException;

}

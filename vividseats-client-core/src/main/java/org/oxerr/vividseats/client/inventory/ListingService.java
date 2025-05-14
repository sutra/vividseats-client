package org.oxerr.vividseats.client.inventory;

import java.util.List;

import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.oxerr.vividseats.client.model.v1.inventory.Update;

public interface ListingService {

	List<BrokerListing> get(BrokerListingQuery q);

	BrokerListing create(BrokerListing brokerListing);

	void updateListing(Update update) ;

	void update(BrokerListing brokerListing);

	/**
	 * Delete the listing.
	 *
	 * @param ticketId the internal ticket ID.
	 */
	void deleteListing(String ticketId);

	/**
	 * Delete the listing.
	 *
	 * @param listingId the vivid ticket ID.
	 * @param internalTicketId the internal ticket ID.
	 */
	void delete(Long listingId, String internalTicketId);

}

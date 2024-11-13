package org.oxerr.vividseats.client.inventory;

import java.io.IOException;
import java.util.List;

import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.oxerr.vividseats.client.model.v1.inventory.Update;

public interface ListingService {

	List<BrokerListing> get(
		Long listingId,
		String internalTicketId,
		Integer productionId,
		String fromEventDate,
		String toEventDate,
		Integer headlinerId,
		Boolean includeFiles
	) throws IOException;

	BrokerListing create(BrokerListing brokerListing) throws IOException;

	void updateListing(Update update) throws IOException;

	void update(BrokerListing brokerListing) throws IOException;

	/**
	 * Delete the listing.
	 *
	 * @param ticketId the internal ticket ID.
	 * @throws IOException indicates I/O exception.
	 */
	void deleteListing(String ticketId) throws IOException;

	/**
	 * Delete the listing.
	 *
	 * @param listingId the vivid ticket ID.
	 * @param internalTicketId the internal ticket ID.
	 * @throws IOException indicates I/O exception.
	 */
	void delete(Long listingId, String internalTicketId) throws IOException;

}

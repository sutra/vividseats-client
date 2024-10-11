package org.oxerr.vividseats.client;

import java.io.IOException;
import java.util.List;

import org.oxerr.vividseats.client.model.BrokerListing;

public interface ListingService {

	List<BrokerListing> get(
		String fromEventDate,
		String toEventDate,
		Long listingId,
		String internalTicketId,
		Integer productionId,
		Integer headlinerId,
		Boolean includeFiles
	) throws IOException;

	BrokerListing create(BrokerListing brokerListing) throws IOException;

	void update(BrokerListing brokerListing) throws IOException;

	void delete(Long listingId, String internalTicketId) throws IOException;

}

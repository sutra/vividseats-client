package org.oxerr.vividseats.client;

import java.io.IOException;
import java.util.List;

import org.oxerr.vividseats.client.model.BrokerListing;

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

	void update(BrokerListing brokerListing) throws IOException;

	void delete(Long listingId, String internalTicketId) throws IOException;

}

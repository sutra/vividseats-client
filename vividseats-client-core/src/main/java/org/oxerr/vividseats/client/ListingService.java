package org.oxerr.vividseats.client;

import org.oxerr.vividseats.client.model.ListingsResponse;

public interface ListingService {

	ListingsResponse get(
		String fromEventDate,
		String toEventDate,
		Long listingId,
		String internalTicketId,
		Integer productionId,
		Integer headlinerId,
		Boolean includeFiles
	);

}

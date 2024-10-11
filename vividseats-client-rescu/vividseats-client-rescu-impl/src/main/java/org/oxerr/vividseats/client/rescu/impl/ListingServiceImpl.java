package org.oxerr.vividseats.client.rescu.impl;

import org.oxerr.vividseats.client.ListingService;
import org.oxerr.vividseats.client.model.ListingsResponse;
import org.oxerr.vividseats.client.rescu.resource.ListingResource;

public class ListingServiceImpl implements ListingService {

	private final ListingResource listingResource;

	public ListingServiceImpl(ListingResource listingResource) {
		this.listingResource = listingResource;
	}

	@Override
	public ListingsResponse get(String fromEventDate, String toEventDate, Long listingId, String internalTicketId,
			Integer productionId, Integer headlinerId, Boolean includeFiles) {
		return listingResource.get(fromEventDate, toEventDate, listingId, internalTicketId, productionId, headlinerId, includeFiles);
	}

}

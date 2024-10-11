package org.oxerr.vividseats.client.rescu.impl;

import java.io.IOException;
import java.util.List;

import org.oxerr.vividseats.client.ListingService;
import org.oxerr.vividseats.client.model.BrokerListing;
import org.oxerr.vividseats.client.rescu.resource.ListingResource;

public class ListingServiceImpl implements ListingService {

	private final ListingResource listingResource;

	public ListingServiceImpl(ListingResource listingResource) {
		this.listingResource = listingResource;
	}

	@Override
	public List<BrokerListing> get(String fromEventDate, String toEventDate, Long listingId, String internalTicketId,
			Integer productionId, Integer headlinerId, Boolean includeFiles) throws IOException {
		return listingResource.get(fromEventDate, toEventDate, listingId, internalTicketId, productionId, headlinerId, includeFiles).getListings();
	}

	@Override
	public BrokerListing create(BrokerListing brokerListing) throws IOException {
		return listingResource.create(brokerListing).getListing();
	}

	@Override
	public void update(BrokerListing brokerListing) throws IOException {
		listingResource.update(brokerListing);
	}

	@Override
	public void delete(Long listingId, String internalTicketId) throws IOException {
		listingResource.delete(listingId, internalTicketId);
	}

}

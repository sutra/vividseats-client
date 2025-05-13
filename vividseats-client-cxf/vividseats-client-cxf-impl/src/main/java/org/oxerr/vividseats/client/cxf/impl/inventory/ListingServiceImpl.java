package org.oxerr.vividseats.client.cxf.impl.inventory;

import java.util.List;

import org.oxerr.vividseats.client.cxf.model.v1.inventory.BrokerListings;
import org.oxerr.vividseats.client.inventory.BrokerListingQuery;
import org.oxerr.vividseats.client.inventory.ListingService;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.oxerr.vividseats.client.model.v1.inventory.Update;

import jakarta.annotation.Nullable;

public class ListingServiceImpl implements ListingService {

	private final org.oxerr.vividseats.client.cxf.resource.v1.inventory.ListingResource listingResourceV1;

	private final org.oxerr.vividseats.client.cxf.resource.inventory.ListingResource listingResource;

	private final String token;

	public ListingServiceImpl(
		org.oxerr.vividseats.client.cxf.resource.v1.inventory.ListingResource listingResourceV1,
		org.oxerr.vividseats.client.cxf.resource.inventory.ListingResource listingResource,
		String token
	) {
		this.listingResourceV1 = listingResourceV1;
		this.listingResource = listingResource;
		this.token = token;
	}

	public BrokerListings getListings(@Nullable Integer ticketId) {
		return listingResourceV1.getListings(token, ticketId);
	}

	@Override
	public List<BrokerListing> get(BrokerListingQuery q) {
		return listingResource.get(
			q.getListingId(),
			q.getInternalTicketId(),
			q.getProductionId(),
			q.getHeadlinerId(),
			q.getFromEventDate(),
			q.getToEventDate(),
			q.getIncludeFiles()
		).getListings();
	}

	@Override
	public BrokerListing create(BrokerListing brokerListing) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void updateListing(Update update) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void update(BrokerListing brokerListing) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void deleteListing(String ticketId) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void delete(Long listingId, String internalTicketId) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

}

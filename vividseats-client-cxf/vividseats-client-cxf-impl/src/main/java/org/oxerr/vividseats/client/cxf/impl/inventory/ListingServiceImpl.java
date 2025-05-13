package org.oxerr.vividseats.client.cxf.impl.inventory;

import org.oxerr.vividseats.client.cxf.resource.v1.inventory.ListingResource;
import org.oxerr.vividseats.client.model.v1.inventory.BrokerListings;

import jakarta.annotation.Nullable;

public class ListingServiceImpl {

	private final ListingResource listingResource;

	private final String token;

	public ListingServiceImpl(ListingResource listingResource, String token) {
		this.token = token;
		this.listingResource = listingResource;
	}

	public BrokerListings getListings(@Nullable Integer ticketId) {
		return listingResource.getListings(token, ticketId);
	}

}

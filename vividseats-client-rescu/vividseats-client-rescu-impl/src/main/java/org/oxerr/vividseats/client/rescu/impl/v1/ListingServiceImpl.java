package org.oxerr.vividseats.client.rescu.impl.v1;

import org.oxerr.vividseats.client.model.v1.Update;
import org.oxerr.vividseats.client.rescu.resource.v1.ListingResource;
import org.oxerr.vividseats.client.v1.ListingService;

public class ListingServiceImpl implements ListingService {

	private final ListingResource listingResource;

	public ListingServiceImpl(ListingResource listingResource) {
		this.listingResource = listingResource;
	}

	@Override
	public void updateListing(Update update) {
		// TODO Auto-generated method stub
		
	}

}

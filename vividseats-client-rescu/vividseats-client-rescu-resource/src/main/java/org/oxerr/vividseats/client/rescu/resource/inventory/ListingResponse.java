package org.oxerr.vividseats.client.rescu.resource.inventory;

import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.oxerr.vividseats.client.rescu.resource.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingResponse extends Response {

	private static final long serialVersionUID = 2024101201L;

	private BrokerListing listing;

	public ListingResponse(
		@JsonProperty("success") Boolean success,
		@JsonProperty("message") String message
	) {
		super(success, message);
	}

	public BrokerListing getListing() {
		return listing;
	}

	public void setListing(BrokerListing listing) {
		this.listing = listing;
	}

}

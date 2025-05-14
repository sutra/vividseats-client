package org.oxerr.vividseats.client.rescu.model.inventory;

import java.util.List;

import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.oxerr.vividseats.client.rescu.model.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingResponse extends Response {

	private static final long serialVersionUID = 2024101201L;

	private BrokerListing listing;

	public ListingResponse(
		@JsonProperty("success") Boolean success,
		@JsonProperty("message") String message,
		@JsonProperty("errors") List<String> errors
	) {
		super(success, message, errors);
	}

	public BrokerListing getListing() {
		return listing;
	}

	public void setListing(BrokerListing listing) {
		this.listing = listing;
	}

}

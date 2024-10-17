package org.oxerr.vividseats.client.rescu.resource.inventory;

import java.util.List;

import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.oxerr.vividseats.client.rescu.resource.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingsResponse extends Response {

	private static final long serialVersionUID = 2024092601L;

	private List<BrokerListing> listings;

	private String attribute;

	public ListingsResponse(
		@JsonProperty("success") Boolean success,
		@JsonProperty("message") String message
	) {
		super(success, message);
	}

	public List<BrokerListing> getListings() {
		return listings;
	}

	public void setListings(List<BrokerListing> listings) {
		this.listings = listings;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

}

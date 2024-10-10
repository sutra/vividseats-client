package org.oxerr.vividseats.client.model;

import java.util.List;

public class ListingsResponse extends Response {

	private static final long serialVersionUID = 2024092601L;

	private List<BrokerListing> listings;

	private String attribute;

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

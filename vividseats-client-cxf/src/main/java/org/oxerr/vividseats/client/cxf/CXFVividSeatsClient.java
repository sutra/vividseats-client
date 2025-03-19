package org.oxerr.vividseats.client.cxf;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

public class CXFVividSeatsClient {

	private static final String DEFAULT_BASE_URL = "https://brokers.vividseats.com/webservices";

	private final String token;

	private final ListingResource listingResource;

	public CXFVividSeatsClient(String token) {
		this.token = token;
		this.listingResource = JAXRSClientFactory.create(DEFAULT_BASE_URL, ListingResource.class);
	}

	public BrokerListings getListings(Integer ticketId) {
		return listingResource.getListings(token, ticketId);
	}

}

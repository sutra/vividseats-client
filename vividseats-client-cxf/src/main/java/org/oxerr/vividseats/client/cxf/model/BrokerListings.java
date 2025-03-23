package org.oxerr.vividseats.client.cxf.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "brokerListings")
public class BrokerListings {

	private List<BrokerListing> brokerListings;

	@XmlElement(name = "brokerListing")
	public List<BrokerListing> getBrokerListings() {
		return brokerListings;
	}

	public void setBrokerListings(List<BrokerListing> brokerListings) {
		this.brokerListings = brokerListings;
	}

}

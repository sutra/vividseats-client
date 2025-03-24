package org.oxerr.vividseats.client.cached.inventory;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.oxerr.ticket.inventory.support.Listing;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;

public class VividSeatsListing extends Listing<String, BrokerListing> {

	private static final long serialVersionUID = 2024101701L;

	private Integer marketplaceEventId;

	public VividSeatsListing() {
	}

	public VividSeatsListing(String id, Integer marketplaceEventId, BrokerListing request) {
		super(id, request);
		this.marketplaceEventId = marketplaceEventId;
	}

	public Integer getMarketplaceEventId() {
		return marketplaceEventId;
	}

	public void setMarketplaceEventId(Integer marketplaceEventId) {
		this.marketplaceEventId = marketplaceEventId;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof VividSeatsListing)) {
			return false;
		}
		VividSeatsListing rhs = (VividSeatsListing) obj;
		return EqualsBuilder.reflectionEquals(this, rhs);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

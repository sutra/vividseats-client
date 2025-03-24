package org.oxerr.vividseats.client.cached.inventory;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.oxerr.ticket.inventory.support.Event;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;

public class VividSeatsEvent extends Event<String, String, BrokerListing, VividSeatsListing> {

	private static final long serialVersionUID = 2025032401L;

	private Integer marketplaceEventId;

	public VividSeatsEvent() {
		this(null, null, null, Collections.emptyList());
	}

	public VividSeatsEvent(String id, OffsetDateTime startDate, Integer marketplaceEventId) {
		this(id, startDate, marketplaceEventId, Collections.emptyList());
	}

	public VividSeatsEvent(String id, OffsetDateTime startDate, Integer marketplaceEventId, List<VividSeatsListing> listings) {
		super(id, startDate, listings);
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
		if (!(obj instanceof VividSeatsEvent)) {
			return false;
		}
		VividSeatsEvent rhs = (VividSeatsEvent) obj;
		return EqualsBuilder.reflectionEquals(this, rhs);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

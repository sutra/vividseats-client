package org.oxerr.vividseats.client.cached.inventory;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.oxerr.ticket.inventory.support.Event;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;

public class VividSeatsEvent extends Event<String, String, BrokerListing, VividSeatsListing> {

	private static final long serialVersionUID = 2023031901L;

	private Integer vividSeatsEventId;

	public VividSeatsEvent() {
		this(null, null, null, Collections.emptyList());
	}

	public VividSeatsEvent(String id, OffsetDateTime startDate, Integer vividSeatsEventId) {
		this(id, startDate, vividSeatsEventId, Collections.emptyList());
	}

	public VividSeatsEvent(String id, OffsetDateTime startDate, Integer vividSeatsEventId, List<VividSeatsListing> listings) {
		super(id, startDate, listings);
		this.vividSeatsEventId = vividSeatsEventId;
	}

	public Integer getVividSeatsEventId() {
		return vividSeatsEventId;
	}

	public void setVividSeatsEventId(Integer vividSeatsEventId) {
		this.vividSeatsEventId = vividSeatsEventId;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}

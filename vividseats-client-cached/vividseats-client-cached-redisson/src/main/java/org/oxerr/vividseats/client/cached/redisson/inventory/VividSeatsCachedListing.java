package org.oxerr.vividseats.client.cached.redisson.inventory;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.oxerr.ticket.inventory.support.cached.redisson.CachedListing;
import org.oxerr.ticket.inventory.support.cached.redisson.Status;
import org.oxerr.vividseats.client.cached.inventory.VividSeatsEvent;
import org.oxerr.vividseats.client.cached.inventory.VividSeatsListing;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;

public class VividSeatsCachedListing extends CachedListing<BrokerListing> {

	private static final long serialVersionUID = 2024101701L;

	private VividSeatsCachedEvent event;

	public VividSeatsCachedListing() {
	}

	public VividSeatsCachedListing(VividSeatsEvent event, VividSeatsListing listing, Status status) {
		this(new VividSeatsCachedEvent(event), listing, status);
	}

	public VividSeatsCachedListing(VividSeatsCachedEvent event, VividSeatsListing listing, Status status) {
		this(event, listing.getRequest(), status);
	}

	public VividSeatsCachedListing(VividSeatsCachedEvent event, BrokerListing request, Status status) {
		super(request, status);
		this.event = event;
	}

	public VividSeatsCachedEvent getEvent() {
		return event;
	}

	public void setEvent(VividSeatsCachedEvent event) {
		this.event = event;
	}

	public VividSeatsListing toVividSeatsListing() {
		return new VividSeatsListing(this.getRequest().getTicketId(), this.event.getMarketplaceEventId(), this.getRequest());
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
		if (!(obj instanceof VividSeatsCachedListing)) {
			return false;
		}
		VividSeatsCachedListing rhs = (VividSeatsCachedListing) obj;
		return EqualsBuilder.reflectionEquals(this, rhs);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

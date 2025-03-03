package org.oxerr.vividseats.client.cached.redisson.inventory;

import java.io.Serializable;
import java.time.OffsetDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.oxerr.vividseats.client.cached.inventory.VividSeatsEvent;

public class VividSeatsCachedEvent implements Serializable {

	private static final long serialVersionUID = 2024101701L;

	private String id;

	private Integer vividSeatsEventId;

	private OffsetDateTime startDate;

	public VividSeatsCachedEvent() {
	}

	public VividSeatsCachedEvent(String id, Integer vividSeatsEventId, OffsetDateTime startDate) {
		this.id = id;
		this.vividSeatsEventId = vividSeatsEventId;
		this.startDate = startDate;
	}

	public VividSeatsCachedEvent(VividSeatsEvent event) {
		this(event.getId(), event.getVividSeatsEventId(), event.getStartDate());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVividSeatsEventId() {
		return vividSeatsEventId;
	}

	public void setVividSeatsEventId(Integer vividSeatsEventId) {
		this.vividSeatsEventId = vividSeatsEventId;
	}

	public OffsetDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}

	public VividSeatsEvent toVividSeatsEvent() {
		return new VividSeatsEvent(id, startDate, vividSeatsEventId);
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
		if (!(obj instanceof VividSeatsCachedEvent)) {
			return false;
		}
		VividSeatsCachedEvent rhs = (VividSeatsCachedEvent) obj;
		return EqualsBuilder.reflectionEquals(this, rhs);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

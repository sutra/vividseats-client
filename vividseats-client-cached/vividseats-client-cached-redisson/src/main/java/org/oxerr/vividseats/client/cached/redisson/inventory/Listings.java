package org.oxerr.vividseats.client.cached.redisson.inventory;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;

final class Listings {

	private Listings() {
		throw new AssertionError("No " + this.getClass() + " instances for you!");
	}

	public static boolean isSame(BrokerListing a, BrokerListing b) {
		return Stream.of(
			Objects.equals(a.getSection(), b.getSection()),
			Objects.equals(a.getRow(), b.getRow()),
			Objects.equals(a.getSeatFrom(), b.getSeatFrom()),
			Objects.equals(a.getSeatThru(), b.getSeatThru()),
			Objects.equals(a.getQuantity(), b.getQuantity()),
			Objects.compare(a.getPrice(), b.getPrice(), BigDecimal::compareTo) == 0,
			Objects.equals(a.getNotes(), b.getNotes())
		).allMatch(Boolean::booleanValue);
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
		if (!(obj instanceof Listings)) {
			return false;
		}
		Listings rhs = (Listings) obj;
		return EqualsBuilder.reflectionEquals(this, rhs);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

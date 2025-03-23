package org.oxerr.vividseats.client.cached.inventory;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The check params implements the {@link CheckOptions}.
 */
public class CheckParams implements CheckOptions {

	/**
	 * The chunk size to load keys from cache.
	 */
	private int chunkSize = 0;

	@Override
	public CheckOptions chunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
		return this;
	}

	public int chunkSize() {
		return chunkSize;
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
		if (!(obj instanceof CheckParams)) {
			return false;
		}
		CheckParams rhs = (CheckParams) obj;
		return EqualsBuilder.reflectionEquals(this, rhs);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

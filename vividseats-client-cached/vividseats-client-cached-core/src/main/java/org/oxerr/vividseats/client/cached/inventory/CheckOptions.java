package org.oxerr.vividseats.client.cached.inventory;

/**
 * The options in check listings.
 *
 * @since 6.6.0
 */
public interface CheckOptions {

	static CheckOptions defaults() {
		return new CheckParams();
	}

	CheckOptions chunkSize(int chunkSize);

	int chunkSize();

}

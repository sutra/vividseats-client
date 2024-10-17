package org.oxerr.vividseats.client.cached;

import org.oxerr.vividseats.client.VividSeatsClient;
import org.oxerr.vividseats.client.cached.inventory.VividSeatsCachedListingService;

public interface CachedVividSeatsClient {

	VividSeatsClient getClient();

	VividSeatsCachedListingService getCachedListingService();

}

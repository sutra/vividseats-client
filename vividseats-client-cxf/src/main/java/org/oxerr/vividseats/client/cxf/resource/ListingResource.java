package org.oxerr.vividseats.client.cxf.resource;

import java.util.concurrent.TimeUnit;

import org.oxerr.vividseats.client.cxf.model.BrokerListings;

import io.github.poshjosh.ratelimiter.annotations.Rate;
import jakarta.annotation.Nullable;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/listings/v1")
public interface ListingResource {

	/**
	 * XML response
	 * The getListings method will return active listings in your broker account.
	 *
	 * Rate limit: 1 request per ticketId every 2 minutes.
	 *
	 * @param apiToken the API token.
	 * @param ticketId the ticket ID.
	 * @return the broker listings.
	 */
	@GET
	@Path("/getListings")
	@Produces(MediaType.APPLICATION_XML)
	@Rate(permits = 1, duration = 2, timeUnit = TimeUnit.MINUTES)
	BrokerListings getListings(
		@QueryParam("apiToken") String apiToken,
		@QueryParam("ticketId") @Nullable Integer ticketId
	);

}

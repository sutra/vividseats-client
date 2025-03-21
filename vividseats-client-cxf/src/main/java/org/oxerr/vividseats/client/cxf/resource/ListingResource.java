package org.oxerr.vividseats.client.cxf.resource;

import java.util.concurrent.TimeUnit;

import org.oxerr.vividseats.client.cxf.model.BrokerListings;

import io.github.poshjosh.ratelimiter.annotations.Rate;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/listings/v1")
public interface ListingResource {

	@GET
	@Path("/getListings")
	@Produces(MediaType.APPLICATION_XML)
	@Rate(permits = 1, duration = 2, timeUnit = TimeUnit.MINUTES)
	BrokerListings getListings(
		@QueryParam("apiToken") String apiToken,
		@QueryParam("ticketId") Integer ticketId
	);

}

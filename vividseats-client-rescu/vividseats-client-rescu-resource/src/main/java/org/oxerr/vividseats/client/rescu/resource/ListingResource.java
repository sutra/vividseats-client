package org.oxerr.vividseats.client.rescu.resource;

import org.oxerr.vividseats.client.model.ListingsResponse;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/listings/v2")
public interface ListingResource {

	/**
	 * Retrieves listings.
	 *
	 * Rate limit: 10 requests per second.
	 *
	 * @param fromEventDate
	 * @param toEventDate
	 * @param listingId
	 * @param internalTicketId
	 * @param productionId
	 * @param headlinerId
	 * @param includeFiles
	 * @return
	 */
	@GET
	@Path("/get")
	ListingsResponse get(
		@QueryParam("fromEventDate") String fromEventDate,
		@QueryParam("toEventDate") String toEventDate,
		@QueryParam("listingId") Long listingId,
		@QueryParam("internalTicketId") String internalTicketId,
		@QueryParam("productionId") Integer productionId,
		@QueryParam("headlinerId") Integer headlinerId,
		@QueryParam("includeFiles") Boolean includeFiles
	);

}

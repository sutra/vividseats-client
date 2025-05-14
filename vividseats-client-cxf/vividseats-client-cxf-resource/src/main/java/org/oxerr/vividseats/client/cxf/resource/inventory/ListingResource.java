package org.oxerr.vividseats.client.cxf.resource.inventory;

import java.time.LocalDateTime;

import org.oxerr.vividseats.client.cxf.model.inventory.ListingResponse;
import org.oxerr.vividseats.client.cxf.model.inventory.ListingsResponse;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;

import io.github.poshjosh.ratelimiter.annotations.Rate;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/listings/v2")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ListingResource {

	/**
	 * Retrieves listings.
	 *
	 * Rate limit: 10 requests per second.
	 *
	 * @param listingId The listing id.
	 * @param internalTicketId The internal ticket id.
	 * @param productionId The production id.
	 * @param headlinerId The headliner id.
	 * @param fromEventDate The start date of the event.
	 * @param toEventDate The end date of the event.
	 * @param includeFiles Whether to include files.
	 * @return listings.
	 */
	@GET
	@Path("/get")
	@Rate(10)
	ListingsResponse get(
		@QueryParam("listingId") Long listingId,
		@QueryParam("internalTicketId") String internalTicketId,
		@QueryParam("productionId") Integer productionId,
		@QueryParam("headlinerId") Integer headlinerId,
		@QueryParam("fromEventDate") LocalDateTime fromEventDate,
		@QueryParam("toEventDate") LocalDateTime toEventDate,
		@QueryParam("includeFiles") Boolean includeFiles
	);

	/**
	 * Creates a listing.
	 *
	 * Either productionId or eventName, venue, eventDate is required. If the
	 * eventName|venue|eventDate parameters are used, the create request may be sent
	 * to mapping. If the productionId is included in the request or our system can
	 * determine the productionId from the eventName|venue|eventDate parameters, the
	 * listing will be returned in the response. This method will only work if the
	 * Content-Type header is set to application/json.
	 *
	 * Rate limit: 50 requests per second.
	 *
	 * @param brokerListing The broker listing to create.
	 * @return the created listing.
	 */
	@POST
	@Path("/create")
	@Rate(50)
	ListingResponse create(BrokerListing brokerListing);

	/**
	 * Updates a listing.
	 *
	 * All the fields including attributes and tickets will be updated with the
	 * object passed in. This method will only work if the Content-Type header is
	 * set to application/json. An alternative version is /listings/v1/updateListing
	 * as it only requires you to supply your internal ticketID. If you want to use
	 * v2 and you only have a ticketId then you need to get the listing by ticket id
	 * first.
	 *
	 * Rate limit: 50 requests per second.
	 *
	 * @param brokerListing The broker listing to update.
	 * @return the response.
	 */
	@PUT
	@Path("/update")
	@Rate(50)
	Response update(BrokerListing brokerListing);

	/**
	 * Deletes a listing.
	 *
	 * Either the ticketId or listingId is required.
	 *
	 * Rate limit: 50 requests per second.
	 *
	 * @param listingId The listing id.
	 * @param internalTicketId The internal ticket id.
	 * @return the response.
	 */
	@DELETE
	@Path("/delete")
	@Rate(50)
	Response delete(
		@QueryParam("listingId") Long listingId,
		@QueryParam("internalTicketId") String internalTicketId
	);

}

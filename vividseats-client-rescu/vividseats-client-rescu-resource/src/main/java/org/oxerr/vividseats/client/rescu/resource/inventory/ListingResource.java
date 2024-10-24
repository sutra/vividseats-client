package org.oxerr.vividseats.client.rescu.resource.inventory;

import java.io.IOException;

import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.oxerr.vividseats.client.rescu.resource.Response;
import org.oxerr.vividseats.client.rescu.resource.VividSeatsException;

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

@Path("/listings/v2")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ListingResource {

	/**
	 * Retrieves listings.
	 *
	 * Rate limit: 10 requests per second.
	 *
	 * @param fromEventDate The start date of the event.
	 * @param toEventDate The end date of the event.
	 * @param listingId The listing id.
	 * @param internalTicketId The internal ticket id.
	 * @param productionId The production id.
	 * @param headlinerId The headliner id.
	 * @param includeFiles Whether to include files.
	 * @return listings.
	 * @throws IOException if an I/O error occurs.
	 * @throws VividSeatsException if an error response is returned.
	 */
	@GET
	@Path("/get")
	@Rate(10)
	ListingsResponse get(
		@QueryParam("fromEventDate") String fromEventDate,
		@QueryParam("toEventDate") String toEventDate,
		@QueryParam("listingId") Long listingId,
		@QueryParam("internalTicketId") String internalTicketId,
		@QueryParam("productionId") Integer productionId,
		@QueryParam("headlinerId") Integer headlinerId,
		@QueryParam("includeFiles") Boolean includeFiles
	) throws IOException, VividSeatsException;

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
	 * @throws IOException if an I/O error occurs.
	 * @throws VividSeatsException if an error response is returned.
	 */
	@POST
	@Path("/create")
	@Rate(50)
	ListingResponse create(BrokerListing brokerListing) throws IOException, VividSeatsException;

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
	 * @throws IOException if an I/O error occurs.
	 * @throws VividSeatsException if an error response is returned.
	 */
	@PUT
	@Path("/update")
	@Rate(50)
	Response update(BrokerListing brokerListing) throws IOException, VividSeatsException;

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
	 * @throws IOException if an I/O error occurs.
	 * @throws VividSeatsException if an error response is returned.
	 */
	@DELETE
	@Path("/delete")
	@Rate(50)
	Response delete(
		@QueryParam("listingId") Long listingId,
		@QueryParam("internalTicketId") String internalTicketId
	) throws IOException, VividSeatsException;

}

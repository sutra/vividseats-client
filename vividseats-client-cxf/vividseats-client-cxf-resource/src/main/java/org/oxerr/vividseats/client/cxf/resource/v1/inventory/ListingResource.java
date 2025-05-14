package org.oxerr.vividseats.client.cxf.resource.v1.inventory;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.oxerr.vividseats.client.cxf.model.Response;
import org.oxerr.vividseats.client.cxf.model.v1.inventory.BrokerListings;
import org.oxerr.vividseats.client.model.inventory.SplitType;

import io.github.poshjosh.ratelimiter.annotations.Rate;
import jakarta.annotation.Nullable;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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


	/**
	 * Delete a listing.
	 *
	 * XML or JSON response.
	 *
	 * The deleteListing method allows you to delete a listing in real-time.
	 *
	 * Not rate limited
	 *
	 * @param apiToken The API token.
	 * @param ticketId The ticket ID.
	 * @return the response of the deletion request.
	 */
	@GET
	@Path("/deleteListing")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Response deleteListing(
		@QueryParam("apiToken") CharSequence apiToken,
		@QueryParam("ticketId") String ticketId
	);

	/**
	 * Updates a listing.
	 *
	 * XML or JSON responses.
	 *
	 * The updateListing method allows you to update a listing in real-time.
	 * Only parameters you pass in to the method will be updated.
	 * The ticketId is your internal unique ID associated with the listing.
	 *
	 * Not rate limited
	 *
	 * @param apiToken The API token.
	 * @param ticketId The ticket ID.
	 * @param quantity The quantity of tickets.
	 * @param section The section.
	 * @param row The row.
	 * @param seatFrom The seat from.
	 * @param seatThru The seat thru.
	 * @param notes The notes.
	 * @param price The price.
	 * @param electronic The electronic.
	 * @param inHandDate The in hand date.
	 * @param splitType The split type.
	 * @param splitValue The split value.
	 * @param barcode The barcode.
	 * @param faceValue The face value.
	 * @param unitTaxedCost The unit taxed cost.
	 * @return the response.
	 * @see <a href="https://vividseats.stoplight.io/docs/broker-portal/ebf6bb237e97b-update-listing">Update Listing</a>
	 */
	@POST
	@Path("/updateListing")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Response updateListing(
		@FormParam("apiToken") CharSequence apiToken,
		@FormParam("ticketId") String ticketId,
		@FormParam("quantity") Integer quantity,
		@FormParam("section") String section,
		@FormParam("row") String row,
		@FormParam("seatFrom") String seatFrom,
		@FormParam("seatThru") String seatThru,
		@FormParam("notes") String notes,
		@FormParam("price") BigDecimal price,
		@FormParam("electronic") Boolean electronic,
		@FormParam("inHandDate") String inHandDate,
		@FormParam("splitType") SplitType splitType,
		@FormParam("splitValue") String splitValue,
		@FormParam("barcode") List<String> barcode,
		@FormParam("faceValue") BigDecimal faceValue,
		@FormParam("unitTaxedCost") BigDecimal unitTaxedCost
	);

}

package org.oxerr.vividseats.client.rescu.resource.v1.inventory;

import java.math.BigDecimal;
import java.util.List;

import org.oxerr.vividseats.client.model.inventory.SplitType;
import org.oxerr.vividseats.client.rescu.model.Response;
import org.oxerr.vividseats.client.rescu.model.VividSeatsException;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/listings/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ListingResource {

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
	 * @throws VividSeatsException if an error occurs.
	 */
	@GET
	@Path("/deleteListing")
	Response deleteListing(
		@QueryParam("apiToken") CharSequence apiToken,
		@QueryParam("ticketId") String ticketId
	) throws VividSeatsException;

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
	 * @throws VividSeatsException if an error occurs.
	 * @see <a href="https://vividseats.stoplight.io/docs/broker-portal/ebf6bb237e97b-update-listing">Update Listing</a>
	 */
	@POST
	@Path("/updateListing")
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
	) throws VividSeatsException;

}

package org.oxerr.vividseats.client.rescu.resource.v1;

import java.io.IOException;

import org.oxerr.vividseats.client.model.v1.UpdateListingRequest;
import org.oxerr.vividseats.client.rescu.resource.Response;
import org.oxerr.vividseats.client.rescu.resource.VividSeatsException;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/listings/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ListingResource {

	@POST
	@Path("/updateListing")
	Response update(UpdateListingRequest updateListingRequest) throws IOException, VividSeatsException;

}

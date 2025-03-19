package org.oxerr.vividseats.client.cxf;

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
	BrokerListings getListings(
		@QueryParam("apiToken") String apiToken,
		@QueryParam("ticketId") Integer ticketId
	);

}

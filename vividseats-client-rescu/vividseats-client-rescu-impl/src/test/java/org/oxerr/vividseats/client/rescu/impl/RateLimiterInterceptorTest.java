package org.oxerr.vividseats.client.rescu.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.oxerr.rescu.ext.singleton.RestProxyFactorySingletonImpl;
import org.oxerr.vividseats.client.rescu.resource.ListingsResponse;
import org.oxerr.vividseats.client.rescu.resource.VividSeatsException;

import io.github.poshjosh.ratelimiter.annotations.Rate;
import io.github.poshjosh.ratelimiter.store.BandwidthsStore;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import si.mazi.rescu.ClientConfig;
import si.mazi.rescu.IRestProxyFactory;
import si.mazi.rescu.RestProxyFactoryImpl;

@Disabled("Token is required")
class RateLimiterInterceptorTest {

	private final Logger log = LogManager.getLogger();

	@Path("/listings/v2")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public interface ListingResource {

		@GET
		@Path("/get")
		@Rate(permits = 1, duration = 3, timeUnit = TimeUnit.SECONDS)
		ListingsResponse get(
			@QueryParam("fromEventDate") String fromEventDate,
			@QueryParam("toEventDate") String toEventDate,
			@QueryParam("listingId") Long listingId,
			@QueryParam("internalTicketId") String internalTicketId,
			@QueryParam("productionId") Integer productionId,
			@QueryParam("headlinerId") Integer headlinerId,
			@QueryParam("includeFiles") Boolean includeFiles
		) throws IOException, VividSeatsException;

		@GET
		@Path("/get")
		@Rate(permits = 1, duration = 3, timeUnit = TimeUnit.SECONDS)
		ListingsResponse get() throws IOException, VividSeatsException;

	}

	@Test
	void test() throws VividSeatsException, IOException {
		var baseUrl = "https://brokers.vividseats.com/webservices";
		var clientConfig = new ClientConfig();
		clientConfig.addDefaultParam(HeaderParam.class, "Api-token", ResCUVividSeatsClientTest.getProps().get("token"));
		var rateLimiterInterceptor = new RateLimiterInterceptor(BandwidthsStore.ofDefaults());
		IRestProxyFactory restProxyFactory = new RestProxyFactorySingletonImpl(new RestProxyFactoryImpl());
		ListingResource listingResource = restProxyFactory.createProxy(ListingResource.class, baseUrl, clientConfig, rateLimiterInterceptor);

		int i = 0;
		for (; i < 1; i++) {

			log.info("Invocation {} of 2 should not wait", i);
			listingResource.get(null, null, null, null, null, null, null);
		}

		log.info("Invocation {} of 2 should wait", i);
		assertNotNull(listingResource.get(null, null, null, null, null, null, null));

		log.info("Another method invocation {} should not wait", i);
		assertNotNull(listingResource.get());
	}

}

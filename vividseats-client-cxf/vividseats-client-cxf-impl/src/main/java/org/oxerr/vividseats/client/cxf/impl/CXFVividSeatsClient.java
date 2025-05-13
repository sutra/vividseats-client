package org.oxerr.vividseats.client.cxf.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.oxerr.vividseats.client.VividSeatsClient;
import org.oxerr.vividseats.client.cxf.impl.inventory.ListingServiceImpl;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;

import io.github.poshjosh.ratelimiter.store.BandwidthsStore;

public class CXFVividSeatsClient implements VividSeatsClient {

	private static final String DEFAULT_BASE_URL = "https://brokers.vividseats.com/webservices";

	private final ListingServiceImpl listingService;

	private final HTTPClientPolicy policy;

	public CXFVividSeatsClient(String token, BandwidthsStore<String> bandwidthsStore) {
		this(token, bandwidthsStore, new HTTPClientPolicy());
	}

	public CXFVividSeatsClient(String token, BandwidthsStore<String> bandwidthsStore, HTTPClientPolicy policy) {
		this.policy = policy;

		org.oxerr.vividseats.client.cxf.resource.v1.inventory.ListingResource listingResourceV1 = createProxy(
			DEFAULT_BASE_URL,
			org.oxerr.vividseats.client.cxf.resource.v1.inventory.ListingResource.class,
			Collections.singletonList(new RateLimiterFilter(bandwidthsStore))
		);

		var providers = List.of(
			createJacksonJsonProvider(),
			new RateLimiterFilter(bandwidthsStore),
			new ApiTokenHeaderFilter(token)
		);

		org.oxerr.vividseats.client.cxf.resource.inventory.ListingResource listingResource = createProxy(
			DEFAULT_BASE_URL,
			org.oxerr.vividseats.client.cxf.resource.inventory.ListingResource.class,
			providers
		);
		this.listingService = new ListingServiceImpl(listingResourceV1, listingResource, token);
	}

	@Override
	public ListingServiceImpl getListingService() {
		return listingService;
	}

	@SuppressWarnings("unchecked")
	protected <T> T createProxy(String baseAddress, Class<T> cls, List<?> providers) {
		T client = JAXRSClientFactory.create(baseAddress, cls, providers);

		configureTimeouts(client, policy);

		return (T) Proxy.newProxyInstance(
			cls.getClassLoader(),
			new Class<?>[] { cls },
			new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					// Store method info in ThreadLocal
					InvokedMethodHolder.set(method);
					return method.invoke(client, args);
				}
			}
		);
	}

	protected <T> void configureTimeouts(T client, HTTPClientPolicy policy) {
		// Get CXF-specific client configuration
		ClientConfiguration config = WebClient.getConfig(client);

		// Access HTTPConduit to set timeouts
		HTTPConduit conduit = (HTTPConduit) config.getConduit();

		conduit.setClient(policy);
	}

	protected JacksonJsonProvider createJacksonJsonProvider() {
		JacksonJsonProvider provider = new JacksonJsonProvider();
		provider.setMapper(createObjectMapper());
		return provider;
	}

	protected ObjectMapper createObjectMapper() {
		return new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.setSerializationInclusion(Include.NON_ABSENT)
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
			.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

}

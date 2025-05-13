package org.oxerr.vividseats.client.cxf.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.oxerr.vividseats.client.cxf.impl.inventory.ListingServiceImpl;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;

import io.github.poshjosh.ratelimiter.store.BandwidthsStore;

public class CXFVividSeatsClient {

	private static final String DEFAULT_BASE_URL = "https://brokers.vividseats.com/webservices";

	private final ListingServiceImpl listingService;

	public CXFVividSeatsClient(String token, BandwidthsStore<String> bandwidthsStore) {
		org.oxerr.vividseats.client.cxf.resource.v1.inventory.ListingResource listingResourceV1 = createProxy(
			DEFAULT_BASE_URL,
			org.oxerr.vividseats.client.cxf.resource.v1.inventory.ListingResource.class,
			Collections.singletonList(new RateLimiterFilter(bandwidthsStore))
		);

		JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
		jacksonJsonProvider.setMapper(createObjectMapper());
		var providers = List.of(
			jacksonJsonProvider,
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

	public ListingServiceImpl getListingService() {
		return listingService;
	}

	@SuppressWarnings("unchecked")
	public static <T> T createProxy(String baseAddress, Class<T> cls, List<?> providers) {
		T client = JAXRSClientFactory.create(baseAddress, cls, providers);

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

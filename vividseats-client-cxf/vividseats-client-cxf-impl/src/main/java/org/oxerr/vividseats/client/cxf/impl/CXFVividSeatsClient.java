package org.oxerr.vividseats.client.cxf.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.function.Consumer;

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

	public CXFVividSeatsClient(String token, BandwidthsStore<String> bandwidthsStore) {
		this(token, bandwidthsStore, new HTTPClientPolicy(), config -> {});
	}

	public CXFVividSeatsClient(String token, BandwidthsStore<String> bandwidthsStore, HTTPClientPolicy policy) {
		this(token, bandwidthsStore, policy, config -> {});
	}

	public CXFVividSeatsClient(
		String token,
		BandwidthsStore<String> bandwidthsStore,
		HTTPClientPolicy policy,
		Consumer<ClientConfiguration> configurer
	) {
		Consumer<ClientConfiguration> internalConfigurer = config -> {
			HTTPConduit conduit = (HTTPConduit) config.getConduit();
			conduit.setClient(policy);
			configurer.accept(config);
		};

		var jacksonJsonProvider = createJacksonJsonProvider();
		var rateLimiterFilter = new RateLimiterFilter(bandwidthsStore);
		var tokenFilter = new ApiTokenHeaderFilter(token);

		var listingResourceV1 = createProxy(
			DEFAULT_BASE_URL,
			org.oxerr.vividseats.client.cxf.resource.v1.inventory.ListingResource.class,
			List.of(jacksonJsonProvider, rateLimiterFilter),
			internalConfigurer
		);

		var providers = List.of(
			jacksonJsonProvider,
			rateLimiterFilter,
			tokenFilter
		);

		var listingResource = createProxy(
			DEFAULT_BASE_URL,
			org.oxerr.vividseats.client.cxf.resource.inventory.ListingResource.class,
			providers,
			internalConfigurer
		);
		this.listingService = new ListingServiceImpl(listingResourceV1, listingResource, token);
	}

	@Override
	public ListingServiceImpl getListingService() {
		return listingService;
	}

	protected <T> T createProxy(
		String baseAddress,
		Class<T> cls,
		List<?> providers,
		Consumer<ClientConfiguration> configurer
	) {
		T client = JAXRSClientFactory.create(baseAddress, cls, providers);
		configurer.accept(WebClient.getConfig(client));
		return createMethodTrackingProxy(cls, client);
	}

	@SuppressWarnings("unchecked")
	private <T> T createMethodTrackingProxy(Class<T> cls, T delegate) {
		InvocationHandler handler = (proxy, method, args) -> {
			InvokedMethodHolder.set(method);
			return method.invoke(delegate, args);
		};

		return (T) Proxy.newProxyInstance(
			cls.getClassLoader(),
			new Class<?>[] { cls },
			handler
		);
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

package org.oxerr.vividseats.client.rescu.impl;

import java.util.function.Supplier;

import org.apache.commons.lang3.ArrayUtils;
import org.oxerr.rescu.ext.singleton.RestProxyFactorySingletonImpl;
import org.oxerr.vividseats.client.ListingService;
import org.oxerr.vividseats.client.VividSeatsClient;
import org.oxerr.vividseats.client.rescu.resource.ListingResource;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.github.poshjosh.ratelimiter.store.BandwidthsStore;
import jakarta.ws.rs.HeaderParam;
import si.mazi.rescu.ClientConfig;
import si.mazi.rescu.IRestProxyFactory;
import si.mazi.rescu.Interceptor;
import si.mazi.rescu.RestProxyFactoryImpl;
import si.mazi.rescu.serialization.jackson.DefaultJacksonObjectMapperFactory;
import si.mazi.rescu.serialization.jackson.JacksonObjectMapperFactory;

public class ResCUVividSeatsClient implements VividSeatsClient {

	private static final String DEFAULT_BASE_URL = "https://brokers.vividseats.com/webservices";

	private final String baseUrl;

	private final ClientConfig clientConfig;

	private final IRestProxyFactory restProxyFactory;

	private final ListingService listingService;

	/**
	 * Constructs a client with the specified token.
	 *
	 * @param token the token to access the API.
	 * @param interceptors the interceptors to intercept the requests.
	 */
	public ResCUVividSeatsClient(String token, Interceptor... interceptors) {
		this(DEFAULT_BASE_URL, token, interceptors);
	}

	/**
	 * Constructs a client with the specified base URL and token.
	 *
	 * @param baseUrl the base URL of the API.
	 * @param token the token to access the API.
	 * @param interceptors the interceptors to intercept the requests.
	 */
	public ResCUVividSeatsClient(String baseUrl, String token, Interceptor... interceptors) {
		this(baseUrl, () -> token, interceptors);
	}

	/**
	 * Constructs a client with the specified token.
	 *
	 * @param token the token to access the API.
	 * @param interceptors the interceptors to intercept the requests.
	 */
	public ResCUVividSeatsClient(Supplier<String> token, Interceptor... interceptors) {
		this(DEFAULT_BASE_URL, token, interceptors);
	}

	/**
	 * Constructs a client with the specified base URL and token.
	 *
	 * @param baseUrl the base URL of the API.
	 * @param tokenSupplier the token supplier to access the API.
	 * @param interceptors the interceptors to intercept the requests.
	 */
	public ResCUVividSeatsClient(String baseUrl, Supplier<String> tokenSupplier, Interceptor... interceptors) {
		this(baseUrl, tokenSupplier, BandwidthsStore.ofDefaults(), interceptors);
	}

	/**
	 * Constructs a client with the specified base URL, token supplier, bandwidths store and interceptors.
	 *
	 * @param baseUrl the base URL of the API.
	 * @param tokenSupplier the token supplier to access the API.
	 * @param bandwidthsStore the bandwidths store to store the bandwidth.
	 * @param interceptors the interceptors to intercept the requests.
	 */
	public ResCUVividSeatsClient(String baseUrl, Supplier<String> tokenSupplier, BandwidthsStore<String> bandwidthsStore, Interceptor... interceptors) {
		this.baseUrl = baseUrl;

		JacksonObjectMapperFactory jacksonObjectMapperFactory = new DefaultJacksonObjectMapperFactory() {

			@Override
			public void configureObjectMapper(ObjectMapper objectMapper) {
				super.configureObjectMapper(objectMapper);
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.setSerializationInclusion(Include.NON_ABSENT);
				objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
				objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
				objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
			}

		};

		this.clientConfig = new ClientConfig();
		clientConfig.addDefaultParam(HeaderParam.class, "Api-token", new Object() {

			@Override
			public String toString() {
				return tokenSupplier.get();
			}

		});
		clientConfig.setJacksonObjectMapperFactory(jacksonObjectMapperFactory);

		this.restProxyFactory = new RestProxyFactorySingletonImpl(new RestProxyFactoryImpl());

		this.listingService = new ListingServiceImpl(createProxy(ListingResource.class, bandwidthsStore, interceptors));
	}

	@Override
	public ListingService getListingService() {
		return this.listingService;
	}

	protected <I> I createProxy(Class<I> restInterface, BandwidthsStore<String> bandwidthsStore, Interceptor... interceptors) {
		var rateLimiterInterceptor = new RateLimiterInterceptor(bandwidthsStore);
		return createProxy(restInterface, ArrayUtils.add(interceptors, rateLimiterInterceptor));
	}

	protected <I> I createProxy(Class<I> restInterface, Interceptor... interceptors) {
		return this.restProxyFactory.createProxy(restInterface, baseUrl, this.clientConfig, interceptors);
	}

}

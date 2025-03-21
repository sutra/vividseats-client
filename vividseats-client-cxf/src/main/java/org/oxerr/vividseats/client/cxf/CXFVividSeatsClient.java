package org.oxerr.vividseats.client.cxf;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.oxerr.vividseats.client.cxf.model.BrokerListings;
import org.oxerr.vividseats.client.cxf.resource.ListingResource;

import io.github.poshjosh.ratelimiter.store.BandwidthsStore;

public class CXFVividSeatsClient {

	private static final String DEFAULT_BASE_URL = "https://brokers.vividseats.com/webservices";

	private final String token;

	private final ListingResource listingResource;

	public CXFVividSeatsClient(String token, BandwidthsStore<String> bandwidthsStore) {
		this.token = token;
		this.listingResource = createProxy(
			DEFAULT_BASE_URL,
			ListingResource.class,
			Collections.singletonList(new RateLimiterFilter(bandwidthsStore))
		);
	}

	public BrokerListings getListings(Integer ticketId) {
		return listingResource.getListings(token, ticketId);
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

}

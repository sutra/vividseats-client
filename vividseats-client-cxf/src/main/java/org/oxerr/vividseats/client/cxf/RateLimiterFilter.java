package org.oxerr.vividseats.client.cxf;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.poshjosh.ratelimiter.RateLimiter;
import io.github.poshjosh.ratelimiter.RateLimiterContext;
import io.github.poshjosh.ratelimiter.RateLimiterRegistries;
import io.github.poshjosh.ratelimiter.annotations.Rate;
import io.github.poshjosh.ratelimiter.store.BandwidthsStore;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;

/**
 * JAX-RS client request filter that applies rate limiting based on @Rate annotations.
 */
public class RateLimiterFilter implements ClientRequestFilter {

	private final Logger log = LoggerFactory.getLogger(RateLimiterFilter.class);

	// Store rate limits for each API method
	private final ConcurrentMap<Method, RateLimiter> limiters = new ConcurrentHashMap<>();

	private final BandwidthsStore<String> bandwidthsStore;

	public RateLimiterFilter(BandwidthsStore<String> bandwidthsStore) {
		this.bandwidthsStore = bandwidthsStore;
	}

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		Method method = InvokedMethodHolder.get();
		log.trace("Invoked method: {}", method);

		// Retrieve @Rate annotation
		Rate rate = method.getAnnotation(Rate.class);
		if (rate != null) {
			// Get or create a rate limiter for this method
			var limiter = limiters.computeIfAbsent(method, this::getRateLimiter);

			// Enforce rate limiting
			log.trace("Acquiring permit for {}...", method);
			var timeSpent = limiter.acquire();
			log.trace("Acquired permit for {} in {} seconds.", method, timeSpent);
		}
	}

	private RateLimiter getRateLimiter(Method method) {
		var context = RateLimiterContext.builder().classes(method.getDeclaringClass()).store(bandwidthsStore).build();
		return RateLimiterRegistries.of(context).getMethodRateLimiter(method);
	}

}

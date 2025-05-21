package org.oxerr.vividseats.client.rescu.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.poshjosh.ratelimiter.RateLimiter;
import io.github.poshjosh.ratelimiter.RateLimiterContext;
import io.github.poshjosh.ratelimiter.RateLimiterRegistries;
import io.github.poshjosh.ratelimiter.store.BandwidthsStore;
import si.mazi.rescu.Interceptor;

public class RateLimiterInterceptor implements Interceptor {

	private final Logger log = LoggerFactory.getLogger(RateLimiterInterceptor.class);

	// Store rate limits for each API method
	private final ConcurrentMap<Method, RateLimiter> limiters = new ConcurrentHashMap<>();

	private final BandwidthsStore<?> bandwidthsStore;

	public RateLimiterInterceptor(BandwidthsStore<?> bandwidthsStore) {
		this.bandwidthsStore = bandwidthsStore;
	}

	@Override
	public Object aroundInvoke(InvocationHandler invocationHandler, Object proxy, Method method, Object[] args)
			throws Throwable {
		acquire(method);
		return invocationHandler.invoke(proxy, method, args);
	}

	private void acquire(Method method) {
		RateLimiter limiter = limiters.computeIfAbsent(method, this::getRateLimiter);

		log.trace("Acquiring permit for {}...", method);
		double timeSpent = limiter.acquire();
		log.trace("Acquired permit for {} in {} seconds.", method, timeSpent);
	}

	private RateLimiter getRateLimiter(Method method) {
		RateLimiterContext<Object> context = RateLimiterContext.builder().classes(method.getDeclaringClass()).store(bandwidthsStore).build();
		return RateLimiterRegistries.of(context).getMethodRateLimiter(method);
	}

}

package org.oxerr.vividseats.client.rescu.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.poshjosh.ratelimiter.RateLimiter;
import io.github.poshjosh.ratelimiter.RateLimiterContext;
import io.github.poshjosh.ratelimiter.RateLimiterRegistries;
import io.github.poshjosh.ratelimiter.store.BandwidthsStore;
import si.mazi.rescu.Interceptor;

public class RateLimiterInterceptor implements Interceptor {

	private final Logger log = LoggerFactory.getLogger(RateLimiterInterceptor.class);

	private final BandwidthsStore<String> store;

	public RateLimiterInterceptor(BandwidthsStore<String> store) {
		this.store = store;
	}

	@Override
	public Object aroundInvoke(InvocationHandler invocationHandler, Object proxy, Method method, Object[] args)
			throws Throwable {
		var limiter = getRateLimiter(method);

		log.trace("Acquiring permit for {}...", method);
		var timeSpent = limiter.acquire();
		log.trace("Acquired permit for {} in {} seconds.", method, timeSpent);

		return invocationHandler.invoke(proxy, method, args);
	}

	public RateLimiter getRateLimiter(Method method) {
		var context = RateLimiterContext.builder().classes(method.getDeclaringClass()).store(store).build();
		var limiter =  RateLimiterRegistries.of(context).getMethodRateLimiter(method);
		var bandwidth = limiter.getBandwidth();
		log.trace("Got bandwidth for {}: {}", method, bandwidth);
		return limiter;
	}

}

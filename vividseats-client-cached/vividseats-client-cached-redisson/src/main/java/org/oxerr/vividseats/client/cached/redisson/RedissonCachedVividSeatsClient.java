package org.oxerr.vividseats.client.cached.redisson;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.oxerr.vividseats.client.VividSeatsClient;
import org.oxerr.vividseats.client.cached.CachedVividSeatsClient;
import org.oxerr.vividseats.client.cached.redisson.inventory.RedissonCachedListingService;
import org.redisson.api.RedissonClient;

public class RedissonCachedVividSeatsClient implements CachedVividSeatsClient {

	private final VividSeatsClient vividSeatsClient;

	private final RedissonCachedListingService cachedSellerListingService;

	public RedissonCachedVividSeatsClient(
		VividSeatsClient vividSeatsClient,
		RedissonClient redissonClient,
		String keyPrefix
	) {
		this(vividSeatsClient, redissonClient, keyPrefix, ForkJoinPool.commonPool());
	}

	public RedissonCachedVividSeatsClient(
		VividSeatsClient vividSeatsClient,
		RedissonClient redissonClient,
		String keyPrefix,
		Executor executor
	) {
		this(
			vividSeatsClient,
			new RedissonCachedListingService(
				vividSeatsClient.getListingService(),
				redissonClient,
				keyPrefix,
				executor
			)
		);
	}

	public RedissonCachedVividSeatsClient(
		VividSeatsClient vividSeatsClient,
		RedissonCachedListingService cachedListingService
	) {
		this.vividSeatsClient = vividSeatsClient;
		this.cachedSellerListingService = cachedListingService;
	}

	@Override
	public VividSeatsClient getClient() {
		return vividSeatsClient;
	}

	@Override
	public RedissonCachedListingService getCachedListingService() {
		return cachedSellerListingService;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RedissonCachedVividSeatsClient)) {
			return false;
		}
		RedissonCachedVividSeatsClient rhs = (RedissonCachedVividSeatsClient) obj;
		return EqualsBuilder.reflectionEquals(this, rhs);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

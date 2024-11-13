package org.oxerr.vividseats.client.cached.redisson.inventory;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.oxerr.ticket.inventory.support.cached.redisson.ListingConfiguration;
import org.oxerr.ticket.inventory.support.cached.redisson.RedissonCachedListingServiceSupport;
import org.oxerr.ticket.inventory.support.cached.redisson.Status;
import org.oxerr.vividseats.client.cached.inventory.VividSeatsCachedListingService;
import org.oxerr.vividseats.client.cached.inventory.VividSeatsEvent;
import org.oxerr.vividseats.client.cached.inventory.VividSeatsListing;
import org.oxerr.vividseats.client.inventory.ListingService;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.redisson.api.RedissonClient;

public class RedissonCachedListingService
	extends RedissonCachedListingServiceSupport<String, String, BrokerListing, VividSeatsListing, VividSeatsEvent, VividSeatsCachedListing>
	implements VividSeatsCachedListingService {

	private final ListingService listingService;

	/**
	 * Constructs with default {@link ListingConfiguration} and default {@link RetryConfiguration}.
	 *
	 * @param listingService the listing service.
	 * @param redissonClient the redisson client.
	 * @param keyPrefix the key prefix for the cache.
	 * @param executor the executor.
	 */
	public RedissonCachedListingService(
		ListingService listingService,
		RedissonClient redissonClient,
		String keyPrefix,
		Executor executor
	) {
		this(listingService, redissonClient, keyPrefix, executor, new ListingConfiguration());
	}

	/**
	 * Constructs with specified {@link ListingConfiguration} and specified {@link RetryConfiguration}.
	 *
	 * @param listingService the listing service.
	 * @param redissonClient the redisson client.
	 * @param keyPrefix the key prefix for the cache.
	 * @param executor the executor.
	 * @param listingConfiguration the listing configuration.
	 */
	public RedissonCachedListingService(
		ListingService listingService,
		RedissonClient redissonClient,
		String keyPrefix,
		Executor executor,
		ListingConfiguration listingConfiguration
	) {
		super(redissonClient, keyPrefix, executor, listingConfiguration);
		this.listingService = listingService;
	}

	@Override
	protected boolean shouldCreate(
		@Nonnull VividSeatsEvent event,
		@Nonnull VividSeatsListing listing,
		@Nullable VividSeatsCachedListing cachedListing
	) {
		boolean shouldCreate = super.shouldCreate(event, listing, cachedListing);
		return shouldCreate || (cachedListing != null && !Objects.equals(listing.getVividSeatsEventId(), cachedListing.getVividSeatsEventId()));
	}

	@Override
	protected boolean shouldUpdate(
		@Nonnull VividSeatsEvent event,
		@Nonnull VividSeatsListing listing,
		@Nullable VividSeatsCachedListing cachedListing
	) {
		boolean shouldUpdate = super.shouldUpdate(event, listing, cachedListing);
		return shouldUpdate || (cachedListing != null && !Objects.equals(listing.getVividSeatsEventId(), cachedListing.getVividSeatsEventId()));
	}

	@Override
	protected int getPriority(
		@Nonnull VividSeatsEvent event,
		@Nullable VividSeatsListing listing,
		@Nullable VividSeatsCachedListing cachedListing
	) {
		if (listing == null || cachedListing == null) {
			return 0;
		}

		int priority = 0;

		var r = listing.getRequest();
		var cr = cachedListing.getRequest();

		priority += Objects.equals(r.getQuantity(), cr.getQuantity()) ? 0 : 1;
		priority += Objects.equals(r.getSection(), cr.getSection()) ? 0 : 1;
		priority += Objects.equals(r.getRow(), cr.getRow()) ? 0 : 1;
		priority += Objects.equals(r.getSeatFrom(), cr.getSeatFrom()) ? 0 : 1;
		priority += Objects.equals(r.getSeatThru(), cr.getSeatThru()) ? 0 : 1;
		priority += Objects.equals(r.getNotes(), cr.getNotes()) ? 0 : 1;

		return priority;
	}

	@Override
	protected boolean shouldDelete(
		@Nonnull VividSeatsEvent event,
		@Nonnull Set<String> inventoryListingIds,
		@Nonnull String listingId,
		@Nonnull VividSeatsCachedListing cachedListing
	) {
		return super.shouldDelete(event, inventoryListingIds, listingId, cachedListing)
			|| !Objects.equals(event.getVividSeatsEventId(), cachedListing.getVividSeatsEventId());
	}

	@Override
	protected void createListing(VividSeatsEvent event, VividSeatsListing listing) throws IOException {
		this.listingService.create(listing.getRequest());
	}

	@Override
	protected void deleteListing(VividSeatsEvent event, String listingId) throws IOException {
		this.listingService.deleteListing(listingId);
	}

	@Override
	protected VividSeatsCachedListing toCached(VividSeatsEvent event, VividSeatsListing listing, Status status) {
		return new VividSeatsCachedListing(new VividSeatsCachedEvent(event), listing, status);
	}

}

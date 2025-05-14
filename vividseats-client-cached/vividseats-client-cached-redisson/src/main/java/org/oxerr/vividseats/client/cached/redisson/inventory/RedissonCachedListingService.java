package org.oxerr.vividseats.client.cached.redisson.inventory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oxerr.ticket.inventory.support.cached.redisson.ListingConfiguration;
import org.oxerr.ticket.inventory.support.cached.redisson.RedissonCachedListingServiceSupport;
import org.oxerr.ticket.inventory.support.cached.redisson.Status;
import org.oxerr.vividseats.client.cached.inventory.CheckOptions;
import org.oxerr.vividseats.client.cached.inventory.VividSeatsCachedListingService;
import org.oxerr.vividseats.client.cached.inventory.VividSeatsEvent;
import org.oxerr.vividseats.client.cached.inventory.VividSeatsListing;
import org.oxerr.vividseats.client.inventory.BrokerListingQuery;
import org.oxerr.vividseats.client.inventory.ListingService;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.redisson.api.RedissonClient;

public class RedissonCachedListingService
	extends RedissonCachedListingServiceSupport<String, String, BrokerListing, VividSeatsListing, VividSeatsEvent, VividSeatsCachedListing>
	implements VividSeatsCachedListingService {

	private final Logger log = LogManager.getLogger();

	private final ListingService listingService;

	/**
	 * Constructs with default {@link ListingConfiguration}.
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
	 * Constructs with specified {@link ListingConfiguration}.
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
		return shouldCreate || (cachedListing != null && !Objects.equals(listing.getMarketplaceEventId(), cachedListing.getEvent().getMarketplaceEventId()));
	}

	@Override
	protected boolean shouldUpdate(
		@Nonnull VividSeatsEvent event,
		@Nonnull VividSeatsListing listing,
		@Nullable VividSeatsCachedListing cachedListing
	) {
		boolean shouldUpdate = super.shouldUpdate(event, listing, cachedListing);
		return shouldUpdate || (cachedListing != null && !Objects.equals(listing.getMarketplaceEventId(), cachedListing.getEvent().getMarketplaceEventId()));
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
			|| !Objects.equals(event.getMarketplaceEventId(), cachedListing.getEvent().getMarketplaceEventId());
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
	protected void deleteListing(VividSeatsEvent event, String listingId, VividSeatsCachedListing cachedListing, int priority) throws IOException {
		this.listingService.deleteListing(listingId);
	}

	@Override
	protected VividSeatsCachedListing toCached(VividSeatsEvent event, VividSeatsListing listing, Status status) {
		return new VividSeatsCachedListing(new VividSeatsCachedEvent(event), listing, status);
	}

	@Override
	public void check() throws UncheckedIOException {
		check(CheckOptions.defaults());
	}

	@Override
	public void check(CheckOptions options) throws UncheckedIOException {
		List<CompletableFuture<Void>> tasks = Collections.synchronizedList(new ArrayList<CompletableFuture<Void>>());

		// Ticket ID to cache name.
		Map<String, String> ticketIdToCacheName = this.getCacheNamesStream(options.chunkSize())
			.flatMap(cacheName ->
				this.getCache(cacheName).keySet().stream()
					.map(id -> Map.entry(id, cacheName))
			)
			.collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));

		// All listings on the marketplace.
		List<BrokerListing> listings = this.listingService.get(new BrokerListingQuery());

		log.debug("[check] listings size: {}", listings::size);

		// Delete listings which not in cache.
		var deleteTasks = listings.stream()
			.filter(listing -> listing.getTicketId() == null || !ticketIdToCacheName.containsKey(listing.getTicketId()))
			.map(listing -> this.<Void>callAsync(() -> {
				this.listingService.deleteListing(listing.getTicketId());
				return null;
			})).collect(Collectors.toUnmodifiableList());

		tasks.addAll(deleteTasks);

		// Create or update listings in cache.
		listings.stream()
			.filter(listing -> ticketIdToCacheName.containsKey(listing.getTicketId()))
			.forEach(listing -> {
				String cacheName = ticketIdToCacheName.get(listing.getTicketId());
				VividSeatsCachedListing cachedListing = this.getCache(cacheName).get(listing.getTicketId());
				if (cachedListing == null) {
					// Double check the listing if it is not in cache.
					// If the listing is not in cache, delete the listing from marketplace.
					CompletableFuture<Void> task = this.callAsync(() -> {
						this.listingService.deleteListing(listing.getTicketId());
						return null;
					});
					tasks.add(task);
				} else if (!isSame(listing, cachedListing.getRequest())) {
					CompletableFuture<Void> task = this.callAsync(() -> {
						var e = cachedListing.getEvent().toVividSeatsEvent();
						var l = cachedListing.toVividSeatsListing();
						var p = getPriority(e, l, cachedListing);
						this.updateListing(e, l, (VividSeatsListing) null, p);
						return null;
					});
					tasks.add(task);
				}
			});

		// Create listings which in cache but not on the marketplace.
		Set<String> existingTicketIds = listings.stream()
			.map(BrokerListing::getTicketId)
			.collect(Collectors.toSet());

		List<CompletableFuture<Void>> createTasks = ticketIdToCacheName.entrySet().stream()
			.filter(entry -> !existingTicketIds.contains(entry.getKey()))
			.map(entry -> this.<Void>callAsync(() -> {
				String cacheName = entry.getValue();
				VividSeatsCachedListing cachedListing = this.getCache(cacheName).get(entry.getKey());

				if (cachedListing != null) {
					// Double check if the cached listing still exists.
					VividSeatsEvent e = cachedListing.getEvent().toVividSeatsEvent();
					VividSeatsListing l = cachedListing.toVividSeatsListing();
					this.createListing(e, l);
				}

				return null;
			})).collect(Collectors.toUnmodifiableList());

		tasks.addAll(createTasks);

		// Wait all tasks to complete.
		log.debug("[check] checking size: {}", tasks::size);
		CompletableFuture.allOf(tasks.toArray(CompletableFuture[]::new)).join();
	}

	private boolean isSame(BrokerListing listing, BrokerListing request) {
		boolean equals = Listings.isSame(listing, request);
		log.debug("[isSame] listing: {}, request: {}, equals: {}",
			() -> Listings.toString(listing), () -> Listings.toString(request), () -> equals);
		return equals;
	}

}

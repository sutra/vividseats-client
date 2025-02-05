package org.oxerr.vividseats.client.rescu.impl.inventory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import org.oxerr.vividseats.client.inventory.ListingService;
import org.oxerr.vividseats.client.model.inventory.BrokerListing;
import org.oxerr.vividseats.client.model.v1.inventory.Update;
import org.oxerr.vividseats.client.rescu.resource.VividSeatsException;
import org.oxerr.vividseats.client.rescu.resource.inventory.ListingResource;

public class ListingServiceImpl implements ListingService {

	private final Supplier<String> tokenSupplier;

	private final org.oxerr.vividseats.client.rescu.resource.v1.inventory.ListingResource listingResourceV1;

	private final ListingResource listingResource;

	public ListingServiceImpl(
		Supplier<String> tokenSupplier,
		org.oxerr.vividseats.client.rescu.resource.v1.inventory.ListingResource listingResourceV1,
		ListingResource listingResource
	) {
		this.tokenSupplier = tokenSupplier;
		this.listingResourceV1 = listingResourceV1;
		this.listingResource = listingResource;
	}

	@Override
	public List<BrokerListing> get(
		Long listingId,
		String internalTicketId,
		Integer productionId,
		String fromEventDate,
		String toEventDate,
		Integer headlinerId,
		Boolean includeFiles
	) throws IOException {
		return listingResource.get(fromEventDate, toEventDate, listingId, internalTicketId, productionId, headlinerId, includeFiles).getListings();
	}

	@Override
	public BrokerListing create(BrokerListing brokerListing) throws IOException {
		return listingResource.create(brokerListing).getListing();
	}

	@Override
	public void updateListing(Update update) throws IOException {
		listingResourceV1.updateListing(
			this.tokenSupplier.get(),
			update.getTicketId(),
			update.getQuantity(),
			update.getSection(),
			update.getRow(),
			update.getSeatFrom(),
			update.getSeatThru(),
			update.getNotes(),
			update.getPrice(),
			update.getElectronic(),
			update.getInHandDate(),
			update.getSplitType(),
			update.getSplitValue(),
			update.getBarcode(),
			update.getFaceValue(),
			update.getUnitTaxedCost()
		);
	}

	@Override
	public void update(BrokerListing brokerListing) throws IOException {
		listingResource.update(brokerListing);
	}

	@Override
	public void deleteListing(String ticketId) throws IOException {
		try {
			this.listingResourceV1.deleteListing(this.tokenSupplier.get(), ticketId);
		} catch (VividSeatsException e) {
			if (Objects.equals(e.getMessage(), "Listing not found.")) {
				// Listing not found.
			} else {
				throw e;
			}
		}
	}

	@Override
	public void delete(Long listingId, String internalTicketId) throws IOException {
		listingResource.delete(listingId, internalTicketId);
	}

}

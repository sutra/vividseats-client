package org.oxerr.vividseats.client.rescu.resource.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.oxerr.vividseats.client.model.inventory.SplitType;
import org.oxerr.vividseats.client.model.inventory.StockType;

import com.fasterxml.jackson.databind.ObjectMapper;

class ListingsResponseTest {

	@Test
	void test() throws IOException {
		ObjectMapper objectMapper = ObjectMappers.objectMapper();

		var listingsResponse = objectMapper.readValue(this.getClass().getResource("ListingsResponse-response.json"), ListingsResponse.class);
		assertNotNull(listingsResponse);
		var listing = listingsResponse.getListings().get(0);
		assertEquals(-9007199254740991L, listing.getId());
		assertEquals(-2147483648, listing.getProductionId());
		assertEquals(-2147483648, listing.getQuantity());
		assertEquals("string", listing.getSection());
		assertEquals("string", listing.getRow());
		assertEquals("string", listing.getSeatFrom());
		assertEquals("string", listing.getSeatThru());
		assertEquals("string", listing.getNotes());
		assertEquals(BigDecimal.ZERO, listing.getPrice());
		assertEquals("string", listing.getTicketId());
		assertTrue(listing.getElectronic());
		assertTrue(listing.getElectronicTransfer());
		assertEquals("2019-08-24T14:15:22", listing.getInHandDate().toString());
		assertEquals("2019-08-24T14:15:22", listing.getListDate().toString());
		assertEquals(SplitType.DEFAULT, listing.getSplitType());
		assertEquals("string", listing.getSplitValue());
		assertTrue(listing.getSpec());
		assertTrue(listing.getInstantDownload());
		assertEquals("string", listing.getPassThrough());
		assertEquals(StockType.HARD, listing.getStockType());
		assertEquals(-2147483648, listing.getSeatNumbers().get(0));
		assertEquals(BigDecimal.ZERO, listing.getFaceValue());
		assertEquals(BigDecimal.ZERO, listing.getUnitTaxedCost());
		assertTrue(listing.getInstantTransfer());
		assertEquals("ADA", listing.getAttributes().get(0));
		assertEquals(-2147483648, listing.getTickets().get(0).getId());
		assertEquals(-2147483648, listing.getTickets().get(0).getSeatNumber());
		assertEquals("string", listing.getTickets().get(0).getBarCode());
		assertEquals("string", listing.getTickets().get(0).getTicketToken());
		assertEquals(-9007199254740991L, listing.getTickets().get(0).getListingId());
		assertEquals("string", listing.getTickets().get(0).getFileData());
		assertEquals("string", listing.getInternalNotes());
		assertEquals("string", listing.getEventName());
		assertEquals("string", listing.getVenue());
		assertEquals("string", listing.getCity());
		assertEquals("string", listing.getState());
		assertEquals("2019-08-24T14:15:22", listing.getEventDate().toString());
		assertEquals("string", listing.getShipDate());
		assertEquals(BigDecimal.ZERO, listing.getCost());
		assertTrue(listing.getHasFiles());
		assertTrue(listing.getHasBarcodes());
		assertEquals("2019-08-24T14:15:22", listing.getLastUpdate().toString());
		assertEquals("ADA", listingsResponse.getAttribute());
	}

}

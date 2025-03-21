package org.oxerr.vividseats.client.cxf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.oxerr.vividseats.client.cxf.model.BrokerListing;
import org.oxerr.vividseats.client.cxf.model.BrokerListings;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

class BrokerListingsTest {

	@Test
	void test() throws IOException, JAXBException {
		try (InputStream inputStream = getClass().getResourceAsStream("getListings.xml")) {
			JAXBContext jaxbContext = JAXBContext.newInstance(BrokerListings.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			BrokerListings result = (BrokerListings) unmarshaller.unmarshal(inputStream);

			assertEquals(1, result.getBrokerListings().size());

			BrokerListing brokerListing = result.getBrokerListings().get(0);
			assertEquals(11875106179L, brokerListing.getId().longValue());
		}
	}

}

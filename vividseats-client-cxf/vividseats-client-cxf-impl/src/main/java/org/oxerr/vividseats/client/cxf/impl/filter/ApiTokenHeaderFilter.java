package org.oxerr.vividseats.client.cxf.impl.filter;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

public class ApiTokenHeaderFilter implements ClientRequestFilter {

	private final String apiToken;

	public ApiTokenHeaderFilter(String apiToken) {
		this.apiToken = apiToken;
	}

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		requestContext.getHeaders().add("Api-token", apiToken);
	}

}

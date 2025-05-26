/**
 * Provides JAX-RS client request filters for the VividSeats client.
 * <p>
 * This package contains implementations of {@link jakarta.ws.rs.client.ClientRequestFilter}
 * that enhance HTTP client behavior, such as rate limiting based on annotations,
 * logging, request modification, etc.
 * </p>
 *
 * <p>
 * The filters here are typically registered on JAX-RS client proxies to intercept
 * outgoing requests and apply cross-cutting concerns.
 * </p>
 */
package org.oxerr.vividseats.client.cxf.impl.filter;

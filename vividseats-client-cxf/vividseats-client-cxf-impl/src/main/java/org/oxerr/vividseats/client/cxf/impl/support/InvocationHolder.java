package org.oxerr.vividseats.client.cxf.impl.support;

import java.lang.reflect.Method;

/**
 * Holds a {@link Invocation} in a {@link ThreadLocal} for tracking method calls and arguments.
 */
public final class InvocationHolder {

	private static final ThreadLocal<Invocation> holder = new ThreadLocal<>();

	private InvocationHolder() {
		throw new UnsupportedOperationException("Utility class");
	}

	/**
	 * Stores the current method invocation context.
	 *
	 * @param method the invoked method
	 * @param args the method arguments
	 */
	public static void set(Method method, Object[] args) {
		holder.set(new Invocation(method, args));
	}

	/**
	 * Gets the current method invocation.
	 *
	 * @return the invocation or {@code null} if none is set
	 */
	public static Invocation get() {
		return holder.get();
	}

	/**
	 * Clears the invocation from the current thread.
	 */
	public static void clear() {
		holder.remove();
	}
}

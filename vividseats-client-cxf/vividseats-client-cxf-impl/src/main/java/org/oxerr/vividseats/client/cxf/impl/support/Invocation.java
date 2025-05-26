package org.oxerr.vividseats.client.cxf.impl.support;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Encapsulates a method invocation with its arguments.
 */
public final class Invocation {

	private final Method method;
	private final Object[] args;

	/**
	 * Creates a new {@code Invocation}.
	 *
	 * @param method the invoked method
	 * @param args the method arguments
	 */
	public Invocation(Method method, Object[] args) {
		this.method = method;
		this.args = args != null ? args.clone() : null;
	}

	/**
	 * Returns the method being invoked.
	 *
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * Returns a copy of the arguments passed to the method.
	 *
	 * @return the arguments
	 */
	public Object[] getArgs() {
		return args != null ? args.clone() : null;
	}

	@Override
	public String toString() {
		return "Invocation{method=" + method + ", args=" + Arrays.toString(args) + '}';
	}
}

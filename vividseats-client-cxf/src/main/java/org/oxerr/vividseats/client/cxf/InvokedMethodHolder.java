package org.oxerr.vividseats.client.cxf;

import java.lang.reflect.Method;

public final class InvokedMethodHolder {

	private static final ThreadLocal<Method> methodHolder = new ThreadLocal<>();

	private InvokedMethodHolder() {
		throw new UnsupportedOperationException();
	}

	public static void set(Method method) {
		methodHolder.set(method);
	}

	public static Method get() {
		return methodHolder.get();
	}

	public static void clear() {
		methodHolder.remove();
	}

}

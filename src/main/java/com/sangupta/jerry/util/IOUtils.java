package com.sangupta.jerry.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * 
 * @author sangupta
 * @since 3.1.0
 */
public abstract class IOUtils {

	public static void closeQuietly(Closeable... closeables) {
		if (AssertUtils.isEmpty(closeables)) {
			return;
		}

		for (Closeable closeable : closeables) {
			closeQuietly(closeable);
		}
	}

	public static void closeQuietly(Closeable closeable) {
		if (closeable == null) {
			return;
		}
		try {
			closeable.close();
		} catch (final IOException ioe) {
			// ignore
		}
	}

}

/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
 *
 * https://sangupta.com/projects/jerry-core
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.sangupta.jerry.refresh;

/**
 * A value that will auto-refresh when certain time expires
 * using the <code>refresh</code> method provided.
 *
 * The expiration is checked only at access time, when a call
 * to <code>get()</code> method is made.
 *
 * Usage is simple as:
 *
 * <pre>
 * // 60 seconds
 * AutoRefreshableDouble config = new AutoRefreshableDouble(60000);
 *
 * // calls the refresh method to fetch the value, say at 10:00:00
 * double value = config.get();
 *
 * // any call before 10:01:00 will return the same value
 * value = config.get();
 *
 * // a call on or after 10:01:00 will call the refresh method again
 * // and then return the value
 * value = config.get();
 * </pre>
 *
 * @author sangupta
 *
 * @since 2.3
 */
public abstract class AutoRefreshableDouble {

	/**
	 * The value being cached
	 */
	protected double value;

	/**
	 * The time for which the value must be cached
	 */
	protected final long cacheMillis;

	/**
	 * The time when the value was last refreshed
	 */
	protected volatile long lastRefreshed = 0;

	/**
	 * Constructor
	 *
	 * @param cacheMillis
	 *            the milliseconds for which to cache the value
	 */
	public AutoRefreshableDouble(long cacheMillis) {
		if(cacheMillis <= 0) {
			throw new IllegalArgumentException("Cache time in millis should be greater than zero");
		}

		this.cacheMillis = cacheMillis;
	}

	/**
	 * Read the value that is held within
	 *
	 * @return the value that is stored internally
	 */
	public double get() {
		long delta = System.currentTimeMillis() - this.lastRefreshed;
		if(delta > this.cacheMillis) {
			this.value = refresh();
			this.lastRefreshed = System.currentTimeMillis();
		}

		return this.value;
	}

	/**
	 * Refresh the value to a new value. The method is public so that the owners
	 * can call this at will to refresh the value before expiration of cache
	 * time. However, in practical conditions that shall be rare.
	 *
	 * @return the newer refreshed value
	 */
	public abstract double refresh();

}

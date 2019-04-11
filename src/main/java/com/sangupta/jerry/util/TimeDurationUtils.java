/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
 *
 * http://sangupta.com/projects/jerry-core
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


package com.sangupta.jerry.util;

import java.util.Date;

/**
 * Helper methods to compute time duration between a given date and
 * current time, or duration between two given dates.
 *
 * @author sangupta
 *
 */
public abstract class TimeDurationUtils {

	/**
	 * Compute the time duration of the given date from current time.
	 *
	 * @param date
	 *            the date before current time
	 *
	 * @return the duration statement from currnet time
	 *
	 * @throws IllegalArgumentException
	 *             if the given date is <code>null</code>
	 */
	public static String ago(final Date date) {
		return ago(date, System.currentTimeMillis(), false);
	}

	/**
	 * Compute the time duration between two given dates.
	 *
	 * @param before
	 *            the older {@link Date}
	 *
	 * @param after
	 *            the recent {@link Date}
	 *
	 * @param condensed
	 *            whether to display in the condensed format or not
	 *
	 * @return the string representation of time duration between the two dates
	 *
	 */
	public static String ago(final Date before, final Date after, final boolean condensed) {
		if(before == null) {
			throw new IllegalArgumentException("Date item cannot be null");
		}

		if(after == null) {
			throw new IllegalArgumentException("Date item cannot be null");
		}

		long delta = after.getTime() - before.getTime();
		if(!condensed) {
			return fromDelta(delta);
		}

		return fromDeltaCondensed(delta);
	}

	/**
	 * Compute the time duration between the date and time in millis
	 *
	 * @param before
	 *            the older {@link Date}
	 *
	 * @param after
	 *            the current time in millis
	 *
	 * @param condensed
	 *            whether to display in the condensed format or not
	 *
	 * @return the string representation of time duration between the two dates
	 */
	public static String ago(final Date before, final long after, final boolean condensed) {
		if(before == null) {
			throw new IllegalArgumentException("Date item cannot be null");
		}

		long delta = after - before.getTime();
		if(!condensed) {
			return fromDelta(delta);
		}

		return fromDeltaCondensed(delta);

	}

	/**
	 * Compute the time duration from given millis.
	 *
	 * @param millis the duration in millis
	 *
	 * @return the string representation of time duration
	 */
	public static String ago(long millis) {
		return ago(millis, false);
	}

	/**
	 * Compute the time duration from the given date.
	 *
	 * @param date
	 *            the date from which to compute the delta
	 *
	 * @param condensed
	 *            whether to display in the condensed format or not
	 *
	 * @return the string representation of time duration
	 */
	public static String ago(final Date date, final boolean condensed) {
		return ago(date.getTime(), condensed);
	}

	/**
	 * Compute the time duration from given millis.
	 *
	 * @param millis
	 *            the duration in millis
	 *
	 * @param condensed
	 *            whether to display in the condensed format or not
	 *
	 * @return the string representation of time duration
	 */
	public static String ago(final long millis, final boolean condensed) {
		long delta = System.currentTimeMillis() - millis;
		if(!condensed) {
			return fromDelta(delta);
		}

		return fromDeltaCondensed(delta);
	}

	/**
	 * Compute the time duration string for the given delta in condensed format.
	 *
	 * @param delta
	 *            the duration in millis
	 *
	 * @return the string representation of time duration
	 */
	private static String fromDeltaCondensed(long delta) {
		if(delta < 0) {
			return "now";
		}

		if(delta < DateUtils.ONE_MINUTE) {
			return "now";
		}

		if(delta < DateUtils.ONE_HOUR) {
			int minutes = (int) (delta / DateUtils.ONE_MINUTE);
			if(minutes == 1) {
				return "1min";
			}

			return "" + minutes + "min";
		}

		if(delta < DateUtils.ONE_DAY) {
			int hours = (int) (delta / DateUtils.ONE_HOUR);
			if(hours == 1) {
				return "1h";
			}

			return "" + hours + "h";
		}

		if(delta < DateUtils.ONE_MONTH) {
			int days = (int) (delta / DateUtils.ONE_DAY);
			if(days == 1) {
				return "1d";
			}

			return "" + days + "d";
		}

		if(delta < DateUtils.ONE_YEAR) {
			int months = (int) (delta / DateUtils.ONE_MONTH);
			if(months == 1) {
				return "1mo";
			}

			return "" + months + "mo";
		}

		int years = (int) (delta / DateUtils.ONE_YEAR);
		if(years == 1) {
			return "1y";
		}

		return "" + years + "y";
	}

	/**
	 * Compute the time duration string for the given delta in normal format.
	 *
	 * @param delta
	 *            the duration in millis
	 *
	 * @return the string representation of time duration
	 */
	private static String fromDelta(long delta) {
		if(delta < 0) {
			return "moments ago";
		}

		if(delta < DateUtils.ONE_MINUTE) {
			return "less than a minute ago";
		}

		if(delta < DateUtils.ONE_HOUR) {
			int minutes = (int) (delta / DateUtils.ONE_MINUTE);
			if(minutes == 1) {
				return "about a minute ago";
			}

			return "about " + minutes + " minutes ago";
		}

		if(delta < DateUtils.ONE_DAY) {
			int hours = (int) (delta / DateUtils.ONE_HOUR);
			if(hours == 1) {
				return "about an hour ago";
			}

			return "about " + hours + " hours ago";
		}

		if(delta < DateUtils.ONE_MONTH) {
			int days = (int) (delta / DateUtils.ONE_DAY);
			if(days == 1) {
				return "about a day ago";
			}

			return "about " + days + " days ago";
		}

		if(delta < DateUtils.ONE_YEAR) {
			int months = (int) (delta / DateUtils.ONE_MONTH);
			if(months == 1) {
				return "about a month ago";
			}

			return "about " + months + " months ago";
		}

		int years = (int) (delta / DateUtils.ONE_YEAR);
		if(years == 1) {
			return "about an year ago";
		}

		return "about " + years + " years ago";
	}

}

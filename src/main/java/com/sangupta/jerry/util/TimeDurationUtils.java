/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012, Sandeep Gupta
 * 
 * http://www.sangupta/projects/jerry
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
 * @author sangupta
 *
 */
public class TimeDurationUtils {
	
	private static final long MINUTE = 60 * 1000;
	
	private static final long HOUR = 60 * MINUTE;
	
	private static final long DAY = 24 * HOUR;
	
	private static final long MONTH = 30 * DAY;
	
	private static final long YEAR = 365 * DAY;
	
	/**
	 * Compute the time duration from given date.
	 * 
	 * @param date
	 * @return
	 */
	public static String ago(final Date date) {
		return ago(date, false);
	}
	
	public static String ago(final Date date, final boolean condensed) {
		if(date == null) {
			throw new IllegalArgumentException("Date item cannot be null");
		}
		
		long delta = System.currentTimeMillis() - date.getTime();
		if(!condensed) {
			return fromDelta(delta);
		}
		
		return fromDeltaCondensed(delta);
	}
	
	/**
	 * Compute the time duration from given millis.
	 * 
	 * @param millis
	 * @return
	 */
	public static String ago(long millis) {
		return ago(millis, false);
	}
	
	/**
	 * 
	 * @param millis
	 * @param condensed
	 * @return
	 */
	public static String ago(final long millis, final boolean condensed) {
		long delta = System.currentTimeMillis() - millis;
		if(!condensed) {
			return fromDelta(delta);
		}
		
		return fromDeltaCondensed(delta);
	}
	
	/**
	 * @param delta
	 * @return
	 */
	private static String fromDeltaCondensed(long delta) {
		if(delta < 0) {
			return "now";
		}
		
		if(delta < MINUTE) {
			return "now";
		}
		
		if(delta < HOUR) {
			int minutes = (int) (delta / MINUTE);
			if(minutes == 1) {
				return "1min";
			}
			
			return "" + minutes + "min";
		}
		
		if(delta < DAY) {
			int hours = (int) (delta / HOUR);
			if(hours == 1) {
				return "1h";
			}
			
			return "" + hours + "h";
		}
		
		if(delta < MONTH) {
			int days = (int) (delta / DAY);
			if(days == 1) {
				return "1d";
			}
			
			return "" + days + "d";
		}
		
		if(delta < YEAR) {
			int months = (int) (delta / MONTH);
			if(months == 1) {
				return "1mo";
			}
			
			return "" + months + "mo";
		}
		
		int years = (int) (delta / YEAR);
		if(years == 1) {
			return "1y";
		}
		
		return "" + years + "y";
	}

	/**
	 * 
	 * @param delta
	 * @return
	 */
	private static String fromDelta(long delta) {
		if(delta < 0) {
			return "moments ago";
		}
		
		if(delta < MINUTE) {
			return "less than a minute ago";
		}
		
		if(delta < HOUR) {
			int minutes = (int) (delta / MINUTE);
			if(minutes == 1) {
				return "about a minute ago";
			}
			
			return "about " + minutes + " minutes ago";
		}
		
		if(delta < DAY) {
			int hours = (int) (delta / HOUR);
			if(hours == 1) {
				return "about an hour ago";
			}
			
			return "about " + hours + " hours ago";
		}
		
		if(delta < MONTH) {
			int days = (int) (delta / DAY);
			if(days == 1) {
				return "about a day ago";
			}
			
			return "about " + days + " days ago";
		}
		
		if(delta < YEAR) {
			int months = (int) (delta / MONTH);
			if(months == 1) {
				return "about a month ago";
			}
			
			return "about " + months + " months ago";
		}
		
		int years = (int) (delta / YEAR);
		if(years == 1) {
			return "about an year ago";
		}
		
		return "about " + years + " years ago";
	}

}

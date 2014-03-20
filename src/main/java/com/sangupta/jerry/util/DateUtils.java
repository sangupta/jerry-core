/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2014, Sandeep Gupta
 * 
 * http://sangupta.com/projects/jerry
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
public class DateUtils {
	
	/**
	 * One second expressed as millis
	 */
	public static final long ONE_SECOND = 1000l;
	
	/**
	 * Fifteen seconds expressed as millis
	 */
	public static final long FIFTEEN_SECONDS = 15l * ONE_SECOND;
	
	/**
	 * One minute expressed as millis
	 */
	public static final long ONE_MINUTE = 60l * ONE_SECOND;
	
	/**
	 * Five minutes expressed as millis
	 */
	public static final long FIVE_MINUTES = 5l * ONE_MINUTE;

	/**
	 * Fifteen minutes expressed in millis
	 */
	public static final long FIFTEEN_MINUTES = 15l * ONE_MINUTE;
	
	/**
	 * One hour expressed as millis
	 */
	public static final long ONE_HOUR = 60l * ONE_MINUTE;
	
	/**
	 * 6 hours expressed as millis
	 */
	public static final long SIX_HOURS = 6l * ONE_HOUR;
	
	/**
	 * 12 hours expressed as millis
	 */
	public static final long TWELVE_HOURS = 12l * ONE_HOUR;
	
	/**
	 * One day (24-hours) expressed as millis
	 */
	public static final long ONE_DAY = 24l * ONE_HOUR;
	
	/**
	 * One week (7 days) expressed as millis
	 */
	public static final long ONE_WEEK = 7l * ONE_DAY;
	
	/**
	 * One month (30-days) expressed as millis
	 */
	public static final long ONE_MONTH = 30l * ONE_DAY; 
	
	/**
	 * One year (365-days) expressed as millis
	 */
	public static final long ONE_YEAR = 365l * ONE_DAY;

    /**
	 * Convert the given time (in millis) represented as a {@link Long} object
	 * into the {@link Date} object.
	 * 
	 * @param millis
	 *            the millis to be converted
	 * 
	 * @return the {@link Date} object representation, or <code>null</code> if
	 *         the incoming millis are <code>null</code>
	 */
    public static final Date getDate(Long millis) {
        if(millis == null) {
            return null;
        }
        
        return new Date(millis);
    }

    /**
	 * Find the difference between two {@link Date} objects in milliseconds. The
	 * method is <code>null</code>-safe. If either of the object is
	 * <code>null</code>, the total time of the other object is returned. If
	 * both the objects are <code>null</code>, a difference of <code>0</code> is
	 * returned. The value is always positive.
	 * 
	 * @param first
	 *            the first {@link Date} object
	 * 
	 * @param second
	 *            the second {@link Date} object
	 * 
	 * @return the difference in milli-seconds.
	 */
    public static final long getDifference(Date first, Date second) {
    	if(first == null && second == null) {
    		return 0l;
    	}
    	
    	if(first == null) {
    		return second.getTime();
    	}
    	
    	if(second == null) {
    		return first.getTime();
    	}
    	
    	long difference = first.getTime() - second.getTime();
    	if(difference < 0) {
    		return 0 - difference;
    	}
    	
    	return difference;
    }
}

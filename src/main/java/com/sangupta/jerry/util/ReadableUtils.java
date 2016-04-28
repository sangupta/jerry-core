/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2016, Sandeep Gupta
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

import java.util.Arrays;

/**
 * Utility class that provides methods to work between commonly
 * used values and their human readable equivalents, like date,
 * time duration, file size etc.
 * 
 * @author sangupta
 *
 */
public abstract class ReadableUtils {
    
	/**
	 * Convert human readable file size to number of bytes. For example: 2k
	 * would convert to 2048 etc.
	 * 
	 * @param size
	 *            the size as {@link String}
	 * 
	 * @return the number of bytes represented
	 */
	public static long parseByteCount(String size) {
		if(AssertUtils.isEmpty(size)) {
			return 0;
		}
		
		char[] chars = size.toCharArray();
		boolean isNumber = true;
		int i = 0;
		for(i = 0; i < chars.length; i++) {
			char c = chars[i];
			if(!('0' <= c && c <= '9')) {
				isNumber = false;
				break;
			}
		}
		
		if(isNumber) {
			return StringUtils.getLongValue(size, 0);
		}
		
		// not a number
		String num = new String(Arrays.copyOfRange(chars, 0, i));
		String id = new String(Arrays.copyOfRange(chars, i, chars.length));
		long value = StringUtils.getLongValue(num, 0);
		
		// check id
		id = id.trim();
		if("b".equalsIgnoreCase(id)) {
			return value;
		}

		if("k".equalsIgnoreCase(id) || "kb".equalsIgnoreCase(id)) {
			return value * FileUtils.ONE_KB;
		}

		if("m".equalsIgnoreCase(id) || "mb".equalsIgnoreCase(id)) {
			return value * FileUtils.ONE_MB;
		}
		
		if("g".equalsIgnoreCase(id) || "gb".equalsIgnoreCase(id)) {
			return value * FileUtils.ONE_GB;
		}

		if("t".equalsIgnoreCase(id) || "tb".equalsIgnoreCase(id)) {
			return value * FileUtils.ONE_TB;
		}
		
		throw new IllegalArgumentException("Unknown size identifier: " + id);
	}

	/**
	 * Convert the given number of bytes to human-readable {@link String}
	 * format. For example 2048 will be said as 2K.
	 * 
	 * @param bytes
	 *            the number of bytes
	 * 
	 * @return size represented as {@link String} instance
	 */
	public static String getReadableByteCount(long bytes) {
	    if (bytes < FileUtils.ONE_KB) {
	    	return bytes + " B";
	    }
	    
	    int exp = (int) (Math.log(bytes) / Math.log(FileUtils.ONE_KB));
	    String pre = "" + "KMGTPE".charAt(exp - 1);
	    double value = bytes / Math.pow(FileUtils.ONE_KB, exp);
	    if(((value * 10) % 10) == 0) {
	    	return String.format("%.0f %sB", value, pre);
	    }
	    
	    return String.format("%.1f %sB", value, pre);
	}
	
	/**
	 * Convert the given time duration into a human-readable string format.
	 * 
	 * @param millis
	 *            the duration in millseconds
	 * 
	 * @return the human readable time
	 */
	public static String getReadableTimeDuration(long millis) {
		long seconds = millis / 1000l;
		long minutes = seconds / 60l;
		long hours = minutes / 60l;
		long days = hours / 24l;
		
		hours = hours % 24l;
		minutes = minutes % 60l;
		seconds = seconds % 60l;
		millis = millis % 1000l;
		
		String s = "";
		if(days > 0) {
			s += days + getPluralIfNeeded(days, " day ", " days ");
		}
		if(hours > 0) {
			s +=  hours + getPluralIfNeeded(hours, " hour ", " hours ");
		}
		if(minutes > 0) {
			 s += minutes + getPluralIfNeeded(minutes, " minute ", " minutes ");
		}
		if(seconds > 0) {
			s += seconds + getPluralIfNeeded(seconds, " second ", " seconds ");
		}
		if(millis > 0) {
			s += millis + getPluralIfNeeded(millis, " milli", " millis");
		}
		
		return s.trim();
	}
	
	/**
	 * Return the singular string if the value is ONE, or the plural one.
	 * 
	 * @param value
	 *            the value to test for
	 * 
	 * @param singular
	 *            the representation when value is ONE
	 * 
	 * @param plural
	 *            the representation when value is MORE than ONE
	 * 
	 * @return the {@link String} representations
	 * 
	 */
	public static String getPluralIfNeeded(long value, String singular, String plural) {
		if(value == 1) {
			return singular;
		}
		
		return plural;
	}
}
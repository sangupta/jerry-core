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

import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility functions around {@link String} objects.
 * 
 * @author sangupta
 *
 */
public class StringUtils {
	
	/**
	 * Logger instance
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);
	
	/**
	 * UTF-8 charset object
	 */
	public static final Charset CHARSET_UTF8 = Charset.forName("UTF8");
	
	/**
	 * An empty string object containing nothing.
	 */
	public static final String EMPTY_STRING = "";
	
	/**
	 * A blank string containing one blank white-space character.
	 */
	public static final String BLANK_STRING = " ";

	/**
	 * Defines the platform dependent line encoding
	 */
	public static final String SYSTEM_NEW_LINE = System.getProperty("line.separator");
	
	/**
	 * Function to give a HEX representation of the byte array.
	 * 
	 * @param bytes
	 *            the source byte array
	 *            
	 * @return the HEX coded string representing the byte array
	 */
	public static String getHex(byte bytes[]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; ++i) {
			sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}
	
	/**
	 * Function to return a HEX representation of the given byte.
	 * 
	 * @param b
	 *            the byte value
	 * 
	 * @return the hex representation
	 * 
	 */
	public static String getHex(byte b) {
		return Integer.toHexString(b & 0xFF);
	}
	
	/**
	 * Return true if the string is equal to "true" or "yes" otherwise return
	 * false.
	 * 
	 * @param boolString
	 *            string whose content is checked for true or false
	 * 
	 * @return boolean flag indicating whether the input was true or false
	 */
	public static boolean getBoolean(String boolString) {
	    return getBoolean(boolString, false); 
 	}

	/**
	 * Parse the string and return the boolean value for the same. Text such as
	 * <code>yes</code> and <code>true</code> is treated as {@link Boolean#TRUE}
	 * . If the string parsing fails, the default value is returned.
	 * 
	 * @param boolString
	 *            the string to be parsed
	 * 
	 * @param defaultValue
	 *            the value to be returned, if the string does not match
	 * 
	 * @return the boolean value as read by parsing the string
	 */
	public static boolean getBoolean(String boolString, boolean defaultValue) {
		if (AssertUtils.isNotEmpty(boolString)) {
	        boolString = boolString.toLowerCase();
	        if ("yes".equals(boolString) || "true".equals(boolString)) {
	            return true;
	        }
	        
	        return false;
	    }
		
	    return defaultValue; 
 	}
	
	/**
	 * Parse and return the int value of the given string. If the string cannot
	 * be parsed, returns the default value provided
	 * 
	 * @param string
	 *            the string to be parsed
	 * 
	 * @param defaultValue
	 *            the value to be returned if parsing string fails
	 * 
	 * @return the 32-bit value of the string
	 */
	public static int getIntValue(String string, int defaultValue) {
		try {
			if(AssertUtils.isNotEmpty(string)) {
				return Integer.parseInt(string);
			}
		} catch(NumberFormatException e) {
			LOGGER.debug("error getting integer from string: " + string, e);
		}
		
		return defaultValue;
	}
	
	/**
	 * Parse and return the long value of the given string. If the string cannot
	 * be parsed, returns the default value provided
	 * 
	 * @param string
	 *            the string to be parsed
	 * 
	 * @param defaultValue
	 *            the value to be returned if parsing string fails
	 * 
	 * @return the 64-bit value of the string
	 */
	public static long getLongValue(String string, long defaultValue) {
		try {
			if(AssertUtils.isNotEmpty(string)) {
				return Long.parseLong(string);
			}
		} catch(NumberFormatException e) {
			LOGGER.debug("error getting long from string: " + string, e);
		}
		
		return defaultValue;
	}
	
	/**
	 * Generate a given appender delimited string of all items in the list. If
	 * the list has no elements, an empty string is returned back.
	 * 
	 * @param list
	 *            the list containing objects to be joined
	 * 
	 * @param appender
	 *            the appender to be used
	 * 
	 * @return the string representation of list using appender as the delimiter
	 */
	public String fromList(List<Object> list, String appender) {
		if(AssertUtils.isEmpty(list)) {
			return EMPTY_STRING;
		}
		
		StringBuilder builder = new StringBuilder();
		for(int index = 0; index < list.size(); index++) {
			if(index > 0) {
				builder.append(appender);
			}
			builder.append(list.get(index));
		}
		
		return builder.toString();
	}
	
	/**
	 * Check if a value is contained in the list.
	 * 
	 * @param list
	 *            the list of values
	 * 
	 * @param value
	 *            the value that needs to be checked for presence in list
	 * 
	 * @return <code>true</code> if the value exists in list, <code>false</code>
	 *         otherwise.
	 * 
	 */
	public static boolean contains(String[] list, String value) {
		if(list == null) {
			return false;
		}
		
		if(list.length == 0) {
			return false;
		}
		
		for(String item : list) {
			if(item.equals(value)) {
				return true;
			}
		}
		
		return false;
	}

}

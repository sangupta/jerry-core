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
	 * Function to give a HEX representation of the byte array in small-case.
	 * 
	 * @param bytes
	 *            the source byte array
	 *            
	 * @return the HEX coded string representing the byte array
	 */
	public static String asHex(byte bytes[]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; ++i) {
			sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}
	
	/**
	 * Function to return a HEX representation of the given byte in small-case.
	 * 
	 * @param b
	 *            the byte value
	 * 
	 * @return the hex representation
	 * 
	 */
	public static String asHex(byte b) {
		return Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3);
	}
	
	/**
	 * Return the {@link String} representation of the byte array. A
	 * <code>null</code> byte array is converted back to <code>null</code>, an
	 * empty array to an empty string or the bytes to the actual string based on
	 * platform encoding.
	 * 
	 * @param bytes
	 *            the byte array
	 * 
	 * @return its string representation
	 */
	public static String asString(byte[] bytes) {
		if(bytes == null) {
			return null;
		}
		
		if(bytes.length == 0) {
			return EMPTY_STRING;
		}
		
		return new String(bytes);
	}
	
	/**
	 * Return the {@link String} representation of the byte array. A
	 * <code>null</code> byte array is converted back to <code>null</code>, an
	 * empty array to an empty string or the bytes to the actual string in UTF-8
	 * encoding.
	 * 
	 * @param bytes
	 *            the byte array
	 * 
	 * @return its string representation
	 */
	public static String asStringUTF8(byte[] bytes) {
		if(bytes == null) {
			return null;
		}
		
		if(bytes.length == 0) {
			return EMPTY_STRING;
		}
		
		return new String(bytes, CHARSET_UTF8);
	}
	
	/**
	 * Return the actual array that this hex string represents.
	 * 
	 * @param hex
	 *            the hex string
	 * 
	 * @return the actual byte array for the hex string
	 */
	public static byte[] fromHex(String hex) {
		if(AssertUtils.isEmpty(hex)) {
			return null;
		}
		
		int len = hex.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i+1), 16));
	    }
	    
	    return data;
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
	 * Parse and return the float value of the given string. If the string cannot
	 * be parsed, returns the default value provided
	 * 
	 * @param string
	 *            the string to be parsed
	 * 
	 * @param defaultValue
	 *            the value to be returned if parsing string fails
	 * 
	 * @return the float value of the string
	 */
	public static float getFloatValue(String string, float defaultValue) {
		try {
			if(AssertUtils.isNotEmpty(string)) {
				return Float.parseFloat(string);
			}
		} catch(NumberFormatException e) {
			LOGGER.debug("error getting long from string: " + string, e);
		}
		
		return defaultValue;
	}
	
	/**
	 * Parse and return the double value of the given string. If the string cannot
	 * be parsed, returns the default value provided
	 * 
	 * @param string
	 *            the string to be parsed
	 * 
	 * @param defaultValue
	 *            the value to be returned if parsing string fails
	 * 
	 * @return the double value of the string
	 */
	public static double getDoubleValue(String string, double defaultValue) {
		try {
			if(AssertUtils.isNotEmpty(string)) {
				return Double.parseDouble(string);
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
	
	/**
	 * Return a subset of string from the location where the given 'from' word
	 * exists.
	 * 
	 * @param string
	 *            the string from which substring needs to be extracted
	 * 
	 * @param from
	 *            the string to be looked
	 * 
	 * @param searchIndex
	 *            the index from which the string needs to be searched
	 * 
	 * @return the substring after the word is found inside the main string
	 */
	public static String substringAfter(String string, String from, int searchIndex) {
		if(AssertUtils.isEmpty(string)) {
			return EMPTY_STRING;
		}
		
		if(AssertUtils.isEmpty(from)) {
			return string;
		}
		
		int index = string.indexOf(from, searchIndex);
		if(index == -1) {
			return EMPTY_STRING;
		}
		
		return string.substring(index + from.length());
	}
	
	/**
	 * Find the substring between the given prefix and suffix.
	 * 
	 * @param string
	 *            the string which needs to be sub-string'ed
	 * 
	 * @param prefix
	 *            the string before
	 * 
	 * @param suffix
	 *            the string after
	 *            
	 * @return the sub-string between the prefix and suffix
	 * 
	 */
	public static String substringBetween(String string, String prefix, String suffix) {
		return StringUtils.substringBetween(string, prefix, suffix, 0);
	}

	/**
	 * Find the substring between the given prefix and suffix.
	 * 
	 * @param string
	 *            the string which needs to be sub-string'ed
	 * 
	 * @param prefix
	 *            the string before
	 * 
	 * @param suffix
	 *            the string after
	 * 
	 * @param startSearch
	 * 			  the index to start searching the prefix from
	 * 
	 * @return the sub-string between the prefix and suffix
	 * 
	 */
	public static String substringBetween(String string, String prefix, String suffix, int startSearch) {
		if(AssertUtils.isEmpty(string)) {
			return EMPTY_STRING;
		}
		
		int begin = 0, end = string.length();
		if(AssertUtils.isNotBlank(prefix)) {
			begin = string.indexOf(prefix, startSearch);
			if(begin != -1) {
				begin += prefix.length();
			} else {
				begin = 0;
			}
		}
		
		if(AssertUtils.isNotEmpty(suffix)) {
			end = string.indexOf(suffix, begin);
			if(end == -1) {
				end = string.length();
			}
		}
		
		return string.substring(begin, end);
	}
	
	/**
	 * Find the last index of searchString inside string, where searchString
	 * occurs before the suffix in the string.
	 * 
	 * @param string
	 *            the string in which the search will be performed
	 * 
	 * @param searchString
	 *            the string that needs to be searched
	 * 
	 * @param suffix
	 *            the string that decides the end location of the search
	 * 
	 * @return the index of searchString inside string, terminated at point
	 *         where suffix occurs
	 */
	public static int lastIndexBefore(String string, String searchString, String suffix) {
		int index = string.indexOf(suffix);
		if(index != -1) {
			string = string.substring(0, index);
		}
		
		return string.lastIndexOf(searchString);
	}
	
	/**
	 * Find the nth index of a string-part inside another string.
	 * 
	 * @param string
	 *            the string in which the search will be performed
	 * 
	 * @param searchString
	 *            the string that needs to be searched
	 * 
	 * @param n
	 *            the occurrence number that we are looking for
	 * 
	 * @return the index of nth occurrence of searchString inside string
	 */
	public static int nthIndexOf(String string, String searchString, int n) {
		if(n <= 0) {
			throw new IllegalArgumentException("n cannot be less than, or equal to zero");
		}
		
		if(n == 1) {
			return string.indexOf(searchString);
		}
		
		int count = 0;
		int index = -1;
		do {
			index = string.indexOf(searchString, index + 1);
			if(index == -1) {
				return -1;
			}
			
			count++;
			if(count == n) {
				return index;
			}
		} while(true);
	}

}

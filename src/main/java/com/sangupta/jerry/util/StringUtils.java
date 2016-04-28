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

import java.nio.charset.Charset;
import java.util.Arrays;
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
	 * Platform specific charset
	 */
	public static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
	
	/**
	 * An empty string object containing nothing.
	 */
	public static final String EMPTY_STRING = "";
	
	/**
	 * A blank string containing one blank white-space character.
	 */
	public static final String BLANK_STRING = " ";
	
	/**
	 * A String[] array that is empty and contains no elements
	 */
	public static final String[] EMPTY_STRING_LIST = new String[] { };

	/**
	 * Defines the platform dependent line encoding
	 */
	public static final String SYSTEM_NEW_LINE = System.getProperty("line.separator");
	
	/**
	 * Comma as a separator character
	 */
	public static final String COMMA_SEPARATOR_CHAR = ",";
	
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
	        if ("yes".equals(boolString) || "true".equals(boolString) || "on".equals(boolString)) {
	            return true;
	        }
	        
	        if ("no".equals(boolString) || "false".equals(boolString) || "off".equals(boolString)) {
	        	return false;
	        }
	    }
		
	    return defaultValue; 
 	}
	
	public static byte getByteValue(String string, byte defaultValue) {
		try {
			if(AssertUtils.isNotEmpty(string)) {
				return Byte.parseByte(string);
			}
		} catch(NumberFormatException e) {
			LOGGER.debug("error getting byte from string: " + string, e);
		}
		
		return defaultValue;
	}
	
	public static char getCharValue(String string, char defaultValue) {
		if(AssertUtils.isNotEmpty(string)) {
			return string.charAt(0);
		}
		
		return defaultValue;
	}
	
	/**
	 * Parse and return the short value of the given string. If the string cannot
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
	public static short getShortValue(String string, short defaultValue) {
		try {
			if(AssertUtils.isNotEmpty(string)) {
				return Short.parseShort(string);
			}
		} catch(NumberFormatException e) {
			LOGGER.debug("error getting short from string: " + string, e);
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
	 * @param <T>
	 *            the type of instances contained in the collection
	 * 
	 * @return the string representation of list using appender as the delimiter
	 */
	public static <T> String listToString(List<T> list, String appender) {
		if(AssertUtils.isEmpty(list)) {
			return EMPTY_STRING;
		}
		
		final boolean hasAppender = AssertUtils.isNotEmpty(appender);
		
		StringBuilder builder = new StringBuilder();
		for(int index = 0; index < list.size(); index++) {
			if(hasAppender && index > 0) {
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
	 *         
	 */
	public static int lastIndexBefore(String string, String searchString, String suffix) {
		if(AssertUtils.isEmpty(string)) {
			return -1;
		}
		
		if(AssertUtils.isEmpty(searchString)) {
			return -1;
		}
		
		int index = string.indexOf(suffix);
		if(index != -1) {
			string = string.substring(0, index);
		}
		
		return string.lastIndexOf(searchString);
	}
	
	public static int lastIndexBefore(String string, String searchString, int maxSuffix) {
		if(AssertUtils.isEmpty(string)) {
			return -1;
		}
		
		if(AssertUtils.isEmpty(searchString)) {
			return -1;
		}
		
		if(maxSuffix == -1) {
			return string.lastIndexOf(searchString);
		}
		
		maxSuffix = maxSuffix - searchString.length() + 1;
		
		int start = -2;
		int index = -1;
		do {
			index = string.indexOf(searchString, start + 1);
			if(index == -1) {
				break;
			}
			
			if(index >= maxSuffix) {
				index = start;
				break;
			}
			
			start = index;
		} while(true);
		
		if(start < 0) {
			return -1;
		}
		
		return start;
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
	
	/**
	 * Check if the given string starts with the given prefix, ignoring case.
	 * 
	 * @param text
	 *            the string to search in
	 * 
	 * @param prefix
	 *            the prefix to search for
	 * 
	 * @return <code>true</code> if the string starts with prefix,
	 *         <code>false</code> otherwise
	 */
	public static boolean startsWithIgnoreCase(String text, String prefix) {
		if(AssertUtils.isEmpty(text)) {
			return false;
		}
		
		if(AssertUtils.isEmpty(prefix)) {
			return false;
		}
		
		if(text == prefix) {
			return true; // same string
		}
		
		if(prefix.length() > text.length()) {
			return false;
		}
		
		if(text.substring(0, prefix.length()).equalsIgnoreCase(prefix)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if the given string ends with the given suffix, ignoring case.
	 * 
	 * @param text
	 *            the string to search in
	 * 
	 * @param suffix
	 *            the suffix to search for
	 * 
	 * @return <code>true</code> if the string ends with suffix,
	 *         <code>false</code> otherwise
	 */
	public static boolean endsWithIgnoreCase(String text, String suffix) {
		if(AssertUtils.isEmpty(text)) {
			return false;
		}
		
		if(AssertUtils.isEmpty(suffix)) {
			return false;
		}
		
		if(text == suffix) {
			return true; // same string
		}
		
		int start = text.length() - suffix.length();
		if(start < 0) {
			return false;
		}
		
		if(text.substring(start).equalsIgnoreCase(suffix)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Merge the items into a single string separated by the given delimiter
	 * 
	 * @param items
	 *            Items to be merged into a string
	 * 
	 * @param delimiter
	 *            the character delimiter to be used
	 * 
	 * @return the merged string
	 */
	public static String merge(String[] items, char delimiter) {
		if(AssertUtils.isEmpty(items)) {
			return StringUtils.EMPTY_STRING;
		}
		
		StringBuilder builder = new StringBuilder(1024);
		for(int index = 0; index < items.length; index++) {
			if(index > 0) {
				builder.append(delimiter);
			}
			
			builder.append(items[index]);
		}
		
		return builder.toString();
	}

	/**
	 * Merge the items into a single string separated by the given delimiter
	 * 
	 * @param items
	 *            Items to be merged into a string
	 * 
	 * @param delimiter
	 *            the string delimiter to be used
	 * 
	 * @return the merged string
	 */
	public static String merge(String[] items, String delimiter) {
		if(AssertUtils.isEmpty(items)) {
			return StringUtils.EMPTY_STRING;
		}
		
		boolean addDelimiter = AssertUtils.isNotEmpty(delimiter);
		
		StringBuilder builder = new StringBuilder(1024);
		for(int index = 0; index < items.length; index++) {
			if(addDelimiter) {
				if(index > 0) {
					builder.append(delimiter);
				}
			}
			
			builder.append(items[index]);
		}
		
		return builder.toString();
	}
	
	/**
	 * Return a string containing the same character the given number of times.
	 * 
	 * @param ch
	 *            the character to repeat
	 * 
	 * @param times
	 *            the number of times to use
	 * 
	 * @return the {@link String} instance thus created
	 * 
	 * @throws IllegalArgumentException
	 *             if the number of times to repeat is less than or equal to
	 *             ZERO
	 */
	public static String repeat(char ch, int times) {
		if(times <= 0) {
			throw new IllegalArgumentException("Length cannot be less than or equal to zero");
		}
		
		if(times == 1) {
			return String.valueOf(ch);
		}
		
		char[] array = new char[times];
		Arrays.fill(array, ch);
		return new String(array);
	}
	
	/**
	 * Convert the property name to JSON valid property name. Convert all
	 * illegal characters to underscore.
	 * 
	 * @param name
	 *            the property name to convert
	 * 
	 * @return the converted property name
	 */
	public static String convertToJsonPropertyName(String name) {
		char[] chars = name.toCharArray();
		for(int index = 0; index < chars.length; index++) {
			char c = chars[index];
			if(!(('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || (c == '_') || (c == '-'))) {
				chars[index] = '_';
			}
		}
		
		return new String(chars);
	}
	
	/**
	 * Convert the property name to XML valid property name. Convert all illegal
	 * characters to hyphen.
	 * 
	 * @param name
	 *            the property name to convert
	 * 
	 * @return the converted property name
	 */
	public static String convertToXmlPropertyName(String name) {
		char[] chars = name.toCharArray();
		for(int index = 0; index < chars.length; index++) {
			char c = chars[index];
			if(!(('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || (c == '_') || (c == '-'))) {
				chars[index] = '-';
			}
		}
		
		return new String(chars);
	}

	/**
	 * Returns a {@link String} filled with random characters of given length.
	 *  
	 * @param length
	 *            the number of characters to create. If value is less than or equal
	 *            to zero, an {@link IllegalArgumentException} is thrown
	 * 
	 * @return the randomly generated string
	 * 
	 * @throws IllegalArgumentException
	 *             if length is less than or equal to zero
	 */
	public static String getRandomString(int length) {
		if(length <= 0) {
			throw new IllegalArgumentException("Length cannot be less than or equal to zero");
		}
		
		byte[] bytes = ByteArrayUtils.getRandomBytes(length);
		return new String(bytes);
	}
	
	/**
	 * Stringify the array by joining elements taking comma as a separator.
	 *
	 * @param array
	 *            the array to be stringified
	 * 
	 * @return the {@link String} representation or <code>null</code>
	 */
	public static String stringifyArray(Object[] array) {
		if(AssertUtils.isEmpty(array)) {
			return null;
		}
		
		// compute space requirement
		int size = 0;
		if(array instanceof String[]) {
			String[] strArray = (String[]) array;
			
			for(String str : strArray) {
				if(str != null) {
					size += str.length();
				}
			}
			
			// add additional space for separators
			size = size + (2 * array.length);
		}
		
		final StringBuilder builder;
		if(size > 0) {
			builder = new StringBuilder(size);
		} else {
			builder = new StringBuilder();
		}
		
		boolean first = true;
		for(int index = 0; index < array.length; index++) {
			Object object = array[index];
			
			if(!first) {
				builder.append(COMMA_SEPARATOR_CHAR);
			}
			
			if(object == null) {
				continue;
			}
			
			builder.append(object.toString());
			
			first = false;
		}
		
		return builder.toString();
	}
	
	/**
	 * Stringify an integer-array.
	 * 
	 * @param array
	 *            the <code>int[]</code> array to be stringified
	 * 
	 * @return the {@link String} representation or <code>null</code>
	 */
	public static String stringifyArray(int[] array) {
		if(AssertUtils.isEmpty(array)) {
			return null;
		}
		
		StringBuilder builder = new StringBuilder(11 * array.length);
		
		boolean first = true;
		for(int integer : array) {
			
			if(!first) {
				builder.append(COMMA_SEPARATOR_CHAR);
			}
			
			builder.append(String.valueOf(integer));
			
			// set first to false
			first = false;
		}
		
		return builder.toString();
	}
	
	/**
	 * Stringify a short-array.
	 * 
	 * @param array
	 *            the <code>short[]</code> array to be stringified
	 * 
	 * @return the {@link String} representation or <code>null</code>
	 */
	public static String stringifyArray(short[] array) {
		if(AssertUtils.isEmpty(array)) {
			return null;
		}
		
		StringBuilder builder = new StringBuilder(7 * array.length);
		
		boolean first = true;
		for(short value : array) {
			
			if(!first) {
				builder.append(COMMA_SEPARATOR_CHAR);
			}
			
			builder.append(String.valueOf(value));
			
			// set first to false
			first = false;
		}
		
		return builder.toString();
	}
	
	/**
	 * Stringify a long-array.
	 * 
	 * @param array
	 *            the <code>long[]</code> array to be stringified
	 * 
	 * @return the {@link String} representation or <code>null</code>
	 */
	public static String stringifyArray(long[] array) {
		if(AssertUtils.isEmpty(array)) {
			return null;
		}
		
		StringBuilder builder = new StringBuilder(15 * array.length);
		
		boolean first = true;
		for(long value : array) {
			
			if(!first) {
				builder.append(COMMA_SEPARATOR_CHAR);
			}
			
			builder.append(String.valueOf(value));
			
			// set first to false
			first = false;
		}
		
		return builder.toString();
	}
	
	/**
	 * Stringify a char-array.
	 * 
	 * @param array
	 *            the <code>char[]</code> array to be stringified
	 * 
	 * @return the {@link String} representation or <code>null</code>
	 */
	public static String stringifyArray(char[] array) {
		if(AssertUtils.isEmpty(array)) {
			return null;
		}
		
		StringBuilder builder = new StringBuilder(2 * array.length);
		
		boolean first = true;
		for(char value : array) {
			
			if(!first) {
				builder.append(COMMA_SEPARATOR_CHAR);
			}
			
			builder.append(String.valueOf(value));
			
			// set first to false
			first = false;
		}
		
		return builder.toString();
	}
	
	/**
	 * Stringify a float-array.
	 * 
	 * @param array
	 *            the <code>float[]</code> array to be stringified
	 * 
	 * @return the {@link String} representation or <code>null</code>
	 */
	public static String stringifyArray(float[] array) {
		if(AssertUtils.isEmpty(array)) {
			return null;
		}
		
		StringBuilder builder = new StringBuilder(15 * array.length);
		
		boolean first = true;
		for(float value : array) {
			
			if(!first) {
				builder.append(COMMA_SEPARATOR_CHAR);
			}
			
			builder.append(String.valueOf(value));
			
			// set first to false
			first = false;
		}
		
		return builder.toString();
	}
	
	/**
	 * Stringify a double-array.
	 * 
	 * @param array
	 *            the <code>double[]</code> array to be stringified
	 * 
	 * @return the {@link String} representation or <code>null</code>
	 */
	public static String stringifyArray(double[] array) {
		if(AssertUtils.isEmpty(array)) {
			return null;
		}
		
		StringBuilder builder = new StringBuilder(15 * array.length);
		
		boolean first = true;
		for(double value : array) {
			
			if(!first) {
				builder.append(COMMA_SEPARATOR_CHAR);
			}
			
			builder.append(String.valueOf(value));
			
			// set first to false
			first = false;
		}
		
		return builder.toString();
	}
	
	/**
	 * Stringify a byte-array.
	 * 
	 * @param array
	 *            the <code>byte[]</code> array to be stringified
	 * 
	 * @return the {@link String} representation or <code>null</code>
	 */
	public static String stringifyArray(byte[] array) {
		if(AssertUtils.isEmpty(array)) {
			return null;
		}
		
		StringBuilder builder = new StringBuilder(5 * array.length);
		
		boolean first = true;
		for(byte value : array) {
			
			if(!first) {
				builder.append(COMMA_SEPARATOR_CHAR);
			}
			
			builder.append(String.valueOf(value));
			
			// set first to false
			first = false;
		}
		
		return builder.toString();
	}
	
	/**
	 * Stringify a boolean-array.
	 * 
	 * @param array
	 *            the <code>boolean[]</code> array to be stringified
	 * 
	 * @return the {@link String} representation or <code>null</code>
	 */
	public static String stringifyArray(boolean[] array) {
		if(AssertUtils.isEmpty(array)) {
			return null;
		}
		
		StringBuilder builder = new StringBuilder(5 * array.length);
		
		boolean first = true;
		for(boolean value : array) {
			
			if(!first) {
				builder.append(COMMA_SEPARATOR_CHAR);
			}
			
			builder.append(String.valueOf(value));
			
			// set first to false
			first = false;
		}
		
		return builder.toString();
	}
	
	/**
	 * Construct back a byte-array from its stringified representation.
	 * 
	 * @param value
	 *            the {@link String} representation
	 * 
	 * @return the <code>byte[]</code> thus constructed, or <code>null</code>
	 * 
	 * @throws NumberFormatException
	 *             if any of the value cannot be parsed
	 */
	public static byte[] deStringifyByteArray(String value) {
		if(AssertUtils.isEmpty(value)) {
			return null;
		}
		
		String[] tokens = value.split(COMMA_SEPARATOR_CHAR);
		byte[] array = new byte[tokens.length];
		for(int index = 0; index < tokens.length; index++) {
			array[index] = Byte.parseByte(tokens[index]);
		}
		
		return array;
	}
	
	/**
	 * Construct back a char-array from its stringified representation.
	 * 
	 * @param value
	 *            the {@link String} representation
	 * 
	 * @return the <code>char[]</code> thus constructed, or <code>null</code>
	 * 
	 * @throws NumberFormatException
	 *             if any of the value cannot be parsed
	 */
	public static char[] deStringifyCharArray(String value) {
		if(AssertUtils.isEmpty(value)) {
			return null;
		}
		
		String[] tokens = value.split(COMMA_SEPARATOR_CHAR);
		char[] array = new char[tokens.length];
		for(int index = 0; index < tokens.length; index++) {
			array[index] = tokens[index].charAt(0);
		}
		
		return array;
	}
	
	/**
	 * Construct back a short-array from its stringified representation.
	 * 
	 * @param value
	 *            the {@link String} representation
	 * 
	 * @return the <code>short[]</code> thus constructed, or <code>null</code>
	 * 
	 * @throws NumberFormatException
	 *             if any of the value cannot be parsed
	 */
	public static short[] deStringifyShortArray(String value) {
		if(AssertUtils.isEmpty(value)) {
			return null;
		}
		
		String[] tokens = value.split(COMMA_SEPARATOR_CHAR);
		short[] array = new short[tokens.length];
		for(int index = 0; index < tokens.length; index++) {
			array[index] = Short.valueOf(tokens[index]);
		}
		
		return array;
	}
	
	/**
	 * Construct back a boolean-array from its stringified representation.
	 * 
	 * @param value
	 *            the {@link String} representation
	 * 
	 * @return the <code>boolean[]</code> thus constructed, or <code>null</code>
	 * 
	 * @throws NumberFormatException
	 *             if any of the value cannot be parsed
	 */
	public static boolean[] deStringifyBooleanArray(String value) {
		if(AssertUtils.isEmpty(value)) {
			return null;
		}
		
		String[] tokens = value.split(COMMA_SEPARATOR_CHAR);
		boolean[] array = new boolean[tokens.length];
		for(int index = 0; index < tokens.length; index++) {
			array[index] = getBoolean(tokens[index]);
		}
		
		return array;
	}

	/**
	 * Construct back a int-array from its stringified representation.
	 * 
	 * @param value
	 *            the {@link String} representation
	 * 
	 * @return the <code>int[]</code> thus constructed, or <code>null</code>
	 * 
	 * @throws NumberFormatException
	 *             if any of the value cannot be parsed
	 */
	public static int[] deStringifyIntArray(String value) {
		if(AssertUtils.isEmpty(value)) {
			return null;
		}
		
		String[] tokens = value.split(COMMA_SEPARATOR_CHAR);
		int[] array = new int[tokens.length];
		for(int index = 0; index < tokens.length; index++) {
			array[index] = Integer.parseInt(tokens[index]);
		}
		
		return array;
	}

	/**
	 * Construct back a long-array from its stringified representation.
	 * 
	 * @param value
	 *            the {@link String} representation
	 * 
	 * @return the <code>long[]</code> thus constructed, or <code>null</code>
	 * 
	 * @throws NumberFormatException
	 *             if any of the value cannot be parsed
	 */
	public static long[] deStringifyLongArray(String value) {
		if(AssertUtils.isEmpty(value)) {
			return null;
		}
		
		String[] tokens = value.split(COMMA_SEPARATOR_CHAR);
		long[] array = new long[tokens.length];
		for(int index = 0; index < tokens.length; index++) {
			array[index] = Long.valueOf(tokens[index]);
		}
		
		return array;
	}
	
	/**
	 * Construct back a float-array from its stringified representation.
	 * 
	 * @param value
	 *            the {@link String} representation
	 * 
	 * @return the <code>float[]</code> thus constructed, or <code>null</code>
	 * 
	 * @throws NumberFormatException
	 *             if any of the value cannot be parsed
	 */
	public static float[] deStringifyFloatArray(String value) {
		if(AssertUtils.isEmpty(value)) {
			return null;
		}
		
		String[] tokens = value.split(COMMA_SEPARATOR_CHAR);
		float[] array = new float[tokens.length];
		for(int index = 0; index < tokens.length; index++) {
			array[index] = Float.valueOf(tokens[index]);
		}
		
		return array;
	}
	
	/**
	 * Construct back a double-array from its stringified representation.
	 * 
	 * @param value
	 *            the {@link String} representation
	 * 
	 * @return the <code>double[]</code> thus constructed, or <code>null</code>
	 * 
	 * @throws NumberFormatException
	 *             if any of the value cannot be parsed
	 */
	public static double[] deStringifyDoubleArray(String value) {
		if(AssertUtils.isEmpty(value)) {
			return null;
		}
		
		String[] tokens = value.split(COMMA_SEPARATOR_CHAR);
		double[] array = new double[tokens.length];
		for(int index = 0; index < tokens.length; index++) {
			array[index] = Double.valueOf(tokens[index]);
		}
		
		return array;
	}

	public static boolean wildcardMatch(String string, String pattern) {
		int i = 0;
		int j = 0;
		int starIndex = -1;
		int iIndex = -1;
	 
		while (i < string.length()) {
			if (j < pattern.length() && (pattern.charAt(j) == '?' || pattern.charAt(j) == string.charAt(i))) {
				++i;
				++j;
			} else if (j < pattern.length() && pattern.charAt(j) == '*') {
				starIndex = j;		
				iIndex = i;
				j++;
			} else if (starIndex != -1) {
				j = starIndex + 1;
				i = iIndex+1;
				iIndex++;
			} else {
				return false;
			}
		}
	 
		while (j < pattern.length() && pattern.charAt(j) == '*') {
			++j;
		}
	 
		return j == pattern.length();
	}
}
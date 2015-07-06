/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2015, Sandeep Gupta
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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Utility methods to work with {@link Properties}.
 * 
 * @author sangupta
 *
 */
public class PropertiesUtils {

	/**
	 * Convert a given {@link Properties} instance to a corresponding
	 * {@link HashMap} instance. If the {@link Properties} instance is
	 * <code>null</code>, a <code>null</code> is returned back. If the instance
	 * is <code>empty</code>, an <code>empty</code> map is returned. Else, a
	 * {@link HashMap} with all keys is populated and returned.
	 * 
	 * @param properties
	 *            the {@link Properties} instance to be converted
	 * 
	 * @return the corresponding {@link HashMap} instance, or <code>null</code>
	 */
	public static Map<String, String> asMap(Properties properties) {
		if(properties == null) {
			return null;
		}
		
		Map<String, String> map = new HashMap<String, String>();
		if(properties.isEmpty()) {
			return map;
		}
		
		Set<String> keys = properties.stringPropertyNames();
		for(String key : keys) {
			String value = properties.getProperty(key);
			map.put(key, value);
		}
		
		return map;
	}

	/**
	 * Convert a given {@link Map} instance to a corresponding
	 * {@link Properties} instance
	 * 
	 * @param map
	 *            the map to convert
	 * 
	 * @return the {@link Properties} instance thus created and populated
	 */
	public static Properties fromMap(Map<String, String> map) {
		if(map == null) {
			return null;
		}
		
		Properties properties = new Properties();
		if(map.isEmpty()) {
			return properties;
		}
		
		Set<String> keys = map.keySet();
		for(String key : keys) {
			properties.setProperty(key, map.get(key));
		}
		
		return properties;
	}

	/**
	 * Convert and return a {@link Boolean} value after reading from
	 * {@link Properties} the {@link String} value against the given
	 * <code>key</code>. If no value exists a <code>false</code> is returned.
	 * 
	 * @param properties
	 *            the {@link Properties} instance to read from
	 * 
	 * @param key
	 *            the <code>key</code> to look for
	 * 
	 * @return the {@link Boolean} value thus deciphered
	 */
	public static boolean getBooleanValue(Properties properties, String key) {
		return getBooleanValue(properties, key, false);
	}
	
	/**
	 * Convert and return a {@link Boolean} value after reading from
	 * {@link Properties} the {@link String} value against the given
	 * <code>key</code>. If no value exists the given <code>defaultValue</code>
	 * is returned.
	 * 
	 * @param properties
	 *            the {@link Properties} instance to read from
	 * 
	 * @param key
	 *            the <code>key</code> to look for
	 * 
	 * @param defaultValue
	 *            the default value to return if the property cannot be found or
	 *            the value specified for property is <code>null</code> or empty
	 * 
	 * @return the {@link Boolean} value thus deciphered
	 */
	public static boolean getBooleanValue(Properties properties, String key, boolean defaultValue) {
		if(AssertUtils.isEmpty(properties)) {
			return defaultValue;
		}
		
		String value = properties.getProperty(key);
		return StringUtils.getBoolean(value, defaultValue);
	}
	
	/**
	 * Convert and return a {@link Short} value after reading from
	 * {@link Properties} the {@link String} value against the given
	 * <code>key</code>. If no value exists the given <code>defaultValue</code>
	 * is returned.
	 * 
	 * @param properties
	 *            the {@link Properties} instance to read from
	 * 
	 * @param key
	 *            the <code>key</code> to look for
	 * 
	 * @param defaultValue
	 *            the default value to return if the property cannot be found or
	 *            the value specified for property is <code>null</code> or empty
	 * 
	 * @return the {@link Short} value thus deciphered
	 */
	public static short getShortValue(Properties properties, String key, short defaultValue) {
		if(AssertUtils.isEmpty(properties)) {
			return defaultValue;
		}
		
		String value = properties.getProperty(key);
		return StringUtils.getShortValue(value, defaultValue);
	}
	
	/**
	 * Convert and return a {@link Integer} value after reading from
	 * {@link Properties} the {@link String} value against the given
	 * <code>key</code>. If no value exists the given <code>defaultValue</code>
	 * is returned.
	 * 
	 * @param properties
	 *            the {@link Properties} instance to read from
	 * 
	 * @param key
	 *            the <code>key</code> to look for
	 * 
	 * @param defaultValue
	 *            the default value to return if the property cannot be found or
	 *            the value specified for property is <code>null</code> or empty
	 * 
	 * @return the {@link Integer} value thus deciphered
	 */
	public static int getIntValue(Properties properties, String key, int defaultValue) {
		if(AssertUtils.isEmpty(properties)) {
			return defaultValue;
		}
		
		String value = properties.getProperty(key);
		return StringUtils.getIntValue(value, defaultValue);
	}
	
	/**
	 * Convert and return a {@link Long} value after reading from
	 * {@link Properties} the {@link String} value against the given
	 * <code>key</code>. If no value exists the given <code>defaultValue</code>
	 * is returned.
	 * 
	 * @param properties
	 *            the {@link Properties} instance to read from
	 * 
	 * @param key
	 *            the <code>key</code> to look for
	 * 
	 * @param defaultValue
	 *            the default value to return if the property cannot be found or
	 *            the value specified for property is <code>null</code> or empty
	 * 
	 * @return the {@link Long} value thus deciphered
	 */
	public static long getLongValue(Properties properties, String key, long defaultValue) {
		if(AssertUtils.isEmpty(properties)) {
			return defaultValue;
		}
		
		String value = properties.getProperty(key);
		return StringUtils.getLongValue(value, defaultValue);
	}
	
	/**
	 * Convert and return a {@link Float} value after reading from
	 * {@link Properties} the {@link String} value against the given
	 * <code>key</code>. If no value exists the given <code>defaultValue</code>
	 * is returned.
	 * 
	 * @param properties
	 *            the {@link Properties} instance to read from
	 * 
	 * @param key
	 *            the <code>key</code> to look for
	 * 
	 * @param defaultValue
	 *            the default value to return if the property cannot be found or
	 *            the value specified for property is <code>null</code> or empty
	 * 
	 * @return the {@link Float} value thus deciphered
	 */
	public static float getFloatValue(Properties properties, String key, float defaultValue) {
		if(AssertUtils.isEmpty(properties)) {
			return defaultValue;
		}
		
		String value = properties.getProperty(key);
		return StringUtils.getFloatValue(value, defaultValue);
	}

	/**
	 * Convert and return a {@link Double} value after reading from
	 * {@link Properties} the {@link String} value against the given
	 * <code>key</code>. If no value exists the given <code>defaultValue</code>
	 * is returned.
	 * 
	 * @param properties
	 *            the {@link Properties} instance to read from
	 * 
	 * @param key
	 *            the <code>key</code> to look for
	 *
	 * @param defaultValue
	 *            the default value to return if the property cannot be found or
	 *            the value specified for property is <code>null</code> or empty
	 * 
	 * @return the {@link Double} value thus deciphered
	 */
	public static double getDoubleValue(Properties properties, String key, double defaultValue) {
		if(AssertUtils.isEmpty(properties)) {
			return defaultValue;
		}
		
		String value = properties.getProperty(key);
		return StringUtils.getDoubleValue(value, defaultValue);
	}
	
}

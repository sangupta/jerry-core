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
	 * @return
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
	
}

/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2017, Sandeep Gupta
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

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author sangupta
 *
 */
public abstract class EnvironmentUtils {

	/**
	 * Read an environment variable first from the java command line, and then
	 * from the system variables. If none is found, return <code>null</code>
	 *
	 * @param name
	 *            the property name to read
	 *
	 * @return the value of the property
	 */
	public static String readProperty(String name) {
		String value = System.getProperty(name);
		if(value != null) {
			return value;
		}

		return System.getenv(name);
	}

	/**
	 * Read an environment variable first from the java command line, and then
	 * from the system variables. If none is found, return the default value.
	 *
	 * @param name
	 *            the property name to read
	 *
	 * @param defaultValue
	 *            the value of return in case no such property is found
	 *
	 * @return the value of the property or defaultValue
	 */
	public static String readProperty(String name, String defaultValue) {
		String value = readProperty(name);
		if(value == null) {
			return defaultValue;
		}

		return value;
	}

	/**
	 * Dump all environment variables and properties as a String and return it
	 * back. Useful for logging all values for debugging purposes.
	 *
	 * @return a {@link String} instance consisting of all system properties
	 *         separated by system new line
	 */
	public static String dumpAllProperties() {
		StringBuilder builder = new StringBuilder(4096); // 4KB to start with

		// first all properties
		Properties properties = System.getProperties();
		if(!properties.isEmpty()) {
			Enumeration<Object> keys = properties.keys();

			while(keys.hasMoreElements()) {
				Object key = keys.nextElement();
				Object value = properties.get(key);
				builder.append("Property: ");
				builder.append(key);
				builder.append('=');
				builder.append(value);
				builder.append(StringUtils.SYSTEM_NEW_LINE);
			}
		}

		// next all environment values
		Map<String, String> map = System.getenv();
		if(AssertUtils.isNotEmpty(map)) {
			Set<String> keys = map.keySet();
			for(String key : keys) {
				String value = map.get(key);

				builder.append("Environment: ");
				builder.append(key);
				builder.append('=');
				builder.append(value);
				builder.append(StringUtils.SYSTEM_NEW_LINE);
			}
		}
		return builder.toString();
	}
}

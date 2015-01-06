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

/**
 * @author sangupta
 *
 */
public class EnvironmentUtils {
	
	/**
	 * Read an environment variable first from the java command line, and
	 * then from the system variables. If none is found, return <code>null</code>
	 * 
	 * @param name
	 * @return
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
	 * @param defaultValue
	 * @return
	 */
	public static String readProperty(String name, String defaultValue) {
		String value = readProperty(name);
		if(value == null) {
			return defaultValue;
		}
		
		return value;
	}

}

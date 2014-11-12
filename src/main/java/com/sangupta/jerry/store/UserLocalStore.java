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

package com.sangupta.jerry.store;

import java.util.List;

/**
 * Contract for a data store that intends to store
 * key-value pairs on behalf of the user in the user's 
 * home directory.
 * 
 * @author sangupta
 *
 */
public interface UserLocalStore {
	
	/**
	 * Read the value of the data-store.
	 * 
	 * @param property
	 * @return
	 */
	public String get(String property);
	
	/**
	 * Return the name of all keys within the data-store
	 * 
	 * @return
	 */
	public List<String> getAllKeys();
	
	/**
	 * Return the value associated with the property if found, or the provided
	 * default value.
	 * 
	 * @param property
	 *            the property being looked for
	 * 
	 * @param defaultValue
	 *            the default value
	 * 
	 * @return the actual value if found, or the default value
	 */
	public String get(String property, String defaultValue);

	/**
	 * Store the given value in the data-store.
	 *  
	 * @param key
	 * @param property
	 */
	public void put(String key, String property);
	
	/**
	 * Delete the value associated with the key in the data-store.
	 * 
	 * @param key
	 */
	public void delete(String key);
	
}

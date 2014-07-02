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

import java.io.File;

import com.sangupta.jerry.constants.SystemProperty;
import com.sangupta.jerry.util.AssertUtils;

/**
 * Abstract implementation of the {@link UserLocalStore} that provides
 * some base functionality around the key-value store.
 * 
 * @author sangupta
 *
 */
public abstract class AbstractUserLocalStore implements UserLocalStore {
	
	/**
	 * The directory where files need to be stored.
	 * 
	 */
	protected final File dataDirectory;
	
	/**
	 * Create a new data-store and initialize the directory in user-home
	 * and create any pending folders if needed.
	 * 
	 * @param folderName
	 */
	public AbstractUserLocalStore(String folderName) {
		if(AssertUtils.isEmpty(folderName)) {
			throw new IllegalArgumentException("Folder name cannot be empty/null");
		}
		
		File userHome = new File(System.getProperty(SystemProperty.USER_HOME));
		this.dataDirectory = new File(userHome, folderName);
		
		this.dataDirectory.mkdirs();
	}
	
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
	public String get(String property, String defaultValue) {
		String value = this.get(property);
		if(value == null) {
			return defaultValue;
		}
		
		return value;
	}
	
}

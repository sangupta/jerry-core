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
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

/**
 * A {@link Properties} based {@link UserLocalStore} that makes use of
 * properties to store all properties.
 * 
 * @author sangupta
 *
 */
public class PropertiesUserLocalStore extends AbstractUserLocalStore {
	
	protected final File propertiesFile;
	
	protected final Properties properties;
	
	public PropertiesUserLocalStore(String folderName, String fileName) {
		super(folderName);
		
		this.propertiesFile = new File(this.dataDirectory, fileName);
		this.properties = new Properties();
		
		if(this.propertiesFile.exists() && this.propertiesFile.isFile()) {
			try {
				this.properties.load(FileUtils.openInputStream(this.propertiesFile));
			} catch (IOException e) {
				throw new RuntimeException("Unable to read from the data store", e);
			}
		}
	}

	@Override
	public String get(String property) {
		return this.properties.getProperty(property);
	}

	@Override
	public void put(String key, String property) {
		this.properties.put(key, property);
		try {
			this.properties.store(FileUtils.openOutputStream(this.propertiesFile, false), "Updating from Properties data-store");
		} catch (IOException e) {
			throw new RuntimeException("Unable to write to the data store", e);
		}
	}

	@Override
	public void delete(String key) {
		this.put(key, null);
	}

}

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
 
package com.sangupta.jerry.store;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.sangupta.jerry.util.AssertUtils;

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
		
		if(AssertUtils.isEmpty(fileName)) {
			throw new IllegalArgumentException("Filename cannot be null/empty");
		}
		
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
	public Collection<String> getAllKeys() {
		List<String> list = new ArrayList<String>();
		
		Set<Object> set = this.properties.keySet();
		for(Object o : set) {
			list.add(o.toString());
		}
		
		return list;
	}

	@Override
	public void put(String key, String property) {
		this.properties.put(key, property);
		save();
	}
	
	@Override
	protected void putNoSave(String key, String property) {
		this.properties.put(key, property);
	}

	@Override
	public void delete(String key) {
		this.properties.remove(key);
		save();
	}
	
	/**
	 * Save the properties back to underlying storage
	 * 
	 */
	@Override
	protected void save() {
		try {
			this.properties.store(FileUtils.openOutputStream(this.propertiesFile, false), " Updating from Properties data-store");
		} catch (IOException e) {
			throw new RuntimeException("Unable to write to the data store", e);
		}
	}

}

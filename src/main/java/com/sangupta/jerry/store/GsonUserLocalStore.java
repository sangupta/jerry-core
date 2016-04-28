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


package com.sangupta.jerry.store;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.google.gson.internal.LinkedTreeMap;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.GsonUtils;

/**
 * An implementation of the {@link UserLocalStore} that uses Google
 * GSON library for persisting data as JSON on disk.
 *
 * @author sangupta
 *
 */
public class GsonUserLocalStore extends AbstractUserLocalStore {

	protected final File propertiesFile;

	protected final LinkedTreeMap<String, Object> properties = new LinkedTreeMap<String, Object>();

	@SuppressWarnings("unchecked")
	public GsonUserLocalStore(String folderName, String fileName) {
		super(folderName);

		if(AssertUtils.isEmpty(fileName)) {
			throw new IllegalArgumentException("Filename cannot be null/empty");
		}

		this.propertiesFile = new File(this.dataDirectory, fileName);

		if(this.propertiesFile.exists() && this.propertiesFile.isFile()) {
			try {
				// read file
				String contents = FileUtils.readFileToString(this.propertiesFile);
				if(AssertUtils.isEmpty(contents)) {
					return;
				}

				// read via gson
				LinkedTreeMap<String, Object> gsonProperties = GsonUtils.getGson().fromJson(contents, LinkedTreeMap.class);
				if(AssertUtils.isNotEmpty(gsonProperties)) {
					this.properties.putAll(gsonProperties);
				}
			} catch (IOException e) {
				throw new RuntimeException("Unable to read from the data store", e);
			}
		}
	}

	@Override
	public String get(String property) {
		Object object = this.properties.get(property);
		if(object == null) {
			return null;
		}

		return object.toString();
	}

	@Override
	public Collection<String> getAllKeys() {
		return this.properties.keySet();
	}

	@Override
	public void put(String key, Object property) {
		this.properties.put(key, property);
		save();
	}

	@Override
	protected void putNoSave(String key, Object property) {
		this.properties.put(key, property);
	}

	@Override
	protected Object getValueObject(String property) {
		return this.properties.get(property);
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
			FileUtils.writeStringToFile(this.propertiesFile, GsonUtils.getGson().toJson(this.properties));
		} catch (IOException e) {
			throw new RuntimeException("Unable to write to the data store", e);
		}
	}
}

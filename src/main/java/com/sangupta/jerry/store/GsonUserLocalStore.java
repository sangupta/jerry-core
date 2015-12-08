package com.sangupta.jerry.store;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.google.gson.internal.LinkedTreeMap;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.GsonUtils;

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
			FileUtils.writeStringToFile(this.propertiesFile, GsonUtils.getGson().toJson(this.properties));
		} catch (IOException e) {
			throw new RuntimeException("Unable to write to the data store", e);
		}
	}
}

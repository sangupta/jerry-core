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
import java.lang.reflect.Field;

import com.sangupta.jerry.constants.SystemPropertyNames;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.ReflectionUtils;
import com.sangupta.jerry.util.StringUtils;

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
	 * Create a new data-store and initialize the directory in user-home and
	 * create any pending folders if needed.
	 * 
	 * @param folderName
	 *            the name of the folder to create data-store in
	 */
	public AbstractUserLocalStore(String folderName) {
		if(folderName != null && AssertUtils.isEmpty(folderName)) {
			throw new IllegalArgumentException("Folder name cannot be empty/null");
		}
		
		File userHome = new File(System.getProperty(SystemPropertyNames.USER_HOME));
		if(folderName != null) {
			this.dataDirectory = new File(userHome, folderName);
			this.dataDirectory.mkdirs();
		} else {
			this.dataDirectory = userHome;
		}
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
	
	@Override
	public boolean getBoolean(String property, boolean defaultValue) {
		return StringUtils.getBoolean(this.get(property), defaultValue);
	}
	
	public int getInt(String property, int defaultValue) {
		return StringUtils.getIntValue(this.get(property), defaultValue);
	}
	
	public long getLong(String property, long defaultValue) {
		return StringUtils.getLongValue(this.get(property), defaultValue);
	}
	
	public float getFloat(String property, float defaultValue) {
		return StringUtils.getFloatValue(this.get(property), defaultValue);
	}
	
	public double getDouble(String property, double defaultValue) {
		return StringUtils.getDoubleValue(this.get(property), defaultValue);
	}
	
	public boolean readTo(Object instance) {
		if(instance == null) {
			return false;
		}
		
		Field[] fields = instance.getClass().getDeclaredFields();
		if(AssertUtils.isEmpty(fields)) {
			return true;
		}
		
		for(Field field : fields) {
			// check if fields has the annotation of property name
			PropertyName propertyName = field.getAnnotation(PropertyName.class);
			
			if(field.getName().equals("this$0")) {
				// skip this field
				continue;
			}
			
			// get name to read from
			String name;
			if(propertyName != null) {
				name = propertyName.value();
			} else {
				name = field.getName();
			}
			
			// read the value from underlying store
			Object value = this.get(name);
			if(value == null) {
				// there is nothing for us to do
				continue;
			}
			
			// set this value back to the instance
			ReflectionUtils.bindValue(field, instance, value);
		}
		
		return true;
	}
	
	public boolean saveFrom(Object instance) {
		if(instance == null) {
			return false;
		}
		
		Field[] fields = instance.getClass().getDeclaredFields();
		if(AssertUtils.isEmpty(fields)) {
			return true;
		}
		
		for(Field field : fields) {
			// set accessible
			field.setAccessible(true);
			
			// check if fields has the annotation of property name
			PropertyName propertyName = field.getAnnotation(PropertyName.class);
			
			// get name to save to
			String name;
			if(propertyName != null) {
				name = propertyName.value();
			} else {
				name = field.getName();
				
				// skip if field is transient
				if(ReflectionUtils.isTransient(field)) {
					continue;
				}
			}
			
			// read the fields value
			Object value = null;
			try {
				value = field.get(instance);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			// save the value
			if(value != null) {
				this.putNoSave(name, value.toString());
			} else {
				this.putNoSave(name, null);
			}
		}
		
		this.save();
		return true;
	}

	protected abstract void save();
	
	protected abstract void putNoSave(String key, String property);
	
}

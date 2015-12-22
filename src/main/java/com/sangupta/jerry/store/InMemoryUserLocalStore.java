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

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sangupta.jerry.util.AssertUtils;

/**
 * An implementation of the {@link UserLocalStore} that uses an in-memory
 * {@link ConcurrentHashMap} for storage. No data is written to disk ever.
 * 
 * @author sangupta
 *
 */
public class InMemoryUserLocalStore extends AbstractUserLocalStore {
	
	private final Map<String, Object> map = new ConcurrentHashMap<String, Object>();

	public InMemoryUserLocalStore() {
		super(null);
	}

	@Override
	public String get(String property) {
		if(AssertUtils.isEmpty(property)) {
			return null;
		}
		
		Object obj = this.map.get(property);
		if(obj == null) {
			return null;
		}
		
		return obj.toString();
	}

	@Override
	public Collection<String> getAllKeys() {
		return this.map.keySet();
	}

	@Override
	public void put(String property, Object value) {
		if(AssertUtils.isEmpty(property)) {
			return;
		}
		
		this.map.put(property, value);
	}

	@Override
	public void delete(String property) {
		if(AssertUtils.isEmpty(property)) {
			return;
		}
		
		this.map.remove(property);
	}

	@Override
	protected void save() {
		// do nothing
	}

	@Override
	protected void putNoSave(String property, Object value) {
		this.put(property, value);
	}

	@Override
	protected Object getValueObject(String property) {
		return this.map.get(property);
	}

}

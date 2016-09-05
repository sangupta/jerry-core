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

package com.sangupta.jerry.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A very simple implementation of a Multi-Map for use that does not implement
 * the {@link Map} interface. It is useful for situations where a very
 * light-weight implementation is needed, for collecting and iterating over
 * multiple objects that map to same key.
 *
 * This implementation is based on {@link ConcurrentHashMap}. For more advanced
 * implementations see Google Guava library.
 *
 * The implementation is not thread-safe multiple threads are adding values for
 * a key that is also being removed.
 *
 * @author sangupta
 *
 */
public class SimpleMultiMap<K, V> {

	/**
	 * The backing {@link ConcurrentHashMap}
	 */
	private final ConcurrentMap<K, List<V>> map = new ConcurrentHashMap<K, List<V>>();

	/**
	 * Return the number of keys stored in the map.
	 *
	 * @return the size of map
	 */
	public int size() {
		return this.map.size();
	}

	/**
	 * Check if there are any keys stored in this map or not
	 *
	 * @return <code>true</code> if no keys are present, <code>false</code>
	 *         otherwise
	 */
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	/**
	 * Check if the given key is present in the map or not
	 *
	 * @param key
	 *            the key being looked for
	 *
	 * @return <code>true</code> if key is present, <code>false</code> otherwise
	 */
	public boolean containsKey(K key) {
		return this.map.containsKey(key);
	}

	/**
	 * Return the list of all values stored against the key
	 *
	 * @param key
	 *            the key being looked for
	 *
	 * @return the list of all values, <code>null</code> otherwise
	 */
	public List<V> getValues(K key) {
		if(key == null) {
			return null;
		}
		
		return this.map.get(key);
	}
	
	/**
	 * Return the number of values stored against the given key.
	 * 
	 * @param key
	 *            the key to check for
	 * 
	 * @return the number of values stored, <code>zero</code> otherwise
	 */
	public int numValues(K key) {
		List<V> values = this.getValues(key);
		if(values == null) {
			return 0;
		}
		
		return values.size();
	}

	/**
	 * Store the given value object against the key.
	 *
	 * @param key
	 *            the key for mapping
	 *
	 * @param value
	 *            the value to be stored
	 */
	public void put(K key, V value) {
		List<V> values;
		if(this.map.containsKey(key)) {
			values = this.map.get(key);
			if(values != null) {
				values.add(value);
				return;
			}
		}

		// no value
		values = new ArrayList<V>();
		values.add(value);

		values = this.map.putIfAbsent(key, values);
		if(values == null) {
			// added successfully
			return;
		}

		values.add(value);
	}
	
	/**
	 * Remove and return all values associated with the given key.
	 *
	 * @param key
	 *            the key to remove
	 *
	 * @return the list of values stored against the removed key
	 */
	public List<V> remove(K key) {
		return this.map.remove(key);
	}

	/**
	 * Return a {@link Set} of all keys in this map.
	 *
	 * @return all the keys in the map
	 */
	public Set<K> keySet() {
		return this.map.keySet();
	}

	/**
	 * Clear all keys from this map.
	 *
	 */
	public void clear() {
		this.map.clear();
	}

	@Override
	public int hashCode() {
		return this.map.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.map.equals(obj);
	}

}

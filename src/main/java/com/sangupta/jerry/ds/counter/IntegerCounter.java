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
 
package com.sangupta.jerry.ds.counter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.sangupta.jerry.util.AssertUtils;

/**
 * An efficient way to count multiple named entities.
 *  
 * @author sangupta
 * @since 1.1
 */
public class IntegerCounter {

	/**
	 * Holds all counters for us.
	 * 
	 */
	private final ConcurrentMap<String, int[]> counterMap = new ConcurrentHashMap<String, int[]>();
	
	/**
     * Returns the current state of this instance as a {@link Map}. The map is a
     * snapshot in time, and the values may change without being reflected in
     * the returned instance.
     * 
     * @return a {@link Map} instance containing all the keys in the counter,
     *         and their respective values. Never returns a <code>null</code>.
     */
	public Map<String, Integer> asMap() {
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    
	    for(String key : counterMap.keySet()) {
	        map.put(key, this.get(key));
	    }
	    
	    return map;
	}
	
	/**
	 * Return the names of the counters present in this {@link IntegerCounter}.
	 * 
	 * @return the names of the counters if available, <code>null</code>
	 *         otherwise
	 */
	public Set<String> getCounterNames() {
		if(this.counterMap.isEmpty()) {
			return null;
		}
		
		return this.counterMap.keySet();
	}

	/**
	 * Get or create a new counter. The default value of the new counter will be
	 * <code>0</code>
	 * 
	 * @param name
	 *            the name of the counter
	 * 
	 * @return the current value of the counter
	 */
	public int get(String name) {
		if(AssertUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Counter name cannot be null");
		}
		
		int[] values = counterMap.get(name);
		if(values == null) {
			int[] olderValues = counterMap.putIfAbsent(name, new int[] { 0 });
			if(olderValues != null) {
				return olderValues[0];
			}
			
			return 0;
		}
		
		return values[0];
	}
	
	/**
	 * Get or create a new counter. The default value of the new counter will be
	 * <code>0</code>
	 * 
	 * @param name
	 *            the name of the counter
	 * 
	 * @param initialValue
	 *            the initial value to which the counter needs to be set
	 * 
	 * @return the current value of the counter
	 */
	public int get(final String name, final int initialValue) {
		if(AssertUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Counter name cannot be null");
		}
		
		int[] values = counterMap.get(name);
		if(values == null) {
			int[] olderValues = counterMap.putIfAbsent(name, new int[] { initialValue });
			if(olderValues != null) {
				return olderValues[0];
			}
			
			return initialValue;
		}
		
		return values[0];
	}
	
	/**
	 * Remove a counter and return its current value.
	 * 
	 * @param name
	 *            the name of the counter
	 * 
	 * @return the current value of the counter
	 */
	public int remove(String name) {
		if(AssertUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Counter name cannot be null");
		}
		
		int[] values = counterMap.remove(name);
		if(values == null) {
			return 0;
		}
		
		return values[0];
	}

	/**
	 * Increment the counter for the given name. If the counter does not exists,
	 * a new counter with a default value of <code>1</code> is created.
	 * 
	 * @param name
	 *            the name of the counter
	 * 
	 * @return the current value of the counter
	 */
	public int increment(String name) {
		if(AssertUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Counter name cannot be null");
		}
		
		int[] values = counterMap.get(name);
		if(values == null) {
			int[] olderValues = counterMap.putIfAbsent(name, new int[] { 1 });
			if(olderValues != null) {
				olderValues[0]++;
				return olderValues[0];
			}
			
			return 1;
		}
		
		values[0]++;
		return values[0];
	}
	
	/**
	 * Decrement the counter for the given name. If the counter does not exists,
	 * a new counter with a default value of <code>-1</code> is created.
	 * 
	 * @param name
	 *            the name of the counter
	 * 
	 * @return the current value of the counter
	 */
	public int decrement(String name) {
		if(AssertUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Counter name cannot be null");
		}
		
		int[] values = counterMap.get(name);
		if(values == null) {
			int[] olderValues = counterMap.putIfAbsent(name, new int[] { -1 });
			if(olderValues != null) {
				olderValues[0]--;
				return olderValues[0];
			}
			
			return -1;
		}
		
		values[0]--;
		return values[0];
	}
	
	/**
	 * Set the counter to the desired value.
	 * 
	 * @param name
	 *            the name of the counter to set
	 * 
	 * @param value
	 *            the value to which the counter needs to be set
	 * 
	 */
	public void set(String name, int value) {
		if(AssertUtils.isEmpty(name)) {
			throw new IllegalArgumentException("Counter name cannot be null");
		}
		
		int[] values = counterMap.get(name);
		if(values != null) {
			values[0] = value;
			return;
		}
		
		int[] olderValues = counterMap.putIfAbsent(name, new int[] { value });
		if(olderValues == null) {
			return;
		}
		
		olderValues[0] = value;
	}
	
}

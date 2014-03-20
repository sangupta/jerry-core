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

package com.sangupta.jerry.ds;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
	 * Get or create a new counter. The default value of the new counter will be
	 * <code>0</code>
	 * 
	 * @param name
	 *            the name of the counter
	 * 
	 * @return the current value of the counter
	 */
	public int get(String name) {
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
	 * Remove a counter and return its current value.
	 * 
	 * @param name
	 *            the name of the counter
	 * 
	 * @return the current value of the counter
	 */
	public int remove(String name) {
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
	
}

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

import java.util.Collections;

/**
 * A simple wrapper over an integer to be used in {@link Collections}
 * where the value can be changed as and when needed.
 * 
 * @author sangupta
 *
 */
public class MutableInt {

	private int value;
	
	/**
	 * Create a mutable integer with initial value of <code>0</code>.
	 * 
	 */
	public MutableInt() {
		
	}
	
	/**
	 * Create a mutable integer with the given initial value
	 * 
	 * @param value
	 */
	public MutableInt(int value) {
		this.value = value;
	}
	
	/**
	 * Get the current value
	 * 
	 * @return
	 */
	public int get() {
		return this.value;
	}
	
	/**
	 * Set the current value of this mutable integer to the given value
	 * 
	 * @param value
	 */
	public void set(int value) {
		this.value = value;
	}
	
	/**
	 * Change the value if the given value is more than the current value
	 * 
	 * @param value
	 */
	public void setIfMax(int value) {
		if(this.value < value) {
			this.value = value;
		}
	}
	
	/**
	 * Change the value if the given value is less than the current value
	 * 
	 * @param value
	 */
	public void setIfMin(int value) {
		if(this.value < value) {
			this.value = value;
		}
	}
	
}

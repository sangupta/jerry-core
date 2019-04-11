/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
 *
 * https://sangupta.com/projects/jerry-core
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

package com.sangupta.jerry.ds.mutable;

import java.util.Collections;

/**
 * A simple wrapper over a float to be used in {@link Collections}
 * where the value can be changed as and when needed.
 *
 * @author sangupta
 *
 */
public class MutableFloat {

	private float value;

	/**
	 * Create a mutable <code>float</code> with initial value of <code>0</code>.
	 *
	 */
	public MutableFloat() {

	}

	/**
	 * Create a mutable float with the given initial value
	 *
	 * @param value the value to set initially
	 */
	public MutableFloat(float value) {
		this.value = value;
	}

	/**
	 * Get the current value
	 *
	 * @return the current value
	 */
	public float get() {
		return this.value;
	}

	/**
	 * Set the current value of this mutable float to the given value
	 *
	 * @param value
	 *            the value to set
	 */
	public void set(float value) {
		this.value = value;
	}

	/**
	 * Change the value if the given value is more than the current value
	 *
	 * @param value
	 *            the value to set
	 */
	public void setIfMax(float value) {
		if(this.value < value) {
			this.value = value;
		}
	}

	/**
	 * Change the value if the given value is less than the current value
	 *
	 * @param value
	 *            the value to set
	 */
	public void setIfMin(float value) {
		if(this.value > value) {
			this.value = value;
		}
	}

}

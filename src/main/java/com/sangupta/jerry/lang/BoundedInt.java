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

package com.sangupta.jerry.lang;

import net.jcip.annotations.NotThreadSafe;

/**
 * An integer value that is bounded between the given minimum and maximum
 * values. The value when updated will always be in bounds. If a value
 * less than minimum is being set, the value will update to minimum. If a
 * value greater than maximum is being set, the value will update to maximum.
 *
 *
 * @author sangupta
 *
 * @since 2.3
 */
@NotThreadSafe
public class BoundedInt {

	/**
	 * The minimum value that this {@link BoundedInt} can go down to
	 */
	protected final int minimum;

	/**
	 * The maximum value that this {@link BoundedInt} can go up to
	 */
	protected final int maximum;

	/**
	 * The current value of this {@link BoundedInt}
	 */
	protected int current;

	/**
	 * Create a new instance for the given minimum and maximum values
	 *
	 * @param minValue
	 *            the minimum value that this {@link BoundedInt} can go up to
	 *
	 * @param maxValue
	 *            the maximum value that this {@link BoundedInt} can go up to
	 *
	 * @throws IllegalArgumentException
	 *             if maxValue is less than or equal to minValue
	 */
	private BoundedInt(int minValue, int maxValue) {
		if(maxValue <= minValue) {
			throw new IllegalArgumentException("Maximum value cannot be less than or equal to minimum value");
		}

		this.minimum = minValue;
		this.maximum = maxValue;
	}

	/**
	 * Create a new {@link BoundedInt} with the given current value in the given
	 * range.
	 *
	 * @param current
	 *            the current value to set to
	 *
	 * @param minValue
	 *            the minimum value that this {@link BoundedInt} can go up to
	 *
	 * @param maxValue
	 *            the maximum value that this {@link BoundedInt} can go up to
	 *
	 * @throws IllegalArgumentException
	 *             if maxValue is less than or equal to minValue
	 */
	public BoundedInt(int current, int minValue, int maxValue) {
		this(minValue, maxValue);
		this.set(current);
	}

	/**
	 * Updates the current value to the given new value. If the new value is
	 * less than minimum, the value is set to minimum. If the new value is
	 * greater than maximum, the value is set to maximum.
	 *
	 * @param newValue
	 *            the value to be set
	 *
	 * @return the value that this {@link BoundedInt} is now set to
	 */
	public int set(int newValue) {
		if(newValue < this.minimum) {
			this.current = this.minimum;
			return this.current;
		}

		if(this.maximum < newValue) {
			this.current = this.maximum;
			return this.current;
		}

		this.current = newValue;
		return this.current;
	}

	/**
	 * Update the current value to the given new value, only if the new value is
	 * in the range of this {@link BoundedInt}.
	 *
	 * @param newValue
	 *            the value to be set
	 *
	 * @return the value that this {@link BoundedInt} is now set to
	 */
	public int checkAndSet(int newValue) {
		if(newValue < this.minimum) {
			return this.current;
		}

		if(this.maximum < newValue) {
			return this.current;
		}

		this.current = newValue;
		return this.current;
	}

	/**
	 * Return the current value of this {@link BoundedInt}.
	 *
	 * @return the current value
	 */
	public int get() {
		return this.current;
	}

	/**
	 * Increment the current value by <code>ONE</code> and return the new
	 * current value.
	 *
	 * @return the value that this {@link BoundedInt} is now set to
	 */
	public int increment() {
		return this.set(this.current + 1);
	}

	/**
	 * Decrement the current value by <code>ONE</code> and return the new
	 * current value.
	 *
	 * @return the value that this {@link BoundedInt} is now set to
	 */
	public int decrement() {
		return this.set(this.current - 1);
	}

	/**
	 * Increase the value held by the given number
	 *
	 * @param additive
	 *            the number to be added to current number
	 *
	 * @return the value that this {@link BoundedInt} is now set to
	 */
	public int add(int additive) {
		return this.set(this.current + additive);
	}

	/**
	 * Decrease the value held by the given number
	 *
	 * @param subtractive
	 *            the number to be subtracted from current number
	 *
	 * @return the value that this {@link BoundedInt} is now set to
	 */
	public int subtract(int subtractive) {
		return this.set(this.current - subtractive);
	}

	/**
	 * Multiply the value held by the given number
	 *
	 * @param multiplier
	 *            the number by which to multiple the current number
	 *
	 * @return the value that this {@link BoundedInt} is now set to
	 */
	public int multiply(int multiplier) {
		return this.set(this.current * multiplier);
	}

	/**
	 * Divide the value held by the given number
	 *
	 * @param divisor
	 *            the number by which to divide the current number
	 *
	 * @return the value that this {@link BoundedInt} is now set to
	 */
	public int divide(int divisor) {
		return this.set(this.current / divisor);
	}

}

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
 
package com.sangupta.jerry.util;

/**
 * Utility class to work with bits and bit operations.
 * 
 * @author sangupta
 *
 */
public class BitUtils {
	
	private static final int LEAST_BIT = 0;

	private static final int MAX_BIT_INT = 31;
	
	private static final int MAX_BIT_LONG = 63;

	/**
	 * Check if a bit is set in an int value.
	 * 
	 * @param value
	 *            the integer value to look in
	 * 
	 * @param bit
	 *            the bit number to look for
	 * 
	 * @return <code>true</code> if bit is set, <code>false</code> otherwise
	 */
	public static boolean isBitSet(int value, int bit) {
	    CheckUtils.checkArgument(bit >= LEAST_BIT, "Bit to set cannot be less than zero");
	    CheckUtils.checkArgument(bit <= MAX_BIT_INT, "Bit to set cannot be greater than " + MAX_BIT_INT);
	    int mask = 1 << bit;
	    return (value & mask) != 0;
	}
	
	/**
	 * Set a bit in an int value
	 * 
	 * @param value
	 *            the integer value to operate on
	 * 
	 * @param bit
	 *            the bit to set
	 * 
	 * @return <code>true</code> if the bit was modified, <code>false</code>
	 *         otherwise
	 */
	public static int setBit(int value, int bit) {
		CheckUtils.checkArgument(bit >= LEAST_BIT, "Bit to set cannot be less than zero");
	    CheckUtils.checkArgument(bit <= MAX_BIT_INT, "Bit to set cannot be greater than " + MAX_BIT_INT);
		int mask = 1 << bit;
		return value | mask;
	}

	/**
	 * Clear a bit in an int value
	 * 
	 * @param value
	 *            the integer value to operate on
	 * 
	 * @param bit
	 *            the bit to clear
	 * 
	 * @return <code>true</code> if bit was modified, <code>false</code>
	 *         otherwise
	 */
	public static int clearBit(int value, int bit) {
		CheckUtils.checkArgument(bit >= LEAST_BIT, "Bit to set cannot be less than zero");
	    CheckUtils.checkArgument(bit <= MAX_BIT_INT, "Bit to set cannot be greater than " + MAX_BIT_INT);
		int mask = ~setBit(0, bit);
		return value & mask;
	}

	/**
	 * Check if a bit is set in a long value
	 * 
	 * @param value
	 *            the long value to operate on
	 * 
	 * @param bit
	 *            the bit to check
	 * 
	 * @return <code>true</code> if bit is set, <code>false</code>
	 *         otherwise
	 */
	public static boolean isBitSet(long value, int bit) {
	    CheckUtils.checkArgument(bit >= LEAST_BIT, "Bit to set cannot be less than zero");
	    CheckUtils.checkArgument(bit <= MAX_BIT_LONG, "Bit to set cannot be greater than " + MAX_BIT_LONG);
	    long mask = 1l << bit;
	    return ((long) (value & mask)) != 0l;
	}
	
	/**
	 * Set a bit in a long value
	 * 
	 * @param value
	 *            the long value to operate on
	 * 
	 * @param bit
	 *            the bit to set
	 * 
	 * @return <code>true</code> if bit was modified, <code>false</code>
	 *         otherwise
	 */
	public static long setBit(long value, int bit) {
		CheckUtils.checkArgument(bit >= LEAST_BIT, "Bit to set cannot be less than zero");
	    CheckUtils.checkArgument(bit <= MAX_BIT_LONG, "Bit to set cannot be greater than " + MAX_BIT_LONG);
		long mask = 1L << bit;
		return value | mask;
	}
	
	/**
	 * Clear a bit in a long value
	 * 
	 * @param value
	 *            the long value to operate on
	 * 
	 * @param bit
	 *            the bit to clear
	 * 
	 * @return <code>true</code> if bit was modified, <code>false</code>
	 *         otherwise
	 */
	public static long clearBit(long value, int bit) {
		CheckUtils.checkArgument(bit >= LEAST_BIT, "Bit to set cannot be less than zero");
	    CheckUtils.checkArgument(bit <= MAX_BIT_LONG, "Bit to set cannot be greater than " + MAX_BIT_LONG);
		long mask = ~setBit(0L, bit);
		return value & mask;
	}
}

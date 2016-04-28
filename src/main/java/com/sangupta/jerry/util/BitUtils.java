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
	
	/**
	 * Find the index of the highest bit that is set. Bits on the left are
	 * considered the highest bit.
	 * 
	 * @param byteValue
	 *            the value to check
	 *            
	 * @return the index of the highest bit set, or <code>-1</code> if none is
	 *         set
	 */
	public static int getHighestSetBitIndex(byte byteValue) {
		int value = byteValue;
		if(byteValue < 0) {
			value = byteValue & 0xFF;
		}
		
		if(value == 0) {
			return -1;
		}
		
		for(int index = 7; index >= 0; index--) {
			int bitValue = 1 << index;
			if((value & bitValue) == bitValue) {
				return index;
			}
		}
		
		return -1;
	}
	
	/**
	 * Find the index of the lowest bit that is set. Bits on the left are
	 * considered the highest bit.
	 * 
	 * @param byteValue
	 *            the value to check
	 * 
	 * @return the index of the lowest bit set, or <code>-1</code> if none is
	 *         set
	 */
	public static int getLowestSetBitIndex(byte byteValue) {
		int value = byteValue;
		if(byteValue < 0) {
			value = byteValue & 0xFF;
		}
		
		if(value == 0) {
			return -1;
		}
		
		for(int index = 0; index < 8; index++) {
			int bitValue = 1 << index;
			if((value & bitValue) == bitValue) {
				return index;
			}
		}
		
		return -1;
	}
	
	/**
	 * Find the index of the highest bit that is set. Bits on the left are
	 * considered the highest bit.
	 * 
	 * @param shortValue
	 *            the value to check
	 *            
	 * @return the index of the highest bit set, or <code>-1</code> if none is
	 *         set
	 */
	public static int getHighestSetBitIndex(short shortValue) {
		int value = shortValue;
		if(shortValue < 0) {
			value = shortValue & 0xFFFF;
		}
		
		if(value == 0) {
			return -1;
		}
		
		for(int index = 15; index >= 0; index--) {
			int bitValue = 1 << index;
			if((value & bitValue) == bitValue) {
				return index;
			}
		}
		
		return -1;
	}
	
	/**
	 * Find the index of the lowest bit that is set. Bits on the left are
	 * considered the highest bit.
	 * 
	 * @param shortValue
	 *            the value to check
	 * 
	 * @return the index of the lowest bit set, or <code>-1</code> if none is
	 *         set
	 */
	public static int getLowestSetBitIndex(short shortValue) {
		int value = shortValue;
		if(shortValue < 0) {
			value = shortValue & 0xFFFF;
		}
		
		if(value == 0) {
			return -1;
		}
		
		for(int index = 0; index < 16; index++) {
			int bitValue = 1 << index;
			if((value & bitValue) == bitValue) {
				return index;
			}
		}
		
		return -1;
	}
	
	/**
	 * Find the index of the highest bit that is set. Bits on the left are
	 * considered the highest bit.
	 * 
	 * @param intValue
	 *            the value to check
	 * 
	 * @return the index of the highest bit set, or <code>-1</code> if none is
	 *         set
	 */
	public static int getHighestSetBitIndex(int intValue) {
		if(intValue < 0) {
			intValue = intValue & 0xFFFFFFFF;
		}
		
		if(intValue == 0) {
			return -1;
		}
		
		for(int index = 31; index >= 0; index--) {
			int bitValue = 1 << index;
			if((intValue & bitValue) == bitValue) {
				return index;
			}
		}
		
		return -1;
	}
	
	/**
	 * Find the index of the lowest bit that is set. Bits on the left are
	 * considered the highest bit.
	 * 
	 * @param intValue
	 *            the value to check
	 * 
	 * @return the index of the lowest bit set, or <code>-1</code> if none is
	 *         set
	 */
	public static int getLowestSetBitIndex(int intValue) {
		int value = intValue;
		if(intValue < 0) {
			value = intValue & 0xFFFFFFFF;
		}
		
		if(value == 0) {
			return -1;
		}
		
		for(int index = 0; index < 32; index++) {
			int bitValue = 1 << index;
			if((value & bitValue) == bitValue) {
				return index;
			}
		}
		
		return -1;
	}
	
	/**
	 * Find the index of the highest bit that is set. Bits on the left are
	 * considered the highest bit.
	 * 
	 * @param longValue
	 *            the value to check
	 * 
	 * @return the index of the highest bit set, or <code>-1</code> if none is
	 *         set
	 */
	public static int getHighestSetBitIndex(long longValue) {
		if(longValue < 0) {
			longValue = longValue & 0xFFFFFFFF;
		}
		
		if(longValue == 0) {
			return -1;
		}
		
		for(int index = 63; index >= 0; index--) {
			long bitValue = 1l << index;
			if((longValue & bitValue) == bitValue) {
				return index;
			}
		}
		
		return -1;
	}
	
	/**
	 * Find the index of the lowest bit that is set. Bits on the left are considered
	 * the highest bit.
	 *  
	 * @param longValue the value to check 
	 * 
	 * @return the index of the lowest bit set, or <code>-1</code> if none is
	 *         set
	 */
	public static int getLowestSetBitIndex(long longValue) {
		if(longValue < 0) {
			longValue = longValue & 0xFFFFFFFF;
		}
		
		if(longValue == 0) {
			return -1;
		}
		
		for(int index = 0; index < 64; index++) {
			long bitValue = 1l << index;
			if((longValue & bitValue) == bitValue) {
				return index;
			}
		}
		
		return -1;
	}
}
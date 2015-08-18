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
 
package com.sangupta.jerry.ds.bitarray;

import static java.lang.Math.abs;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;

import com.sangupta.jerry.util.BitUtils;
import com.sangupta.jerry.util.ByteArrayUtils;

import net.jcip.annotations.NotThreadSafe;

/**
 * A fast bit-set implementation that allows direct access to data
 * property so that it can be easily serialized.
 * 
 * @author sangupta
 * @since 1.7
 */
@NotThreadSafe
public class FastBitArray implements BitArray {

	/**
	 * The data-set
	 */
	final long[] data;
	
	/**
	 * The current bit count
	 */
	private int bitCount;

	/**
	 * Construct an instance of the {@link FastBitArray} that can hold
	 * the given number of bits
	 * 
	 * @param bits the number of bits this instance can hold 
	 */
	public FastBitArray(long bits) {
		this(new long[checkedCast(divide(bits, 64, RoundingMode.CEILING))]);
	}

	/**
	 * Construct an implementation with the given long[] array
	 * 
	 * @param data
	 */
	public FastBitArray(long[] data) {
		if(data == null || data.length == 0) {
			throw new IllegalArgumentException("Data is either null or zero-length");
		}
		
		this.data = data;
		int bitCount = 0;
		for (long value : data) {
			bitCount += Long.bitCount(value);
		}
		
		this.bitCount = bitCount;
	}

	/**
	 * Number of bits
	 * 
	 * @return total number of bits allocated
	 */
	public int bitSize() {
		return data.length * Long.SIZE;
	}
	
	/**
	 * Number of bytes
	 * 
	 * @return total number of bytes being used
	 * 
	 */
	public int numBytes() {
		return this.bitSize() >>> 3;
	}
	
	/**
	 * Number of set bits (1s)
	 * 
	 * @return the number of set bits
	 */
	public int bitCount() {
		return this.bitCount;
	}

	/**
	 * Copy the bitset.
	 * 
	 * @return a new {@link FastBitArray} that is exactly in the same state as
	 *         this
	 */
	public FastBitArray copy() {
		return new FastBitArray(data.clone());
	}

	/**
	 * Combines the two BitArrays using bitwise OR.
	 * 
	 * @param array
	 */
	void putAll(FastBitArray array) {
		if(array == null) {
			throw new IllegalArgumentException("Array to be combined with cannot be null");
		}
		
		if(this.data.length != array.data.length) {
			throw new IllegalArgumentException("Array to be combined with must be of equal length");
		}
		
		bitCount = 0;
		
		for (int i = 0; i < data.length; i++) {
			data[i] |= array.data[i];
			bitCount += Long.bitCount(data[i]);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof FastBitArray) {
			FastBitArray bitArray = (FastBitArray) o;
			return Arrays.equals(data, bitArray.data);
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(data);
	}

	@Override
	public void close() throws IOException {
		// nothing to do - we are in-memory
	}

	@Override
	public boolean getBit(int index) {
		return (data[index >> 6] & (1L << index)) != 0;
	}

	@Override
	public boolean setBit(int index) {
		if (!this.getBit(index)) {
			data[index >> 6] |= (1L << index);
			bitCount++;
			return true;
		}
		
		return false;
	}

	@Override
	public void clear() {
		Arrays.fill(this.data, 0);
	}

	@Override
	public void clearBit(int index) {
		if (!this.getBit(index)) {
			return;
		}
		
		data[index >> 6] &= ~(1L << index);
	}

	@Override
	public boolean setBitIfUnset(int index) {
		return this.setBit(index);
	}

	@Override
	public void or(BitArray array) {
		if(array == null) {
			throw new IllegalArgumentException("Array to be combined with cannot be null");
		}
		
		if(this.data.length != array.bitSize()) {
			throw new IllegalArgumentException("Array to be combined with must be of equal length");
		}
		
		if(array instanceof FastBitArray) {
			FastBitArray fastArray = (FastBitArray) array;
			for (int i = 0; i < data.length; i++) {
				data[i] |= fastArray.data[i];
			}
			
			return;
		}
		
		// work on the byte-array
		byte[] bytes = array.toByteArray();
		for(int index = 0; index < this.data.length; index++) {
			this.data[index] |= ByteArrayUtils.readLong(bytes, index << 3);
		}
	}

	@Override
	public void and(BitArray array) {
		if(array == null) {
			throw new IllegalArgumentException("Array to be combined with cannot be null");
		}
		
		if(this.data.length != array.bitSize()) {
			throw new IllegalArgumentException("Array to be combined with must be of equal length");
		}
		
		if(array instanceof FastBitArray) {
			FastBitArray fastArray = (FastBitArray) array;
			for (int i = 0; i < data.length; i++) {
				data[i] &= fastArray.data[i];
			}
			
			return;
		}
		
		// work on the byte-array
		byte[] bytes = array.toByteArray();
		for(int index = 0; index < this.data.length; index++) {
			this.data[index] &= ByteArrayUtils.readLong(bytes, index << 3);
		}
	}
	
	@Override
	public byte[] toByteArray() {
		byte[] bytes = new byte[this.numBytes()];
		
		// now for each long - put the bytes in the right order
		for(int index = 0; index < this.data.length; index++) {
			ByteArrayUtils.writeLong(bytes, this.data[index], index << 3);
		}
		
		return bytes;
	}
	
	/**
	 * Returns the {@code int} value that is equal to {@code value}, if
	 * possible.
	 * 
	 * @param value
	 *            any value in the range of the {@code int} type
	 * 
	 * @return the {@code int} value that equals {@code value}
	 * 
	 * @throws IllegalArgumentException
	 *             if {@code value} is greater than {@link Integer#MAX_VALUE} or
	 *             less than {@link Integer#MIN_VALUE}
	 */
	public static int checkedCast(long value) {
		int result = (int) value;
		if (result != value) {
			// don't use checkArgument here, to avoid boxing
			throw new IllegalArgumentException("Out of range: " + value);
		}
		return result;
	}
	
	/**
	 * Returns the result of dividing {@code p} by {@code q}, rounding using the
	 * specified {@code RoundingMode}.
	 * 
	 * 
	 * @param numerator
	 *            the value to be divided
	 * 
	 * @param denominator
	 *            the value with which to divide
	 * 
	 * @param mode
	 *            the {@link RoundingMode} to use
	 * 
	 * @return the division result thus rounded
	 * 
	 * @throws ArithmeticException
	 *             if {@code q == 0}, or if {@code mode == UNNECESSARY} and
	 *             {@code a} is not an integer multiple of {@code b}
	 * 
	 */
	@SuppressWarnings("fallthrough")
	public static long divide(long numerator, long denominator, RoundingMode mode) {
		if(mode == null) {
			throw new IllegalArgumentException("Rounding mode cannot be null");
		}
		
		long div = numerator / denominator; // throws if q == 0
		long rem = numerator - denominator * div; // equals p % q

		if (rem == 0) {
			return div;
		}

		/*
		 * Normal Java division rounds towards 0, consistently with
		 * RoundingMode.DOWN. We just have to deal with the cases where rounding
		 * towards 0 is wrong, which typically depends on the sign of p / q.
		 * 
		 * signum is 1 if p and q are both nonnegative or both negative, and -1
		 * otherwise.
		 */
		int signum = 1 | (int) ((numerator ^ denominator) >> (Long.SIZE - 1));
		boolean increment;
		switch (mode) {
		case UNNECESSARY:
			checkRoundingUnnecessary(rem == 0);
			// fall through
		case DOWN:
			increment = false;
			break;
		case UP:
			increment = true;
			break;
		case CEILING:
			increment = signum > 0;
			break;
		case FLOOR:
			increment = signum < 0;
			break;
		case HALF_EVEN:
		case HALF_DOWN:
		case HALF_UP:
			long absRem = abs(rem);
			long cmpRemToHalfDivisor = absRem - (abs(denominator) - absRem);
			// subtracting two nonnegative longs can't overflow
			// cmpRemToHalfDivisor has the same sign as compare(abs(rem), abs(q)
			// / 2).
			if (cmpRemToHalfDivisor == 0) { // exactly on the half mark
				increment = (mode == HALF_UP | (mode == HALF_EVEN & (div & 1) != 0));
			} else {
				increment = cmpRemToHalfDivisor > 0; // closer to the UP value
			}
			break;
		default:
			throw new AssertionError();
		}
		return increment ? div + signum : div;
	}

	static void checkRoundingUnnecessary(boolean condition) {
		if (!condition) {
			throw new ArithmeticException(
					"mode was UNNECESSARY, but rounding was necessary");
		}
	}

	@Override
	public int getHighestBitSet() {
		int length = this.data.length;
		for(int index = length - 1; index >= 0; index--) {
			long value = this.data[index];
			if(value != 0) {
				// this is the highest set bit
				if(index > 0) {
					return (index * 64) + BitUtils.getHighestSetBitIndex(value);
				}
				
				return BitUtils.getHighestSetBitIndex(value);
			}
		}
		
		// not found
		return -1;
	}

	@Override
	public int getLowestBitSet() {
		int length = this.data.length;
		for(int index = 0; index < length; index++) {
			long value = this.data[index];
			if(value != 0) {
				// this is the highest set bit
				if(index > 0) {
					return (index * 64) + BitUtils.getLowestSetBitIndex(value);
				}
				
				return BitUtils.getLowestSetBitIndex(value);
			}
		}
		
		// not found
		return -1;
	}

}

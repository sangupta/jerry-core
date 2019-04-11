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

package com.sangupta.jerry.ds.bitarray;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;

import com.sangupta.jerry.util.BitUtils;
import com.sangupta.jerry.util.ByteArrayUtils;
import com.sangupta.jerry.util.NumberUtils;

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
		this(new long[NumberUtils.checkedCast(NumberUtils.divide(bits, Long.SIZE, RoundingMode.CEILING))]);
	}

	/**
	 * Construct an implementation with the given long[] array
	 *
	 * @param data
	 *            the long[] array from which to construct the bit array
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

		if(this.numBytes() != array.numBytes()) {
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

		if(this.numBytes() != array.numBytes()) {
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

	@Override
	public int getHighestBitSet() {
		int length = this.data.length;
		for(int index = length - 1; index >= 0; index--) {
			long value = this.data[index];
			if(value != 0) {
				// this is the highest set bit
				if(index > 0) {
					return (index * Long.SIZE) + BitUtils.getHighestSetBitIndex(value);
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
					return (index * Long.SIZE) + BitUtils.getLowestSetBitIndex(value);
				}

				return BitUtils.getLowestSetBitIndex(value);
			}
		}

		// not found
		return -1;
	}

	@Override
	public int getNextSetBit(int fromIndex) {
	    // check the current long-value
	    int current = fromIndex / Long.SIZE;
	    int bit = BitUtils.getHighestSetBitIndex(this.data[current]);
	    int index = (current * Long.SIZE) + bit;

	    if(index < current) {
	        // the value lies in long values ahead in array
	        for(int loop = current + 1; loop < this.data.length; loop++) {
	            long value = this.data[loop];
	            if(value != 0) {
	                return BitUtils.getLowestSetBitIndex(value);
	            }
	        }

	        // no more data
	        return -1;
	    }

	    // the bit must be higher within this long value
	    for(int loop = fromIndex + 1; loop < fromIndex + Long.SIZE; loop++) {
	        if(this.getBit(loop)) {
	            return loop;
	        }
	    }

	    return -1;
	}
}

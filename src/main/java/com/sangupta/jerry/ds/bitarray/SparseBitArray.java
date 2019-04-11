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

import com.sangupta.jerry.util.BitUtils;
import com.sangupta.jerry.util.ByteArrayUtils;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class SparseBitArray implements BitArray {

	/**
	 * Bits per element - we are using <code>long</code> for elements and thus 64
	 * bits
	 */
	private final int BITS_PER_ELEMENT = Long.SIZE;

	/**
	 * Number of bytes per element
	 */
	private final int BYTES_PER_ELEMENT = BITS_PER_ELEMENT / 8;

	/**
	 * Number of buckets that we need to initialize
	 */
	private final int numBuckets;

	/**
	 * Bits held in one bucket
	 */
	private final int bitsPerBucket;

	/**
	 * Number of elements or long values per bucket
	 */
	private final int elementsNeededPerBucket;

	/**
	 * Maximum index that we can accomodate in this sparse array
	 */
	private final int maxIndex;

	/**
	 * The backing array
	 */
	private final long[][] array;

	/**
	 * Constructor
	 * 
	 * @param numBuckets number of buckets to start with
	 * 
	 * @param bucketSize size of each bucket
	 */
	public SparseBitArray(int numBuckets, int bucketSize) {
		this.numBuckets = numBuckets;
		this.bitsPerBucket = bucketSize;

		this.maxIndex = bucketSize * numBuckets;

		int elements = this.bitsPerBucket / BITS_PER_ELEMENT;
		if (this.bitsPerBucket % BITS_PER_ELEMENT > 0) {
			elements++;
		}
		this.elementsNeededPerBucket = elements;

		this.array = new long[this.numBuckets][];
	}

	@Override
	public void close() throws IOException {
		// nothing to do here
	}

	@Override
	public boolean getBit(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index is out of range: " + index);
		}

		if (index > this.maxIndex) {
			throw new IndexOutOfBoundsException("Index is out of range: " + index);
		}

		int bucket = index / this.bitsPerBucket;
		if (this.array[bucket] == null) {
			return false;
		}

		int subIndex = index % this.bitsPerBucket;
		int element = subIndex / BITS_PER_ELEMENT;
		int bit = subIndex % BITS_PER_ELEMENT;

		long[] subArray = this.array[bucket];
		long value = subArray[element];

		return BitUtils.isBitSet(value, bit);
	}

	@Override
	public boolean setBit(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index is out of range: " + index);
		}

		if (index > this.maxIndex) {
			throw new IndexOutOfBoundsException("Index is out of range: " + index);
		}

		int bucket = index / this.bitsPerBucket;
		if (this.array[bucket] == null) {
			this.array[bucket] = new long[this.elementsNeededPerBucket];
		}

		int subIndex = index % this.bitsPerBucket;

		int element = subIndex / BITS_PER_ELEMENT;
		int bit = subIndex % BITS_PER_ELEMENT;

		long[] subArray = this.array[bucket];
		long value = subArray[element];
		long updated = BitUtils.setBit(value, bit);

		if (value != updated) {
			subArray[element] = updated;
			return true;
		}

		return false;
	}

	@Override
	public void clear() {
		for (int bucket = 0; bucket < this.numBuckets; bucket++) {
			this.array[bucket] = null;
		}
	}

	@Override
	public void clearBit(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index is out of range: " + index);
		}

		if (index > this.maxIndex) {
			throw new IndexOutOfBoundsException("Index is out of range: " + index);
		}

		int bucket = index / this.bitsPerBucket;
		if (this.array[bucket] == null) {
			return;
		}

		int subIndex = index % this.bitsPerBucket;
		int element = subIndex / BITS_PER_ELEMENT;
		int bit = subIndex % BITS_PER_ELEMENT;

		long[] subArray = this.array[bucket];
		long value = subArray[element];
		long updated = BitUtils.clearBit(value, bit);

		this.array[bucket][element] = updated;
	}

	@Override
	public boolean setBitIfUnset(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index is out of range: " + index);
		}

		if (index > this.maxIndex) {
			throw new IndexOutOfBoundsException("Index is out of range: " + index);
		}

		int bucket = index / this.bitsPerBucket;
		if (this.array[bucket] == null) {
			this.array[bucket] = new long[this.elementsNeededPerBucket];
		}

		int subIndex = index % this.bitsPerBucket;

		int element = subIndex / BITS_PER_ELEMENT;
		int bit = subIndex % BITS_PER_ELEMENT;

		long[] subArray = this.array[bucket];
		long value = subArray[element];
		long updated = BitUtils.setBit(value, bit);

		if (value != updated) {
			subArray[element] = updated;
			return true;
		}

		return false;
	}

	@Override
	public void or(BitArray bitArray) {
		if (array == null) {
			throw new IllegalArgumentException("Array to be combined with cannot be null");
		}

		if (bitArray instanceof SparseBitArray) {
			SparseBitArray second = (SparseBitArray) bitArray;

			if (this.numBuckets != second.numBuckets) {
				throw new IllegalArgumentException("Array to be combined with must be of equal length");
			}

			if (this.elementsNeededPerBucket != second.elementsNeededPerBucket) {
				throw new IllegalArgumentException("Array to be combined with must be of equal length");
			}

			for (int bucket = 0; bucket < this.numBuckets; bucket++) {
				long[] subArray1 = this.array[bucket];
				long[] subArray2 = second.array[bucket];

				if (subArray2 == null) {
					// nothing to do
					continue;
				}

				if (subArray1 == null) {
					this.array[bucket] = subArray2.clone();
					continue;
				}

				for (int element = 0; element < this.elementsNeededPerBucket; element++) {
					subArray1[element] = subArray1[element] | subArray2[element];
				}
			}

			return;
		}

		// TODO: do for other arrays
	}

	@Override
	public void and(BitArray bitArray) {
		if (array == null) {
			throw new IllegalArgumentException("Array to be combined with cannot be null");
		}

		if (bitArray instanceof SparseBitArray) {
			SparseBitArray second = (SparseBitArray) bitArray;

			if (this.numBuckets != second.numBuckets) {
				throw new IllegalArgumentException("Array to be combined with must be of equal length");
			}

			if (this.elementsNeededPerBucket != second.elementsNeededPerBucket) {
				throw new IllegalArgumentException("Array to be combined with must be of equal length");
			}

			for (int bucket = 0; bucket < this.numBuckets; bucket++) {
				long[] subArray1 = this.array[bucket];
				long[] subArray2 = second.array[bucket];

				if (subArray2 == null) {
					// nothing to do
					continue;
				}

				if (subArray1 == null) {
					this.array[bucket] = subArray2.clone();
					continue;
				}

				for (int element = 0; element < this.elementsNeededPerBucket; element++) {
					subArray1[element] = subArray1[element] & subArray2[element];
				}
			}

			return;
		}

		// TODO: do for other arrays
	}

	@Override
	public int bitSize() {
		return this.maxIndex;
	}

	@Override
	public int numBytes() {
		return this.numBuckets * this.elementsNeededPerBucket * (this.BITS_PER_ELEMENT / BYTES_PER_ELEMENT);
	}

	@Override
	public byte[] toByteArray() {
		byte[] bytes = new byte[this.numBytes()];

		int bucketIndex = 0;
		for (int bucket = 0; bucket < this.numBuckets; bucket++) {
			bucketIndex = bucket * this.elementsNeededPerBucket * BYTES_PER_ELEMENT;

			long[] subArray = this.array[bucket];
			if (subArray == null) {
				for (int element = 0; element < this.elementsNeededPerBucket; element++) {
					ByteArrayUtils.writeLong(bytes, 0, bucketIndex + element << 3);
				}

				continue;
			}

			for (int element = 0; element < this.elementsNeededPerBucket; element++) {
				ByteArrayUtils.writeLong(bytes, subArray[element], bucketIndex + element << BYTES_PER_ELEMENT);
			}
		}

		return bytes;
	}

	@Override
	public int getHighestBitSet() {
		for (int bucket = this.numBuckets - 1; bucket >= 0; bucket--) {
			long[] subArray = this.array[bucket];
			if (subArray == null) {
				continue;
			}

			for (int element = subArray.length - 1; element >= 0; element--) {
				long value = subArray[element];
				if (value != 0) {
					// we found an element
					int index = BitUtils.getHighestSetBitIndex(value);
					return (bucket * this.elementsNeededPerBucket * BITS_PER_ELEMENT) + (element * BITS_PER_ELEMENT)
							+ index;
				}
			}
		}

		return -1;
	}

	@Override
	public int getLowestBitSet() {
		for (int bucket = 0; bucket < this.numBuckets; bucket++) {
			long[] subArray = this.array[bucket];
			if (subArray == null) {
				continue;
			}

			for (int element = 0; element < this.elementsNeededPerBucket; element++) {
				long value = subArray[element];
				if (value != 0) {
					// we found an element
					int index = BitUtils.getLowestSetBitIndex(value);
					return (bucket * this.elementsNeededPerBucket * BITS_PER_ELEMENT) + (element * BITS_PER_ELEMENT)
							+ index;
				}
			}
		}

		return -1;
	}

	@Override
	public int getNextSetBit(int fromIndex) {
		return 0;
	}

}

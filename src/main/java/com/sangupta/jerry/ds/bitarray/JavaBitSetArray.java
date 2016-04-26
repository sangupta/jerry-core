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

import java.io.IOException;
import java.util.BitSet;

import com.sangupta.jerry.util.BitUtils;

import net.jcip.annotations.NotThreadSafe;

/**
 * A {@link BitArray} implementation that uses the standard Java {@link BitSet}
 * as the underlying implementation.
 * 
 * @author sangupta
 * @since 1.7
 */
@NotThreadSafe
public class JavaBitSetArray implements BitArray {
	
	final BitSet bitSet;
	
	final int size;
	
	public JavaBitSetArray(int numBits) {
		this.bitSet = new BitSet(numBits);
		this.size = this.bitSet.size();
	}

	@Override
	public void clear() {
		this.bitSet.clear();
	}

	@Override
	public boolean getBit(int index) {
		return this.bitSet.get(index);
	}

	@Override
	public boolean setBit(int index) {
		this.bitSet.set(index);
		return true;
	}

	@Override
	public void clearBit(int index) {
		this.bitSet.clear(index);
	}

	@Override
	public boolean setBitIfUnset(int index) {
		if(!this.bitSet.get(index)) {
			return this.setBit(index);
		}
		
		return false;
	}

	@Override
	public void or(BitArray bitArray) {
		if(bitArray == null) {
			throw new IllegalArgumentException("BitArray to OR with cannot be null");
		}
		
		if(this.size != bitArray.bitSize()) {
			throw new IllegalArgumentException("BitArray to OR with is of different length");
		}
		
		BitSet bs = BitSet.valueOf(bitArray.toByteArray());
		this.bitSet.or(bs);
	}

	@Override
	public void and(BitArray bitArray) {
		if(bitArray == null) {
			throw new IllegalArgumentException("BitArray to OR with cannot be null");
		}
		
		if(this.size != bitArray.bitSize()) {
			throw new IllegalArgumentException("BitArray to OR with is of different length");
		}
		
		BitSet bs = BitSet.valueOf(bitArray.toByteArray());
		this.bitSet.and(bs);
	}

	@Override
	public int bitSize() {
		return this.size;
	}
	
	@Override
	public int numBytes() {
		return this.bitSize() >>> 8;
	}
	
	@Override
	public byte[] toByteArray() {
		return this.bitSet.toByteArray();
	}

	@Override
	public void close() throws IOException {
		// do nothing
	}

	@Override
	public int getHighestBitSet() {
		byte[] bytes = this.toByteArray();
		for(int index = bytes.length - 1; index >= 0; index--) {
			byte bite = bytes[index];
			if(bite != 0) {
				// this is the highest set bit
				if(index > 0) {
					return (index * 8) + BitUtils.getHighestSetBitIndex(bite);
				}
				
				return BitUtils.getHighestSetBitIndex(bite);
			}
		}
		
		// not found
		return -1;
	}

	@Override
	public int getLowestBitSet() {
		byte[] bytes = this.toByteArray();
		for(int index = 0; index < bytes.length; index++) {
			byte bite = bytes[index];
			if(bite != 0) {
				// this is the highest set bit
				if(index > 0) {
					return (index * 8) + BitUtils.getLowestSetBitIndex(bite);
				}
				
				return BitUtils.getLowestSetBitIndex(bite);
			}
		}
		
		// not found
		return -1;
	}

	@Override
	public int getNextSetBit(int fromIndex) {
	    return this.bitSet.nextSetBit(fromIndex);
	}
	
}

package com.sangupta.jerry.ds.bitarray;

import java.io.IOException;
import java.util.BitSet;

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

}

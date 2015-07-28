package com.sangupta.jerry.ds.bitarray;

import java.io.Closeable;

/**
 * A contract for all implementations of bit-arrays. This provides
 * specific methods that will be needed for working with bloom filters.
 *  
 * @author sangupta
 * @since 1.7
 */
public interface BitArray extends Closeable {

	/**
	 * Get the bit at index
	 * 
	 * @param index
	 *            the index of the bit in the array
	 * 
	 * @return <code>true</code> if the bit is set, <code>false</code> otherwise
	 */
	public boolean getBit(int index);
	
	/**
	 * Set the bit at index
	 * 
	 * @param index
	 *            the index of the bit in the array
	 * 
	 * @return <code>true</code> if the bit was updated, <code>false</code>
	 *         otherwise.
	 * 
	 */
	public boolean setBit(int index);
	
	/**
	 * Clear all bits in the array.
	 * 
	 */
	public void clear();
	
	/**
	 * Clear a given bit at the index.
	 * 
	 * @param index
	 *            the index of the bit in the array
	 */
	public void clearBit(int index);
	
	/**
	 * Set the bit at index if the bit is unset.
	 * 
	 * @param index
	 *            the index of the bit in the array
	 * 
	 * @return <code>true</code> if the bit was updated, <code>false</code>
	 *         otherwise.
	 */
	public boolean setBitIfUnset(int index);

	/**
	 * Do a Boolean OR with the second {@link BitArray}.
	 * 
	 * @param bitArray
	 *            the bitArray to OR with
	 */
	public void or(BitArray bitArray);
	
	/**
	 * Do a Boolean AND with the second {@link BitArray}.
	 * 
	 * @param bitArray
	 *            the bitArray to AND with
	 */
	public void and(BitArray bitArray);
	
	/**
	 * The space used by this {@link BitArray} in number of bits.
	 * 
	 * @return the number of bits being used
	 */
	public int bitSize();
	
	/**
	 * The space used by this {@link BitArray} in number of bytes.
	 * 
	 * @return the number of bytes being used
	 */
	public int numBytes();

	/**
	 * Return the byte-array representation
	 * 
	 * @return the byte-array representation for this {@link BitArray}
	 */
	byte[] toByteArray();
	
}

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
 
package com.sangupta.jerry.unsafe;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

/**
 * Provides capability to write to a byte buffer using Java {@link Unsafe}
 * class.
 * 
 * @author sangupta
 * 
 */
@SuppressWarnings("restriction")
public class UnsafeMemory {
	
	/**
	 * The internal reference to the UNSAFE object
	 */
	private static final Unsafe UNSAFE;
	
	/**
	 * Obtain the <code>Unsafe</code> object via reflection
	 */
	static {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			UNSAFE = (Unsafe) field.get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Method to return the {@link com.sun.misc.Unsafe} object that can be used
	 * for unsafe operations. Use this method with much caution as this can lead
	 * to unpredictable results.
	 * 
	 * @return the {@link com.sun.misc.Unsafe} object thus obtained
	 * 
	 */
	public static Unsafe getUnsafe() {
		return UnsafeMemory.UNSAFE;
	}

	/**
	 * array offset for bytes on this machine
	 */
	private static final long byteArrayOffset = UNSAFE.arrayBaseOffset(byte[].class);
	
	/**
	 * array offset for longs on this machine
	 */
	private static final long longArrayOffset = UNSAFE.arrayBaseOffset(long[].class);
	
	/**
	 * array offset for double's on this machine
	 */
	private static final long doubleArrayOffset = UNSAFE.arrayBaseOffset(double[].class);

	/**
	 * Size of a boolean value
	 */
	private static final int SIZE_OF_BOOLEAN = 1;
	
	/**
	 * Size of an integer
	 */
	private static final int SIZE_OF_INT = 4;
	
	/**
	 * Size of a byte
	 */
	private static final int SIZE_OF_BYTE = 1;
	
	/**
	 * Size of short
	 */
	private static final int SIZE_OF_SHORT = 2;
	
	/**
	 * Size of a double
	 */
	private static final int SIZE_OF_LONG = 8;

	/**
	 * The current position in the buffer
	 */
	private int pos = 0;
	
	/**
	 * The current length of the byte buffer
	 */
	private final int bufferLength;
	
	/**
	 * The buffer of bytes over which we operate
	 */
	private final byte[] buffer;

	/**
	 * Create a new instance of {@link UnsafeMemory} that we work upon.
	 * 
	 */
	public UnsafeMemory(final byte[] buffer) {
		if (null == buffer) {
			throw new NullPointerException("buffer cannot be null");
		}

		this.buffer = buffer;
		this.bufferLength = buffer.length;
	}

	/**
	 * Reset the current position of the seek in the internal
	 * byte buffer.
	 * 
	 */
	public void reset() {
		this.pos = 0;
	}
	
	/**
	 * Return the current position of the seek in the internal
	 * byte buffer.
	 * 
	 * @return the current position in buffer
	 */
	public int getPosition() {
		return this.pos;
	}
	
	/**
	 * Write a string to the current location in the buffer. The length of the
	 * string is written first as an {@link Integer}.
	 * 
	 * @param value
	 *            the value to be written to buffer
	 */
	public void putString(final String value) {
		if(value == null) {
			putInt(0);
			return;
		}

		char[] chars = value.toCharArray();
		putInt(chars.length);
		for(char c : chars) {
			UNSAFE.putChar(buffer, byteArrayOffset + pos, c);
			pos++;
		}
	}
	
	/**
	 * A method that checks for the position pointer inside the buffer to be in
	 * the range before making a read call.
	 * 
	 */
	private final void positionCheck() {
		if(this.pos > this.bufferLength) {
			throw new IndexOutOfBoundsException("Trying to read from memory position out of buffer area");
		}
	}
	
	/**
	 * Read a string from the current location in buffer. The first
	 * {@link Integer} in the string would be its length.
	 * 
	 * @return the string as read from the buffer
	 */
	public String getString() {
		positionCheck();
		
		int length = getInt();
		if(length == 0) {
			return null;
		}
		
		return getString(length);
	}
	
	/**
	 * Read a string from the current location in buffer. The length of the
	 * string has been provided.
	 * 
	 * @param length
	 *            the length of the string to read from buffer
	 *            
	 * @return the string as read from the buffer
	 */
	public String getString(int length) {
		positionCheck();
		
		char[] values = new char[length];
		
		for(int index = 0; index < length; index++) {
			values[index] = (char) UNSAFE.getByte(buffer, byteArrayOffset + pos);
			pos ++;
		}

		return String.valueOf(values);
	}

	/**
	 * Write a boolean value to the memory buffer.
	 * 
	 * @param value the value to be written
	 */
	public void putBoolean(final boolean value) {
		positionCheck();
		
		UNSAFE.putBoolean(buffer, byteArrayOffset + pos, value);
		pos += SIZE_OF_BOOLEAN;
	}

	/**
	 * Read a boolean value from the memory buffer.
	 * 
	 * @return the boolean value read from buffer
	 */
	public boolean getBoolean() {
		positionCheck();
		
		boolean value = UNSAFE.getBoolean(buffer, byteArrayOffset + pos);
		pos += SIZE_OF_BOOLEAN;

		return value;
	}

	/**
	 * Write an integer value to the memory buffer.
	 * 
	 * @param value the integer value to write
	 */
	public void putInt(final int value) {
		UNSAFE.putInt(buffer, byteArrayOffset + pos, value);
		pos += SIZE_OF_INT;
	}

	/**
	 * Read an integer value from the memory buffer.
	 * 
	 * @return the read integer value
	 */
	public int getInt() {
		positionCheck();
		
		int value = UNSAFE.getInt(buffer, byteArrayOffset + pos);
		pos += SIZE_OF_INT;

		return value;
	}
	
	/**
	 * Write byte into the underlying byte array.
	 * 
	 * @param bite the <code>byte</code> to write
	 */
	public void putByte(final byte bite) {
		UNSAFE.putByte(buffer, byteArrayOffset + pos, bite);
		pos += SIZE_OF_BYTE;
	}
	
	/**
	 * Read a byte from the underlying byte array.
	 * 
	 * @return the read <code>byte</code> value
	 */
	public byte getByte() {
		positionCheck();
		
		byte bite = UNSAFE.getByte(buffer, byteArrayOffset + pos);
		pos += SIZE_OF_BYTE;
		
		return bite;
	}
	
	/**
	 * Write a short value into the underlying byte array.
	 * 
	 * @param shrt the <code>short</code> value to write
	 */
	public void putShort(final short shrt) {
		UNSAFE.putShort(buffer, byteArrayOffset + pos, shrt);
		pos += SIZE_OF_SHORT;
	}
	
	/**
	 * Read a short value from the underlying byte array.
	 * 
	 * @return the read <code>short</code> value
	 */
	public short getShort() {
		positionCheck();
		
		short shrt = UNSAFE.getShort(buffer, byteArrayOffset + pos);
		pos += SIZE_OF_SHORT;
		
		return shrt;
	}

	/**
	 * Write a long value to the memory buffer.
	 * 
	 * @param value the <code>long<code> value to write
	 */
	public void putLong(final long value) {
		UNSAFE.putLong(buffer, byteArrayOffset + pos, value);
		pos += SIZE_OF_LONG;
	}

	/**
	 * Read a long value from the memory buffer.
	 * 
	 * @return the read <code>long</code> value
	 */
	public long getLong() {
		positionCheck();
		
		long value = UNSAFE.getLong(buffer, byteArrayOffset + pos);
		pos += SIZE_OF_LONG;

		return value;
	}

	/**
	 * Write an array of long values to the memory buffer.
	 * 
	 * @param values the <code>long</code> values to write
	 */
	public void putLongArray(final long[] values) {
		putInt(values.length);

		long bytesToCopy = values.length << 3;
		UNSAFE.copyMemory(values, longArrayOffset, buffer, byteArrayOffset + pos, bytesToCopy);
		pos += bytesToCopy;
	}

	/**
	 * Read an array of long values from the memory buffer.
	 * 
	 * @return the read <code>long</code> array
	 */
	public long[] getLongArray() {
		positionCheck();
		
		int arraySize = getInt();
		long[] values = new long[arraySize];

		long bytesToCopy = values.length << 3;
		UNSAFE.copyMemory(buffer, byteArrayOffset + pos, values, longArrayOffset, bytesToCopy);
		pos += bytesToCopy;

		return values;
	}

	/**
	 * Write an array of double values into the memory buffer.
	 * 
	 * @param values
	 */
	public void putDoubleArray(final double[] values) {
		putInt(values.length);

		long bytesToCopy = values.length << 3;
		UNSAFE.copyMemory(values, doubleArrayOffset, buffer, byteArrayOffset + pos, bytesToCopy);
		pos += bytesToCopy;
	}

	/**
	 * Read an array of double values from the memory buffer.
	 * 
	 * @return the read <code>double</code> array
	 */
	public double[] getDoubleArray() {
		positionCheck();
		
		int arraySize = getInt();
		double[] values = new double[arraySize];

		long bytesToCopy = values.length << 3;
		UNSAFE.copyMemory(buffer, byteArrayOffset + pos, values, doubleArrayOffset, bytesToCopy);
		pos += bytesToCopy;

		return values;
	}

	/**
	 * Return the underlying byte buffer.
	 * 
	 * @return the underlying byte[] array used as buffer.
	 * 
	 */
	public byte[] getBuffer() {
		return buffer;
	}

}

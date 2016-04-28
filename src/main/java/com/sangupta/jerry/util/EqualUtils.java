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
 * @author sangupta
 *
 */
public class EqualUtils {

	/**
	 * Tests if two <code>byte</code> arrays are equal in content or not.
	 * 
	 * @param array1
	 *            the first array
	 * 
	 * @param array2
	 *            the second array
	 * 
	 * @return <code>true</code> if arrays are equal in object or content,
	 *         <code>false</code> otherwise
	 */
	public static boolean equals(byte[] array1, byte[] array2) {
		if(array1 == null || array2 == null) {
			return false;
		}
		
		if(array1 == array2) {
			return true;
		}
		
		if(array1.length != array2.length) {
			return false;
		}
		
		for(int index = 0; index < array1.length; index++) {
			if(array1[index] != array2[index]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Tests if two <code>char</code> arrays are equal in content or not.
	 * 
	 * @param array1
	 *            the first array
	 * 
	 * @param array2
	 *            the second array
	 * 
	 * @return <code>true</code> if arrays are equal in object or content,
	 *         <code>false</code> otherwise
	 */
	public static boolean equals(char[] array1, char[] array2) {
		if(array1 == null || array2 == null) {
			return false;
		}
		
		if(array1 == array2) {
			return true;
		}
		
		if(array1.length != array2.length) {
			return false;
		}
		
		for(int index = 0; index < array1.length; index++) {
			if(array1[index] != array2[index]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Tests if two <code>short</code> arrays are equal in content or not.
	 * 
	 * @param array1
	 *            the first array
	 * 
	 * @param array2
	 *            the second array
	 * 
	 * @return <code>true</code> if arrays are equal in object or content,
	 *         <code>false</code> otherwise
	 */
	public static boolean equals(short[] array1, short[] array2) {
		if(array1 == null || array2 == null) {
			return false;
		}
		
		if(array1 == array2) {
			return true;
		}
		
		if(array1.length != array2.length) {
			return false;
		}
		
		for(int index = 0; index < array1.length; index++) {
			if(array1[index] != array2[index]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Tests if two <code>int</code> arrays are equal in content or not.
	 * 
	 * @param array1
	 *            the first array
	 * 
	 * @param array2
	 *            the second array
	 * 
	 * @return <code>true</code> if arrays are equal in object or content,
	 *         <code>false</code> otherwise
	 */
	public static boolean equals(int[] array1, int[] array2) {
		if(array1 == null || array2 == null) {
			return false;
		}
		
		if(array1 == array2) {
			return true;
		}
		
		if(array1.length != array2.length) {
			return false;
		}
		
		for(int index = 0; index < array1.length; index++) {
			if(array1[index] != array2[index]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Tests if two <code>long</code> arrays are equal in content or not.
	 * 
	 * @param array1
	 *            the first array
	 * 
	 * @param array2
	 *            the second array
	 * 
	 * @return <code>true</code> if arrays are equal in object or content,
	 *         <code>false</code> otherwise
	 */
	public static boolean equals(long[] array1, long[] array2) {
		if(array1 == null || array2 == null) {
			return false;
		}
		
		if(array1 == array2) {
			return true;
		}
		
		if(array1.length != array2.length) {
			return false;
		}
		
		for(int index = 0; index < array1.length; index++) {
			if(array1[index] != array2[index]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Tests if two <code>float</code> arrays are equal in content or not.
	 * 
	 * @param array1
	 *            the first array
	 * 
	 * @param array2
	 *            the second array
	 * 
	 * @return <code>true</code> if arrays are equal in object or content,
	 *         <code>false</code> otherwise
	 */
	public static boolean equals(float[] array1, float[] array2) {
		if(array1 == null || array2 == null) {
			return false;
		}
		
		if(array1 == array2) {
			return true;
		}
		
		if(array1.length != array2.length) {
			return false;
		}
		
		for(int index = 0; index < array1.length; index++) {
			if(array1[index] != array2[index]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Tests if two <code>double</code> arrays are equal in content or not.
	 * 
	 * @param array1
	 *            the first array
	 * 
	 * @param array2
	 *            the second array
	 * 
	 * @return <code>true</code> if arrays are equal in object or content,
	 *         <code>false</code> otherwise
	 */
	public static boolean equals(double[] array1, double[] array2) {
		if(array1 == null || array2 == null) {
			return false;
		}
		
		if(array1 == array2) {
			return true;
		}
		
		if(array1.length != array2.length) {
			return false;
		}
		
		for(int index = 0; index < array1.length; index++) {
			if(array1[index] != array2[index]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Test if two byte arrays are not equal in all respects.
	 * 
	 * @param bytes1
	 *            the first byte array
	 * 
	 * @param bytes2
	 *            the second byte array
	 * 
	 * @return <code>true</code> if arrays are not equal, <code>false</code>
	 *         otherwise
	 */
	public static boolean notEquals(byte[] bytes1, byte[] bytes2) {
		return !equals(bytes1, bytes2);
	}

}
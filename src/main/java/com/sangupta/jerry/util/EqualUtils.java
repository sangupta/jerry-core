/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2014, Sandeep Gupta
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
 * @author sangupta
 *
 */
public class EqualUtils {

	/**
	 * Tests if two byte arrays are equal in content or not.
	 * 
	 * @param bytes1 the first byte array
	 * 
	 * @param bytes2 the second byte array
	 * 
	 * @return <code>true</code> if arrays are equal in object or content,
	 * <code>false</code> otherwise
	 */
	public static boolean equals(byte[] bytes1, byte[] bytes2) {
		if(bytes1 == null || bytes2 == null) {
			return false;
		}
		
		if(bytes1 == bytes2) {
			return true;
		}
		
		if(bytes1.length != bytes2.length) {
			return false;
		}
		
		for(int index = 0; index < bytes1.length; index++) {
			if(bytes1[index] != bytes2[index]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Test if two byte arrays are not equal in all respects.
	 * 
	 * @param bytes1 the first byte array
	 * 
	 * @param bytes2 the second byte array
	 * 
	 * @return <code>true</code> if arrays are not equal, <code>false</code>
	 * otherwise
	 */
	public static boolean notEquals(byte[] bytes1, byte[] bytes2) {
		return !equals(bytes1, bytes2);
	}

}

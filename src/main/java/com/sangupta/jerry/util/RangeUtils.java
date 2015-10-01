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
 
package com.sangupta.jerry.util;

/**
 * Utility functions to check if a value is in range.
 * 
 * @author sangupta
 * 
 * @since 2.3
 */
public class RangeUtils {
	
	/**
	 * Check if a character is in the given range.
	 * 
	 * @param valueToTest
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static boolean isChar(char valueToTest, char minValue, char maxValue) {
		if((minValue <= valueToTest) && (valueToTest <= maxValue)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Check if an integer value is in the given range
	 * 
	 * @param valueToTest
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static boolean isInt(int valueToTest, int minValue, int maxValue) {
		if((minValue <= valueToTest) && (valueToTest <= maxValue)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Check if a long value is in the given range.
	 * 
	 * @param valueToTest
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static boolean isLong(long valueToTest, long minValue, long maxValue) {
		if((minValue <= valueToTest) && (valueToTest <= maxValue)) {
			return true;
		}
		
		return false;
	}
	
}

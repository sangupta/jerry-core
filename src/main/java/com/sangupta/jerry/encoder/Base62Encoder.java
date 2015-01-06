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
 
package com.sangupta.jerry.encoder;

import com.sangupta.jerry.util.AssertUtils;

/**
 * Simple class to convert numbers to Base62 and back. The class can encode/decode and compare
 * around 100 million long numbers in around 14 seconds on an i7 quad-core machine with 8 gigs
 * of RAM
 *  
 * @author sangupta
 *
 */
public class Base62Encoder {
	
	/**
	 * List of elements in order to be shown
	 */
	private static final char[] elements = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z'
	};
	
	/**
	 * Function to encode a given number to Base62. Can handle negative numbers
	 * as well.
	 * 
	 * @param number
	 *            the number to encode
	 * 
	 * @return string representation of number
	 */
	public static String encode(long number) {
		StringBuilder builder = new StringBuilder(10);
		
		if(number < 0) {
			builder.append(elements[0]);
			number = 0 - number;
		}
		
		int remainder;
		do {
			remainder = (int) (number % 62l);
			builder.append(elements[remainder]);
			number = number / 62;
		} while(number > 0);
		
		return builder.toString();
	}
	
	/**
	 * Function to encode a list of numbers to Base62 all in one
	 * string-representation.
	 * 
	 * @param numbers
	 *            a list of numbers represented as <code>long</code> values
	 * 
	 * @return a string that is a combined Base62 representation of all given
	 *         numbers
	 */
	public static String encode(long... numbers) {
		StringBuilder builder = new StringBuilder(numbers.length * 10);
		
		for(long number : numbers) {
			if(number < 0) {
				builder.append(elements[0]);
				number = 0 - number;
			}
			
			int remainder;
			do {
				remainder = (int) (number % 62l);
				builder.append(elements[remainder]);
				number = number / 62;
			} while(number > 0);
		}
		
		return builder.toString();
	}
	
	/**
	 * Function to decode a given Base62 string back to its original form
	 * 
	 * @param string
	 *            the Base62 representation
	 * 
	 * @throws IllegalArgumentException
	 *             if the base62 encoded string contains foreign characters
	 *             or, if the string is <code>null</code> or empty 
	 * 
	 * @return the number that it represents
	 */
	public static long decode(String string) {
		if(AssertUtils.isEmpty(string)) {
			throw new IllegalArgumentException("Cannot decode null/empty string");
		}
		
		char[] array = string.toCharArray();
		long num = 0;
		int index = array.length - 1;
		boolean negative = false;
		for( ; index >= 0; index--) {
			char c = array[index];
			
			if(index == 0 && c == elements[0]) {
				negative = true;
				break;
			}

			num = num * 62;
			
			if('A' <= c && c <= 'Z') {
				num += (c - 'A');
				continue;
			}
			
			if('0' <= c && c <= '9') {
				num += (c - '0' + 26);
				continue;
			}
			
			if('a' <= c && c <= 'z') {
				num += (c - 'a' + 36);
				continue;
			}
			
			throw new IllegalArgumentException("String is not Base62 encoded");
		}
		
		if(negative) {
			num = 0 - num;
		}
		
		return num;
	}
	
}

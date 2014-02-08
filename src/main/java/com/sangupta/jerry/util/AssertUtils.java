/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012, Sandeep Gupta
 * 
 * http://www.sangupta/projects/jerry
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

import java.util.Collection;
import java.util.Map;

/**
 * Common assertion functions that are null safe. These may not be
 * performant for common Java data types like {@link String} due
 * to the extra overhead of a method call.
 * 
 * @author sangupta
 *
 */
public class AssertUtils {
	
	/**
	 * Check if a given string is <code>null</code> or zero-length. Returns
	 * <code>false</code> even if the string contains white spaces. Use
	 * {@link AssertUtils#isBlank(String)} to check by ignoring white-spaces.
	 * 
	 * @param string
	 *            the string to tested for emptiness.
	 * 
	 * @return <code>true</code> if string is empty, <code>false</code>
	 *         otherwise.
	 */
	public static boolean isEmpty(String string) {
		if(string == null || string.length() == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if a given string is <code>null</code> or zero-length. Returns
	 * <code>true</code> even if the string contains white spaces. Use
	 * {@link AssertUtils#isEmpty(String)} to check without ignoring
	 * white-spaces.
	 * 
	 * @param string
	 *            the string to tested for emptiness.
	 * 
	 * @return <code>true</code> if string is empty, <code>false</code>
	 *         otherwise.
	 */
	public static boolean isBlank(String string) {
		if(string == null || string.trim().length() == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if a given string is <code>non null</code> and non-zero length.
	 * White-spaces are considered to be non-empty. Use
	 * {@link AssertUtils#isNotBlank(String)} method to check by ignoring
	 * white-spaces.
	 * 
	 * @param string
	 *            the string to tested for non-emptiness.
	 * 
	 * @return <code>false</code> if string is empty, <code>true</code>
	 *         otherwise.
	 */
	public static boolean isNotEmpty(String string) {
		if(string == null || string.length() == 0) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Check if a given string is <code>non null</code> and non-zero length.
	 * White-spaces are considered to be empty. Use
	 * {@link AssertUtils#isNotEmpty(String)} method to check without ignoring
	 * white-spaces.
	 * 
	 * @param string
	 *            the string to tested for non-emptiness.
	 * 
	 * @return <code>false</code> if string is empty, <code>true</code>
	 *         otherwise.
	 */
	public static boolean isNotBlank(String string) {
		if(string == null || string.trim().length() == 0) {
			return false;
		}
		
		return true;
	}

	/**
	 * Check if given array is <code>null</code> or zero-length.
	 * 
	 * @param array
	 *            the array to be tested
	 * 
	 * @return <code>true</code> if array is <code>null</code> or zero-length,
	 *         <code>false</code> otherwise.
	 */
	public static boolean isEmpty(Object[] array) {
		if(array == null || array.length == 0) {
			return true;
		}
		
		return false;
	}

	/**
	 * Check if given array is not-<code>null</code> and non-zero-length.
	 * 
	 * @param array
	 *            the array to be tested
	 * 
	 * @return <code>false</code> if array is <code>null</code> or zero-length,
	 *         <code>true</code> otherwise.
	 */
	public static boolean isNotEmpty(Object[] array) {
		if(array == null || array.length == 0) {
			return false;
		}
		
		return true;
	}

	/**
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map params) {
		if(params == null || params.isEmpty()) {
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Map params) {
		if(params == null || params.isEmpty()) {
			return false;
		}
		
		return true;
	}

	/**
	 * 
	 * @param collection
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection collection) {
		if(collection == null || collection.isEmpty()) {
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * @param collection
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Collection collection) {
		if(collection == null || collection.isEmpty()) {
			return false;
		}
		
		return true;
	}

	/**
	 * Check if an object is <code>null</code>. Ideally this method should
	 * be replaced by a normal <code>null</code> check. It is there only to
	 * support the abstraction of this class, when the incoming object type is
	 * not known.
	 * 
	 * @param object
	 *            the object to be tested
	 * 
	 * @return returns <code>true</code> if object is null,
	 *         <code>false</code> otherwise.
	 */
	public static boolean isEmpty(Object object) {
		if(object == null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if an object is NOT-<code>null</code>. Ideally this method should
	 * be replaced by a normal <code>null</code> check. It is there only to
	 * support the abstraction of this class, when the incoming object type is
	 * not known.
	 * 
	 * @param object
	 *            the object to be tested
	 * 
	 * @return returns <code>true</code> if object is not-null,
	 *         <code>false</code> otherwise.
	 */
	public static boolean isNotEmpty(Object object) {
		if(object == null) {
			return false;
		}
		
		return true;
	}
}

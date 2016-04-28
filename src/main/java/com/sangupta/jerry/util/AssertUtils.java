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

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * Common assertion functions that are null safe. These may not be
 * performant for common Java data types like {@link String} due
 * to the extra overhead of a method call.
 *
 * @author sangupta
 * @since 1.0.0
 */
public abstract class AssertUtils {

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
		if(string == null) {
			return true;
		}

		return string.trim().length() == 0;
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
		if(array == null) {
			return true;
		}

		return array.length == 0;
	}

	/**
	 * Check if the given map is <code>null</code> or empty.
	 *
	 * @param map
	 *            the map to be tested
	 *
	 * @return <code>true</code> if the map is either null or empty,
	 *         <code>false</code> otherwise.
	 *
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map map) {
		if(map == null) {
			return true;
		}

		return map.isEmpty();
	}

	/**
	 * Check if the given collection is <code>null</code> or empty.
	 *
	 * @param collection
	 *            the collection to be tested
	 *
	 * @return <code>true</code> if the collection is either <code>null</code>
	 *         or empty, <code>false</code> otherwise.
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection collection) {
		if(collection == null) {
			return true;
		}

		return collection.isEmpty();
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
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object object) {
		if(object == null) {
			return true;
		}

		// in this method we should be checking for the instance type as well
		// because it may be called on reference type than actual instance type

		if(object instanceof String) {
			return isEmpty((String) object);
		}

		if(object instanceof Collection) {
			return isEmpty((Collection) object);
		}

		if(object instanceof Map) {
			return isEmpty((Map) object);
		}

		if(object instanceof int[]) {
			return isEmpty((int[]) object);
		}

		if(object instanceof byte[]) {
			return isEmpty((byte[]) object);
		}

		if(object instanceof char[]) {
			return isEmpty((char[]) object);
		}

		if(object instanceof short[]) {
			return isEmpty((short[]) object);
		}

		if(object instanceof long[]) {
			return isEmpty((long[]) object);
		}

		if(object instanceof float[]) {
			return isEmpty((float[]) object);
		}

		if(object instanceof double[]) {
			return isEmpty((double[]) object);
		}

		return false;
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
	public static boolean isEmpty(short[] array) {
		if(array == null) {
			return true;
		}

		return array.length == 0;
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
	public static boolean isEmpty(byte[] array) {
		if(array == null) {
			return true;
		}

		return array.length == 0;
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
	public static boolean isEmpty(char[] array) {
		if(array == null) {
			return true;
		}

		return array.length == 0;
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
	public static boolean isEmpty(int[] array) {
		if(array == null) {
			return true;
		}

		return array.length == 0;
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
	public static boolean isEmpty(long[] array) {
		if(array == null) {
			return true;
		}

		return array.length == 0;
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
	public static boolean isEmpty(float[] array) {
		if(array == null) {
			return true;
		}

		return array.length == 0;
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
	public static boolean isEmpty(double[] array) {
		if(array == null) {
			return true;
		}

		return array.length == 0;
	}

	// ALL NOT METHODS GO DOWN HERE

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
		return !isEmpty(string);
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
		return !isBlank(string);
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
		return !isEmpty(array);
	}

	/**
	 * Check if the given map is not-<code>null</code> and not-empty.
	 *
	 * @param map
	 *            the map to be tested
	 *
	 * @return <code>false</code> if the map is either <code>null</code> or
	 *         empty, <code>true</code> otherwise.
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	/**
	 * Check if the given collection is not-<code>null</code> and has values.
	 *
	 * @param collection
	 *            the collection to be tested
	 *
	 * @return <code>true</code> if the collection is not-empty,
	 *         <code>false</code> otherwise
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Collection collection) {
		return !isEmpty(collection);
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
		return !isEmpty(object);
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
	public static boolean isNotEmpty(short[] array) {
		return !isEmpty(array);
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
	public static boolean isNotEmpty(byte[] array) {
		return !isEmpty(array);
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
	public static boolean isNotEmpty(char[] array) {
		return !isEmpty(array);
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
	public static boolean isNotEmpty(int[] array) {
		return !isEmpty(array);
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
	public static boolean isNotEmpty(long[] array) {
		return !isEmpty(array);
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
	public static boolean isNotEmpty(float[] array) {
		return !isEmpty(array);
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
	public static boolean isNotEmpty(double[] array) {
		return !isEmpty(array);
	}

	/**
	 * Check if all provided {@link String}s are empty. Even if a single one is
	 * not empty, this will return a <code>false</code>.
	 *
	 * @param strings
	 *            the strings to check
	 *
	 * @return <code>true</code> if all strings are empty, <code>false</code>
	 *         otherwise
	 */
	public static boolean areEmpty(String... strings) {
		if(AssertUtils.isEmpty(strings)) {
			return true;
		}

		for(String string : strings) {
			if(AssertUtils.isNotEmpty(string)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Check if all provided {@link String}s are not empty. Even if a single one
	 * is empty, this method will return a <code>false</code>.
	 *
	 * @param strings
	 *            the strings to check
	 *
	 * @return <code>false</code> if all the strings are NOT-empty,
	 *         <code>true</code> otherwise
	 */
	public static boolean areNotEmpty(String... strings) {
		if(AssertUtils.isEmpty(strings)) {
			return false;
		}

		for(String string : strings) {
			if(AssertUtils.isEmpty(string)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isEmpty(Properties properties) {
		if(properties == null) {
			return true;
		}

		return properties.isEmpty();
	}

	public static boolean isNotEmpty(Properties properties) {
		if(properties == null) {
			return false;
		}

		return !properties.isEmpty();
	}
}

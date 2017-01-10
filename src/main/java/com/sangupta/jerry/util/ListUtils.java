/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2017, Sandeep Gupta
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

import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions related to {@link List}.
 *
 * @author sangupta
 *
 * @since 2.2
 */
public abstract class ListUtils {

	/**
	 * Create a new list of integer values.
	 *
	 * @param values
	 *            the integer values
	 *
	 * @return <code>null</code> if values is <code>null</code>, an empty list
	 *         if values is empty, else an {@link ArrayList} instance
	 */
	public static List<Integer> listOf(int... values) {
		if(values == null) {
			return null;
		}

		List<Integer> list = new ArrayList<Integer>();
		if(values.length == 0) {
			return list;
		}

		for(int value : values) {
			list.add(value);
		}

		return list;
	}

	/**
	 * Create a new list of long values.
	 *
	 * @param values
	 *            the long values
	 *
	 * @return <code>null</code> if values is <code>null</code>, an empty list
	 *         if values is empty, else an {@link ArrayList} instance
	 */
	public static List<Long> listOf(long... values) {
		if(values == null) {
			return null;
		}

		List<Long> list = new ArrayList<Long>();
		if(values.length == 0) {
			return list;
		}

		for(long value : values) {
			list.add(value);
		}

		return list;
	}

	/**
	 * Create a new list of float values.
	 *
	 * @param values
	 *            the float values
	 *
	 * @return <code>null</code> if values is <code>null</code>, an empty list
	 *         if values is empty, else an {@link ArrayList} instance
	 */
	public static List<Float> listOf(float... values) {
		if(values == null) {
			return null;
		}

		List<Float> list = new ArrayList<Float>();
		if(values.length == 0) {
			return list;
		}

		for(float value : values) {
			list.add(value);
		}

		return list;
	}

	/**
	 * Create a new list of double values.
	 *
	 * @param values
	 *            the double values
	 *
	 * @return <code>null</code> if values is <code>null</code>, an empty list
	 *         if values is empty, else an {@link ArrayList} instance
	 */
	public static List<Double> listOf(double... values) {
		if(values == null) {
			return null;
		}

		List<Double> list = new ArrayList<Double>();
		if(values.length == 0) {
			return list;
		}

		for(double value : values) {
			list.add(value);
		}

		return list;
	}

	/**
	 * Create a new list of {@link String} values.
	 *
	 * @param values
	 *            the {@link String} values
	 *
	 * @return <code>null</code> if values is <code>null</code>, an empty list
	 *         if values is empty, else an {@link ArrayList} instance
	 */
	public static List<String> listOf(String... values) {
		if(values == null) {
			return null;
		}

		List<String> list = new ArrayList<String>();
		if(values.length == 0) {
			return list;
		}

		for(String value : values) {
			list.add(value);
		}

		return list;
	}
}

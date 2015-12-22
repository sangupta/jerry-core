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
 * Utility functions around {@link Object} instances.
 * 
 * @author sangupta
 *
 */
public class ObjectUtils {

	/**
	 * Check if the object represents a primitive type of: <code>byte</code>,
	 * <code>char</code>, <code>short</code>, <code>int</code>,
	 * <code>long</code>, <code>float</code>, <code>double</code> or
	 * <code>boolean</code>.
	 * 
	 * @param instance
	 *            returns <code>true</code> if the object is primitive type,
	 *            <code>false</code> otherwise. <code>null</code> values return
	 *            <code>false</code>
	 * 
	 * @return <code>true</code> if object is primitive, <code>false</code>
	 *         otherwise or if object is null
	 */
	public static boolean isPrimitive(Object instance) {
		if(instance == null) {
			return false;
		}
		
		if(instance instanceof Integer) {
			return true;
		}
		if(instance instanceof Byte) {
			return true;
		}
		if(instance instanceof Short) {
			return true;
		}
		if(instance instanceof Character) {
			return true;
		}
		if(instance instanceof Long) {
			return true;
		}
		if(instance instanceof Float) {
			return true;
		}
		if(instance instanceof Double) {
			return true;
		}
		if(instance instanceof Boolean) {
			return true;
		}
		
		return false;
	}

	/**
	 * Check if given object array represents an array of primitives, i.e. in
	 * one of: <code>int[]</code>, <code>byte[]</code>, <code>short[]</code>,
	 * <code>char[]</code>, <code>long[]</code>, <code>float[]</code>,
	 * <code>double[]</code>, <code>boolean[]</code>
	 * 
	 * @param instance
	 *            the object to be test
	 * 
	 * @return <code>true</code> if the object is a primitive aray,
	 *         <code>false</code> otherwise or if the object is
	 *         <code>null</code>
	 */
	public static boolean isPrimitiveArray(Object instance) {
		if(instance == null) {
			return false;
		}
		
		if(instance instanceof int[]) {
			return true;
		}
		if(instance instanceof byte[]) {
			return true;
		}
		if(instance instanceof short[]) {
			return true;
		}
		if(instance instanceof char[]) {
			return true;
		}
		if(instance instanceof long[]) {
			return true;
		}
		if(instance instanceof float[]) {
			return true;
		}
		if(instance instanceof double[]) {
			return true;
		}
		if(instance instanceof boolean[]) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Convert the array into a {@link String} instance values separated by a
	 * COMMA.
	 * 
	 * @param instance
	 *            the primitive array instance that needs to be stringified
	 * 
	 * @return the {@link String} representation, or <code>null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             if the supplied instance is not a primitive array
	 */
	public static String stringifyPrimitiveArray(Object instance) {
		if(instance == null) {
			return null;
		}
		
		if(instance instanceof int[]) {
			return StringUtils.stringifyArray((int[]) instance);
		}
		
		if(instance instanceof short[]) {
			return StringUtils.stringifyArray((short[]) instance);
		}
		
		if(instance instanceof byte[]) {
			return StringUtils.stringifyArray((byte[]) instance);
		}
		
		if(instance instanceof char[]) {
			return StringUtils.stringifyArray((char[]) instance);
		}
		
		if(instance instanceof long[]) {
			return StringUtils.stringifyArray((long[]) instance);
		}
		
		if(instance instanceof float[]) {
			return StringUtils.stringifyArray((float[]) instance);
		}
		
		if(instance instanceof double[]) {
			return StringUtils.stringifyArray((double[]) instance);
		}
		
		if(instance instanceof boolean[]) {
			return StringUtils.stringifyArray((boolean[]) instance);
		}
		
		throw new IllegalArgumentException("Object is not a primitive array");
	}

	/**
	 * Check if given object array represents an array of boxed-primitives, i.e.
	 * in one of: <code>Integer[]</code>, <code>Byte[]</code>,
	 * <code>Short[]</code>, <code>Char[]</code>, <code>Long[]</code>,
	 * <code>Float[]</code>, <code>Double[]</code>, <code>Boolean[]</code>
	 * 
	 * @param instance
	 *            the object to be test
	 * 
	 * @return <code>true</code> if the object is a boxed-primitive aray,
	 *         <code>false</code> otherwise or if the object is
	 *         <code>null</code>
	 */
	public static boolean isBoxedPrimitiveArray(Object instance) {
		if(instance == null) {
			return false;
		}
		
		if(instance instanceof Integer[]) {
			return true;
		}
		if(instance instanceof Byte[]) {
			return true;
		}
		if(instance instanceof Short[]) {
			return true;
		}
		if(instance instanceof Character[]) {
			return true;
		}
		if(instance instanceof Long[]) {
			return true;
		}
		if(instance instanceof Float[]) {
			return true;
		}
		if(instance instanceof Double[]) {
			return true;
		}
		if(instance instanceof Boolean[]) {
			return true;
		}
		
		return false;
	}
	
}

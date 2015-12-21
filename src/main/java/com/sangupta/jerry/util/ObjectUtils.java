package com.sangupta.jerry.util;

/**
 * Utility functions around {@link Object} instances.
 * 
 * @author sangupta
 *
 */
public class ObjectUtils {

	/**
	 * Check if the object represents a primitive type of: byte, char, short,
	 * int, long, float, double or boolean.
	 * 
	 * @param obj
	 *            returns <code>true</code> if the object is primitive type,
	 *            <code>false</code> otherwise. <code>null</code> values return
	 *            <code>false</code>
	 * 
	 * @return <code>true</code> if object is primitive
	 */
	public static boolean isPrimitive(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj instanceof Integer) {
			return true;
		}
		if(obj instanceof Byte) {
			return true;
		}
		if(obj instanceof Short) {
			return true;
		}
		if(obj instanceof Character) {
			return true;
		}
		if(obj instanceof Long) {
			return true;
		}
		if(obj instanceof Float) {
			return true;
		}
		if(obj instanceof Double) {
			return true;
		}
		if(obj instanceof Boolean) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if given object array represents an array of primitives.
	 * 
	 */
	public static boolean isPrimitiveArray(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj instanceof int[]) {
			return true;
		}
		if(obj instanceof byte[]) {
			return true;
		}
		if(obj instanceof short[]) {
			return true;
		}
		if(obj instanceof char[]) {
			return true;
		}
		if(obj instanceof long[]) {
			return true;
		}
		if(obj instanceof float[]) {
			return true;
		}
		if(obj instanceof double[]) {
			return true;
		}
		if(obj instanceof boolean[]) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Convert the array into a {@link String} instance values separated by COMMA
	 * 
	 * @param array
	 * @return
	 */
	public static String stringifyPrimitiveArray(Object array) {
		if(array == null) {
			return null;
		}
		
		if(array instanceof int[]) {
			return StringUtils.stringifyArray((int[]) array);
		}
		
		if(array instanceof short[]) {
			return StringUtils.stringifyArray((short[]) array);
		}
		
		if(array instanceof byte[]) {
			return StringUtils.stringifyArray((byte[]) array);
		}
		
		if(array instanceof char[]) {
			return StringUtils.stringifyArray((char[]) array);
		}
		
		if(array instanceof long[]) {
			return StringUtils.stringifyArray((long[]) array);
		}
		
		if(array instanceof float[]) {
			return StringUtils.stringifyArray((float[]) array);
		}
		
		if(array instanceof double[]) {
			return StringUtils.stringifyArray((double[]) array);
		}
		
		if(array instanceof boolean[]) {
			return StringUtils.stringifyArray((boolean[]) array);
		}
		
		throw new IllegalArgumentException("Object is not a primitive array");
	}

	public static boolean isBoxedPrimitiveArray(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj instanceof Integer[]) {
			return true;
		}
		if(obj instanceof Byte[]) {
			return true;
		}
		if(obj instanceof Short[]) {
			return true;
		}
		if(obj instanceof Character[]) {
			return true;
		}
		if(obj instanceof Long[]) {
			return true;
		}
		if(obj instanceof Float[]) {
			return true;
		}
		if(obj instanceof Double[]) {
			return true;
		}
		if(obj instanceof Boolean[]) {
			return true;
		}
		
		return false;
	}
}

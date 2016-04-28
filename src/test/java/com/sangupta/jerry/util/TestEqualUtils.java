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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit-tests for {@link EqualUtils} utility functions.
 *
 * @author sangupta
 * @added 13 July 2012
 */
public class TestEqualUtils {

	@Test
	public void testEqualsByteArray() {
		assertFalse(EqualUtils.equals((byte[]) null, null));
		assertFalse(EqualUtils.equals(new byte[2], null));
		assertFalse(EqualUtils.equals(null, new byte[2]));

		byte[] array1 = null;
		byte[] array2 = array1;
		assertFalse(EqualUtils.equals(array1, array2));

		array1 = new byte[1];
		array2 = array1;

		assertTrue(EqualUtils.equals(array1, array2));

		array1[0] = 1;
		array2 = new byte[1];
		array2[0] = 1;
		assertTrue(EqualUtils.equals(array1, array2));

		array2[0] = 2;
		assertFalse(EqualUtils.equals(array1, array2));

		array2 = new byte[2];
		assertFalse(EqualUtils.equals(array1, array2));
	}

	@Test
	public void testEqualsIntArray() {
		assertFalse(EqualUtils.equals((int[]) null, null));
		assertFalse(EqualUtils.equals(new int[2], null));
		assertFalse(EqualUtils.equals(null, new int[2]));

		int[] array1 = null;
		int[] array2 = array1;
		assertFalse(EqualUtils.equals(array1, array2));

		array1 = new int[1];
		array2 = array1;

		assertTrue(EqualUtils.equals(array1, array2));

		array1[0] = 1;
		array2 = new int[1];
		array2[0] = 1;
		assertTrue(EqualUtils.equals(array1, array2));

		array2[0] = 2;
		assertFalse(EqualUtils.equals(array1, array2));

		array2 = new int[2];
		assertFalse(EqualUtils.equals(array1, array2));
	}

	@Test
	public void testEqualsLongArray() {
		assertFalse(EqualUtils.equals((long[]) null, null));
		assertFalse(EqualUtils.equals(new long[2], null));
		assertFalse(EqualUtils.equals(null, new long[2]));

		long[] array1 = null;
		long[] array2 = array1;
		assertFalse(EqualUtils.equals(array1, array2));

		array1 = new long[1];
		array2 = array1;

		assertTrue(EqualUtils.equals(array1, array2));

		array1[0] = 1;
		array2 = new long[1];
		array2[0] = 1;
		assertTrue(EqualUtils.equals(array1, array2));

		array2[0] = 2;
		assertFalse(EqualUtils.equals(array1, array2));

		array2 = new long[2];
		assertFalse(EqualUtils.equals(array1, array2));
	}

	@Test
	public void testEqualsCharArray() {
		assertFalse(EqualUtils.equals((char[]) null, null));
		assertFalse(EqualUtils.equals(new char[2], null));
		assertFalse(EqualUtils.equals(null, new char[2]));

		char[] array1 = null;
		char[] array2 = array1;
		assertFalse(EqualUtils.equals(array1, array2));

		array1 = new char[1];
		array2 = array1;

		assertTrue(EqualUtils.equals(array1, array2));

		array1[0] = 1;
		array2 = new char[1];
		array2[0] = 1;
		assertTrue(EqualUtils.equals(array1, array2));

		array2[0] = 2;
		assertFalse(EqualUtils.equals(array1, array2));

		array2 = new char[2];
		assertFalse(EqualUtils.equals(array1, array2));
	}

	@Test
	public void testEqualsShortArray() {
		assertFalse(EqualUtils.equals((short[]) null, null));
		assertFalse(EqualUtils.equals(new short[2], null));
		assertFalse(EqualUtils.equals(null, new short[2]));

		short[] array1 = null;
		short[] array2 = array1;
		assertFalse(EqualUtils.equals(array1, array2));

		array1 = new short[1];
		array2 = array1;

		assertTrue(EqualUtils.equals(array1, array2));

		array1[0] = 1;
		array2 = new short[1];
		array2[0] = 1;
		assertTrue(EqualUtils.equals(array1, array2));

		array2[0] = 2;
		assertFalse(EqualUtils.equals(array1, array2));

		array2 = new short[2];
		assertFalse(EqualUtils.equals(array1, array2));
	}

	@Test
	public void testEqualsFloatArray() {
		assertFalse(EqualUtils.equals((float[]) null, null));
		assertFalse(EqualUtils.equals(new float[2], null));
		assertFalse(EqualUtils.equals(null, new float[2]));

		float[] array1 = null;
		float[] array2 = array1;
		assertFalse(EqualUtils.equals(array1, array2));

		array1 = new float[1];
		array2 = array1;

		assertTrue(EqualUtils.equals(array1, array2));

		array1[0] = 1;
		array2 = new float[1];
		array2[0] = 1;
		assertTrue(EqualUtils.equals(array1, array2));

		array2[0] = 2;
		assertFalse(EqualUtils.equals(array1, array2));

		array2 = new float[2];
		assertFalse(EqualUtils.equals(array1, array2));
	}

	@Test
	public void testEqualsDoubleArray() {
		assertFalse(EqualUtils.equals((double[]) null, null));
		assertFalse(EqualUtils.equals(new double[2], null));
		assertFalse(EqualUtils.equals(null, new double[2]));

		double[] array1 = null;
		double[] array2 = array1;
		assertFalse(EqualUtils.equals(array1, array2));

		array1 = new double[1];
		array2 = array1;

		assertTrue(EqualUtils.equals(array1, array2));

		array1[0] = 1;
		array2 = new double[1];
		array2[0] = 1;
		assertTrue(EqualUtils.equals(array1, array2));

		array2[0] = 2;
		assertFalse(EqualUtils.equals(array1, array2));

		array2 = new double[2];
		assertFalse(EqualUtils.equals(array1, array2));
	}

}

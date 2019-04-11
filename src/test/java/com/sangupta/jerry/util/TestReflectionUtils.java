/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
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

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestReflectionUtils {

	@Test
	public void testGetGetterMethod() {
		Assert.assertEquals((String) null, ReflectionUtils.getGetterMethodName(null));
		Assert.assertEquals((String) null, ReflectionUtils.getGetterMethodName(""));
		Assert.assertEquals("getName", ReflectionUtils.getGetterMethodName("name"));
	}

	@Test
	public void testConvertToMapNoTransient() {
		// test without transient variables
		MyValueObject mvo = new MyValueObject();
		Map<String, Object> map = ReflectionUtils.convertToMap(mvo, false);

		Assert.assertNotNull(map);
		Assert.assertEquals(17, map.size());

		Assert.assertTrue(map.containsKey("bite"));
		Assert.assertTrue(map.containsKey("chr"));
		Assert.assertTrue(map.containsKey("shrt"));
		Assert.assertTrue(map.containsKey("nt"));
		Assert.assertTrue(map.containsKey("lng"));
		Assert.assertTrue(map.containsKey("flt"));
		Assert.assertTrue(map.containsKey("dbl"));
		Assert.assertTrue(map.containsKey("bln"));
		Assert.assertTrue(map.containsKey("strng"));
		Assert.assertTrue(map.containsKey("intArray"));
		Assert.assertTrue(map.containsKey("charArray"));
		Assert.assertTrue(map.containsKey("byteArray"));
		Assert.assertTrue(map.containsKey("shortArray"));
		Assert.assertTrue(map.containsKey("longArray"));
		Assert.assertTrue(map.containsKey("floatArray"));
		Assert.assertTrue(map.containsKey("doubleArray"));
		Assert.assertTrue(map.containsKey("booleanArray"));

		Assert.assertEquals(mvo.bite, map.get("bite"));
		Assert.assertEquals(mvo.chr, map.get("chr"));
		Assert.assertEquals(mvo.shrt, map.get("shrt"));
		Assert.assertEquals(mvo.nt, map.get("nt"));
		Assert.assertEquals(mvo.lng, map.get("lng"));
		Assert.assertEquals(mvo.flt, map.get("flt"));
		Assert.assertEquals(mvo.dbl, map.get("dbl"));
		Assert.assertEquals(mvo.bln, map.get("bln"));
		Assert.assertEquals(mvo.strng, map.get("strng"));
		Assert.assertEquals(mvo.intArray, map.get("intArray"));
		Assert.assertEquals(mvo.charArray, map.get("charArray"));
		Assert.assertEquals(mvo.byteArray, map.get("byteArray"));
		Assert.assertEquals(mvo.shortArray, map.get("shortArray"));
		Assert.assertEquals(mvo.longArray, map.get("longArray"));
		Assert.assertEquals(mvo.floatArray, map.get("floatArray"));
		Assert.assertEquals(mvo.doubleArray, map.get("doubleArray"));
		Assert.assertEquals(mvo.booleanArray, map.get("booleanArray"));
	}

	@Test
	public void testConvertToMapWithTransient() {
		// test without transient variables
		MyValueObject mvo = new MyValueObject();
		Map<String, Object> map = ReflectionUtils.convertToMap(mvo, true);

		Assert.assertNotNull(map);
		Assert.assertEquals(18, map.size());

		Assert.assertTrue(map.containsKey("bite"));
		Assert.assertTrue(map.containsKey("chr"));
		Assert.assertTrue(map.containsKey("shrt"));
		Assert.assertTrue(map.containsKey("nt"));
		Assert.assertTrue(map.containsKey("lng"));
		Assert.assertTrue(map.containsKey("flt"));
		Assert.assertTrue(map.containsKey("dbl"));
		Assert.assertTrue(map.containsKey("bln"));
		Assert.assertTrue(map.containsKey("strng"));
		Assert.assertTrue(map.containsKey("intArray"));
		Assert.assertTrue(map.containsKey("charArray"));
		Assert.assertTrue(map.containsKey("byteArray"));
		Assert.assertTrue(map.containsKey("shortArray"));
		Assert.assertTrue(map.containsKey("longArray"));
		Assert.assertTrue(map.containsKey("floatArray"));
		Assert.assertTrue(map.containsKey("doubleArray"));
		Assert.assertTrue(map.containsKey("booleanArray"));
		Assert.assertTrue(map.containsKey("notToBeSerialized"));

		Assert.assertEquals(mvo.bite, map.get("bite"));
		Assert.assertEquals(mvo.chr, map.get("chr"));
		Assert.assertEquals(mvo.shrt, map.get("shrt"));
		Assert.assertEquals(mvo.nt, map.get("nt"));
		Assert.assertEquals(mvo.lng, map.get("lng"));
		Assert.assertEquals(mvo.flt, map.get("flt"));
		Assert.assertEquals(mvo.dbl, map.get("dbl"));
		Assert.assertEquals(mvo.bln, map.get("bln"));
		Assert.assertEquals(mvo.strng, map.get("strng"));
		Assert.assertEquals(mvo.intArray, map.get("intArray"));
		Assert.assertEquals(mvo.charArray, map.get("charArray"));
		Assert.assertEquals(mvo.byteArray, map.get("byteArray"));
		Assert.assertEquals(mvo.shortArray, map.get("shortArray"));
		Assert.assertEquals(mvo.longArray, map.get("longArray"));
		Assert.assertEquals(mvo.floatArray, map.get("floatArray"));
		Assert.assertEquals(mvo.doubleArray, map.get("doubleArray"));
		Assert.assertEquals(mvo.booleanArray, map.get("booleanArray"));
		Assert.assertEquals(mvo.notToBeSerialized, map.get("notToBeSerialized"));
	}

	private class MyValueObject {

		private byte bite = 10;

		private char chr = 'A';

		private short shrt = 11;

		private int nt = 12;

		private long lng = 13;

		private float flt = 14f;

		private double dbl = 15d;

		private boolean bln = true;

		private String strng = "Hello World!";

		private int[] intArray = new int[] { 11, 12, 13 };

		private char[] charArray = new char[] { 'a', 'b', 'c' };

		private byte[] byteArray = new byte[] { 110, 111, 112 };

		private short[] shortArray = new short[] { 211, 212, 213 };

		private long[] longArray = new long[] { 314, 415, 516 };

		private float[] floatArray = new float[] { 3f, 4f, 5f };

		private double[] doubleArray = new double[] { 6d, 7d, 8d };

		private boolean[] booleanArray = new boolean[] { true, false, true };

		private transient int notToBeSerialized = 66;

	}
}

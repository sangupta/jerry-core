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

import org.junit.Assert;
import org.junit.Test;

public class TestObjectUtils {
	
	@Test
	public void testIsPrimitive() {
		Assert.assertFalse(ObjectUtils.isPrimitive(null));
		
		Assert.assertTrue(ObjectUtils.isPrimitive((byte) 1));
		Assert.assertTrue(ObjectUtils.isPrimitive((char) 1));
		Assert.assertTrue(ObjectUtils.isPrimitive((short) 1));
		Assert.assertTrue(ObjectUtils.isPrimitive((int) 1));
		Assert.assertTrue(ObjectUtils.isPrimitive((long) 1));
		Assert.assertTrue(ObjectUtils.isPrimitive((float) 1));
		Assert.assertTrue(ObjectUtils.isPrimitive((double) 1));
		Assert.assertTrue(ObjectUtils.isPrimitive(true));
		
		Assert.assertFalse(ObjectUtils.isPrimitive(""));
		Assert.assertFalse(ObjectUtils.isPrimitive(new String()));
		Assert.assertFalse(ObjectUtils.isPrimitive(new Object()));
		Assert.assertFalse(ObjectUtils.isPrimitive(new int[] { }));
	}
	
	@Test
	public void testIsPrimitiveArray() {
		Assert.assertFalse(ObjectUtils.isPrimitiveArray(null));
		
		Assert.assertFalse(ObjectUtils.isPrimitiveArray((byte) 1));
		Assert.assertFalse(ObjectUtils.isPrimitiveArray((char) 1));
		Assert.assertFalse(ObjectUtils.isPrimitiveArray((short) 1));
		Assert.assertFalse(ObjectUtils.isPrimitiveArray((int) 1));
		Assert.assertFalse(ObjectUtils.isPrimitiveArray((long) 1));
		Assert.assertFalse(ObjectUtils.isPrimitiveArray((float) 1));
		Assert.assertFalse(ObjectUtils.isPrimitiveArray((double) 1));
		Assert.assertFalse(ObjectUtils.isPrimitiveArray(true));
		
		Assert.assertFalse(ObjectUtils.isPrimitiveArray(""));
		Assert.assertFalse(ObjectUtils.isPrimitiveArray(new String()));
		Assert.assertFalse(ObjectUtils.isPrimitiveArray(new String[] { }));
		Assert.assertFalse(ObjectUtils.isPrimitiveArray(new Object[] { }));
		
		Assert.assertTrue(ObjectUtils.isPrimitiveArray(new byte[] { }));
		Assert.assertTrue(ObjectUtils.isPrimitiveArray(new char[] { }));
		Assert.assertTrue(ObjectUtils.isPrimitiveArray(new short[] { }));
		Assert.assertTrue(ObjectUtils.isPrimitiveArray(new int[] { }));
		Assert.assertTrue(ObjectUtils.isPrimitiveArray(new long[] { }));
		Assert.assertTrue(ObjectUtils.isPrimitiveArray(new float[] { }));
		Assert.assertTrue(ObjectUtils.isPrimitiveArray(new double[] { }));
		Assert.assertTrue(ObjectUtils.isPrimitiveArray(new boolean[] { }));
	}
	
	@Test
	public void testIsBoxedPrimitiveArray() {
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(null));
		
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray((byte) 1));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray((char) 1));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray((short) 1));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray((int) 1));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray((long) 1));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray((float) 1));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray((double) 1));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(true));
		
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(""));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new String()));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new String[] { }));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new Object[] { }));
		
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new byte[] { }));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new char[] { }));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new short[] { }));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new int[] { }));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new long[] { }));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new float[] { }));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new double[] { }));
		Assert.assertFalse(ObjectUtils.isBoxedPrimitiveArray(new boolean[] { }));
		
		Assert.assertTrue(ObjectUtils.isBoxedPrimitiveArray(new Byte[] { }));
		Assert.assertTrue(ObjectUtils.isBoxedPrimitiveArray(new Character[] { }));
		Assert.assertTrue(ObjectUtils.isBoxedPrimitiveArray(new Short[] { }));
		Assert.assertTrue(ObjectUtils.isBoxedPrimitiveArray(new Integer[] { }));
		Assert.assertTrue(ObjectUtils.isBoxedPrimitiveArray(new Long[] { }));
		Assert.assertTrue(ObjectUtils.isBoxedPrimitiveArray(new Float[] { }));
		Assert.assertTrue(ObjectUtils.isBoxedPrimitiveArray(new Double[] { }));
		Assert.assertTrue(ObjectUtils.isBoxedPrimitiveArray(new Boolean[] { }));
	}

}
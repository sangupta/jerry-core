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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ListUtils}.
 * 
 * @author sangupta
 *
 */
public class TestListUtils {
	
	@Test
	public void testListOfInteger() {
		int[] array = null;
		List<Integer> list = ListUtils.listOf(array);
		Assert.assertNull(list);
		
		array = new int[] { };
		list = ListUtils.listOf(array);
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
		
		array = new int[] { 1, 2, 3 };
		list = ListUtils.listOf(array);
		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());
		Assert.assertEquals(1, (int) list.get(0));
		Assert.assertEquals(2, (int) list.get(1));
		Assert.assertEquals(3, (int) list.get(2));
	}
	
	@Test
	public void testListOfLong() {
		long[] array = null;
		List<Long> list = ListUtils.listOf(array);
		Assert.assertNull(list);
		
		array = new long[] { };
		list = ListUtils.listOf(array);
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
		
		array = new long[] { 1, 2, 3 };
		list = ListUtils.listOf(array);
		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());
		Assert.assertEquals(1, (long) list.get(0));
		Assert.assertEquals(2, (long) list.get(1));
		Assert.assertEquals(3, (long) list.get(2));
	}
	
	@Test
	public void testListOfFloat() {
		float[] array = null;
		List<Float> list = ListUtils.listOf(array);
		Assert.assertNull(list);
		
		array = new float[] { };
		list = ListUtils.listOf(array);
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
		
		array = new float[] { 1, 2, 3 };
		list = ListUtils.listOf(array);
		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());
		Assert.assertEquals(1f, (float) list.get(0), 0f);
		Assert.assertEquals(2f, (float) list.get(1), 0f);
		Assert.assertEquals(3f, (float) list.get(2), 0f);
	}
	
	@Test
	public void testListOfDouble() {
		double[] array = null;
		List<Double> list = ListUtils.listOf(array);
		Assert.assertNull(list);
		
		array = new double[] { };
		list = ListUtils.listOf(array);
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
		
		array = new double[] { 1, 2, 3 };
		list = ListUtils.listOf(array);
		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());
		Assert.assertEquals(1d, (double) list.get(0), 0d);
		Assert.assertEquals(2d, (double) list.get(1), 0d);
		Assert.assertEquals(3d, (double) list.get(2), 0d);
	}
	
	@Test
	public void testListOfString() {
		String[] array = null;
		List<String> list = ListUtils.listOf(array);
		Assert.assertNull(list);
		
		array = new String[] { };
		list = ListUtils.listOf(array);
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
		
		array = new String[] { "1", "2", "3" };
		list = ListUtils.listOf(array);
		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());
		Assert.assertEquals("1", list.get(0));
		Assert.assertEquals("2", list.get(1));
		Assert.assertEquals("3", list.get(2));
	}

}

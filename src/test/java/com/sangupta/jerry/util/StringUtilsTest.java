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

import java.util.UUID;

import org.junit.Test;

import junit.framework.Assert;

/**
 * Unit tests for {@link StringUtils} class.
 * 
 * @author sangupta
 *
 */
public class StringUtilsTest {

	@Test
	public void testSubstringAfter() {
		String test ="this is a test string to test the functionality of substring";
		String from = "test";
		
		String subStr = StringUtils.substringAfter(test, from, 0);
		Assert.assertEquals(" string to test the functionality of substring", subStr);
		
		subStr = StringUtils.substringAfter(test, from, 11);
		Assert.assertEquals(" the functionality of substring", subStr);
		
		Assert.assertEquals(test, StringUtils.substringAfter(test, null, 0));
		Assert.assertEquals("", StringUtils.substringAfter(null, null, 0));
	}
	
	@Test()
	public void testSubstringBetween() {
		String test ="this is a test string to test the functionality of substring";
		
		Assert.assertEquals("", StringUtils.substringBetween(null, null, null));
		Assert.assertEquals("this", StringUtils.substringBetween(test, null, " is"));
		Assert.assertEquals("substring", StringUtils.substringBetween(test, "of ", null));
		Assert.assertEquals("test the functionality", StringUtils.substringBetween(test, "to ", " of"));
	}
	
	@Test
	public void testGetBoolean() {
		Assert.assertFalse(StringUtils.getBoolean(null));
		Assert.assertFalse(StringUtils.getBoolean(""));
		Assert.assertFalse(StringUtils.getBoolean("abc"));
		Assert.assertFalse(StringUtils.getBoolean("no"));
		
		Assert.assertTrue(StringUtils.getBoolean("yes"));
		Assert.assertTrue(StringUtils.getBoolean("yEs"));
		Assert.assertTrue(StringUtils.getBoolean("YES"));
		Assert.assertTrue(StringUtils.getBoolean("true"));
		Assert.assertTrue(StringUtils.getBoolean("tRuE"));
		Assert.assertTrue(StringUtils.getBoolean("TRUE"));
	}
	
	@Test
	public void testContains() {
		String[] array =  { "one", "two", "three" };
		Assert.assertFalse(StringUtils.contains(array, null));
		Assert.assertFalse(StringUtils.contains(array, ""));
		Assert.assertFalse(StringUtils.contains(array, "four"));
		Assert.assertFalse(StringUtils.contains(null, null));
		Assert.assertFalse(StringUtils.contains(null, ""));
		Assert.assertFalse(StringUtils.contains(null, "four"));
		
		Assert.assertTrue(StringUtils.contains(array, "one"));
		Assert.assertTrue(StringUtils.contains(array, "two"));
		Assert.assertTrue(StringUtils.contains(array, "three"));
	}
	
	@Test
	public void testAsHex() {
		Assert.assertEquals("41", StringUtils.asHex((byte) 65));
		Assert.assertEquals("00", StringUtils.asHex((byte) 0));
		Assert.assertEquals("0a", StringUtils.asHex((byte) 10));
		Assert.assertEquals("ff", StringUtils.asHex((byte) 255));
		
		byte[] bytes = "test".getBytes();
		Assert.assertEquals("74657374", StringUtils.asHex(bytes));
	}
	
	@Test
	public void testFromHex() {
		for(int i = 0; i < 1000; i++) {
			String uuid = UUID.randomUUID().toString();
			byte[] bytes = uuid.getBytes();
			String hex = StringUtils.asHex(bytes);
			byte[] reversed = StringUtils.fromHex(hex);
			Assert.assertTrue(EqualUtils.equals(bytes, reversed));
		}
	}
	
	@Test
	public void testAsString() {
		for(int i = 0; i < 1000; i++) {
			String uuid = UUID.randomUUID().toString();
			byte[] bytes = uuid.getBytes();
			String hex = StringUtils.asHex(bytes);
			Assert.assertEquals(uuid, StringUtils.asString(StringUtils.fromHex(hex)));
		}
	}
	
	@Test
	public void testNthIndexOf() {
		String string = "this is a line to test the occurrence of the word test in this test string";
		String searchString = "test";

		Assert.assertEquals(-1, StringUtils.nthIndexOf(string, "sangupta", 1));
		Assert.assertEquals(18, StringUtils.nthIndexOf(string, searchString, 1));
		Assert.assertEquals(50, StringUtils.nthIndexOf(string, searchString, 2));
		Assert.assertEquals(63, StringUtils.nthIndexOf(string, searchString, 3));
		Assert.assertEquals(-1, StringUtils.nthIndexOf(string, searchString, 4));
	}
}

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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import org.junit.Assert;

/**
 * Unit tests for {@link StringUtils} class.
 * 
 * @author sangupta
 *
 */
public class TestStringUtils {

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
		
		Assert.assertEquals("", StringUtils.substringAfter("this is a test string", "sangupta", 0));
		Assert.assertEquals("", StringUtils.substringAfter("this is a test string", "sangupta", 5));
		Assert.assertEquals("", StringUtils.substringAfter("this is a test string", "sangupta", 10));
	}
	
	@Test()
	public void testSubstringBetween() {
		String test ="this is a test string to test the functionality of substring";
		
		Assert.assertEquals("", StringUtils.substringBetween(null, null, null));
		Assert.assertEquals("this", StringUtils.substringBetween(test, null, " is"));
		Assert.assertEquals("substring", StringUtils.substringBetween(test, "of ", null));
		Assert.assertEquals("test the functionality", StringUtils.substringBetween(test, "to ", " of"));
		
		Assert.assertEquals("hello", StringUtils.substringBetween("hello world!", "hello", " world", 3));
		Assert.assertEquals("hello world!", StringUtils.substringBetween("hello world!", "hello", " jerry", 3));
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
		
		Assert.assertFalse(StringUtils.contains(new String[] { }, "one"));
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
		Assert.assertNull(StringUtils.fromHex(null));
		Assert.assertNull(StringUtils.fromHex(""));
		
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
		
		Assert.assertNull(StringUtils.asString(null));
		Assert.assertEquals("", StringUtils.asString(new byte[] { }));
	}
	
	@Test
	public void testAsStringUTF8() {
		for(int i = 0; i < 1000; i++) {
			String uuid = UUID.randomUUID().toString();
			byte[] bytes = uuid.getBytes(StringUtils.CHARSET_UTF8);
			String hex = StringUtils.asHex(bytes);
			Assert.assertEquals(uuid, StringUtils.asStringUTF8(StringUtils.fromHex(hex)));
		}
		
		Assert.assertNull(StringUtils.asStringUTF8(null));
		Assert.assertEquals("", StringUtils.asStringUTF8(new byte[] { }));
	}
	
	@Test
	public void testNthIndexOf() {
		String string = "this is a line to test the occurrence of the word test in this test string";
		String searchString = "test";

		try {
			StringUtils.nthIndexOf(string, searchString, 0);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			StringUtils.nthIndexOf(string, searchString, -1);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(-1, StringUtils.nthIndexOf(string, "sangupta", 1));
		Assert.assertEquals(18, StringUtils.nthIndexOf(string, searchString, 1));
		Assert.assertEquals(50, StringUtils.nthIndexOf(string, searchString, 2));
		Assert.assertEquals(63, StringUtils.nthIndexOf(string, searchString, 3));
		Assert.assertEquals(-1, StringUtils.nthIndexOf(string, searchString, 4));
	}
	
	@Test
	public void testStartsWithIgnoreCase() {
		String x = "hello world!";
		Assert.assertTrue(StringUtils.startsWithIgnoreCase(x, x));
		
		Assert.assertFalse(StringUtils.startsWithIgnoreCase(null, null));
		Assert.assertFalse(StringUtils.startsWithIgnoreCase("some", null));
		Assert.assertFalse(StringUtils.startsWithIgnoreCase(null, "some"));
		Assert.assertFalse(StringUtils.startsWithIgnoreCase("", ""));
		Assert.assertFalse(StringUtils.startsWithIgnoreCase("", null));
		Assert.assertFalse(StringUtils.startsWithIgnoreCase(null, ""));
		
		Assert.assertFalse(StringUtils.startsWithIgnoreCase("some", "some text"));
		Assert.assertFalse(StringUtils.startsWithIgnoreCase("some", "come text"));
		Assert.assertFalse(StringUtils.startsWithIgnoreCase("some", "come"));
		Assert.assertFalse(StringUtils.startsWithIgnoreCase("some", "c"));
		
		Assert.assertTrue(StringUtils.startsWithIgnoreCase("some text", "some"));
		Assert.assertTrue(StringUtils.startsWithIgnoreCase("some text", "SOME"));
		Assert.assertTrue(StringUtils.startsWithIgnoreCase("some text", "SoMe"));
		
		Assert.assertTrue(StringUtils.startsWithIgnoreCase("SOME text", "some"));
		Assert.assertTrue(StringUtils.startsWithIgnoreCase("some text", "SOME"));
		Assert.assertTrue(StringUtils.startsWithIgnoreCase("sOmE text", "SoMe"));
	}
	
	@Test
	public void testEndsWithIgnoreCase() {
		String x = "hello world!";
		Assert.assertTrue(StringUtils.endsWithIgnoreCase(x, x));
		
		Assert.assertFalse(StringUtils.endsWithIgnoreCase("short string", "a very long string"));
		
		Assert.assertFalse(StringUtils.endsWithIgnoreCase(null, null));
		Assert.assertFalse(StringUtils.endsWithIgnoreCase("some", null));
		Assert.assertFalse(StringUtils.endsWithIgnoreCase(null, "some"));
		Assert.assertFalse(StringUtils.endsWithIgnoreCase("", ""));
		Assert.assertFalse(StringUtils.endsWithIgnoreCase("", null));
		Assert.assertFalse(StringUtils.endsWithIgnoreCase(null, ""));
		
		Assert.assertFalse(StringUtils.endsWithIgnoreCase("find some", "some text"));
		Assert.assertFalse(StringUtils.endsWithIgnoreCase("find some", "come text"));
		Assert.assertFalse(StringUtils.endsWithIgnoreCase("find some", "come"));
		Assert.assertFalse(StringUtils.endsWithIgnoreCase("find some", "c"));
		
		Assert.assertTrue(StringUtils.endsWithIgnoreCase("find some", "some"));
		Assert.assertTrue(StringUtils.endsWithIgnoreCase("find some", "SOME"));
		Assert.assertTrue(StringUtils.endsWithIgnoreCase("find some", "SoMe"));
		
		Assert.assertTrue(StringUtils.endsWithIgnoreCase("find SOME", "some"));
		Assert.assertTrue(StringUtils.endsWithIgnoreCase("find some", "SOME"));
		Assert.assertTrue(StringUtils.endsWithIgnoreCase("find sOmE", "SoMe"));
	}
	
	@Test
	public void testGetIntValue() {
		Assert.assertEquals(5, StringUtils.getIntValue(null, 5));
		Assert.assertEquals(5, StringUtils.getIntValue("", 5));
		Assert.assertEquals(5, StringUtils.getIntValue("abc", 5));
		
		Assert.assertEquals(63, StringUtils.getIntValue("63", 5));
		Assert.assertEquals(-63, StringUtils.getIntValue("-63", 5));
	}
	
	@Test
	public void testGetLongValue() {
		Assert.assertEquals(5l, StringUtils.getLongValue(null, 5l));
		Assert.assertEquals(5l, StringUtils.getLongValue("", 5l));
		Assert.assertEquals(5l, StringUtils.getLongValue("abc", 5l));
		
		Assert.assertEquals(63l, StringUtils.getLongValue("63", 5l));
		Assert.assertEquals(-63l, StringUtils.getLongValue("-63", 5l));
	}
	
	@Test
	public void testGetFloatValue() {
		Assert.assertEquals(5f, StringUtils.getFloatValue(null, 5f), 0f);
		Assert.assertEquals(5f, StringUtils.getFloatValue("", 5f), 0f);
		Assert.assertEquals(5f, StringUtils.getFloatValue("abc", 5f), 0f);
		
		Assert.assertEquals(63f, StringUtils.getFloatValue("63", 5f), 0f);
		Assert.assertEquals(-63f, StringUtils.getFloatValue("-63", 5f), 0f);
		
		Assert.assertEquals(63.2f, StringUtils.getFloatValue("63.200", 5f), 0f);
		Assert.assertEquals(-63.2f, StringUtils.getFloatValue("-63.200", 5f), 0f);
	}
	
	@Test
	public void testGetDoubleValue() {
		Assert.assertEquals(5d, StringUtils.getDoubleValue(null, 5d), 0d);
		Assert.assertEquals(5d, StringUtils.getDoubleValue("", 5d), 0d);
		Assert.assertEquals(5d, StringUtils.getDoubleValue("abc", 5d), 0d);
		
		Assert.assertEquals(63d, StringUtils.getDoubleValue("63", 5d), 0d);
		Assert.assertEquals(-63d, StringUtils.getDoubleValue("-63", 5d), 0d);
		
		Assert.assertEquals(63.2d, StringUtils.getDoubleValue("63.200", 5d), 0d);
		Assert.assertEquals(-63.2d, StringUtils.getDoubleValue("-63.200", 5d), 0d);
	}
	
	@Test
	public void testLastIndexBefore() {
		Assert.assertEquals(-1, StringUtils.lastIndexBefore(null, "123", "123"));
		Assert.assertEquals(-1, StringUtils.lastIndexBefore("", "123", "123"));
		
		Assert.assertEquals(-1, StringUtils.lastIndexBefore("123", null, "123"));
		Assert.assertEquals(-1, StringUtils.lastIndexBefore("123", "", "123"));
		
		Assert.assertEquals(8, StringUtils.lastIndexBefore("one two one three one", "one", "three"));
		Assert.assertEquals(18, StringUtils.lastIndexBefore("one two one three one", "one", "four"));
		
		String text = null;
		Assert.assertEquals(-1, StringUtils.lastIndexBefore(text, "num", 100));
		text = "num one num two num three num four";
		Assert.assertEquals(-1, StringUtils.lastIndexBefore(text, null, 100));
		
		Assert.assertEquals(26, StringUtils.lastIndexBefore(text, "num", -1));
		Assert.assertEquals(16, StringUtils.lastIndexBefore(text, "num", 26));
		Assert.assertEquals(0, StringUtils.lastIndexBefore(text, "num", 3));
		Assert.assertEquals(-1, StringUtils.lastIndexBefore(text, "4", 3));
	}
	
	@Test
	public void testListToString() {
		Assert.assertEquals("", StringUtils.listToString(null, ","));
		Assert.assertEquals("", StringUtils.listToString(new ArrayList<String>(), ","));
		
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		
		Assert.assertEquals("one$#two$#three", StringUtils.listToString(list, "$#"));
		Assert.assertEquals("onetwothree", StringUtils.listToString(list, null));
		Assert.assertEquals("onetwothree", StringUtils.listToString(list, ""));
	}

	@Test
	public void testConvertToJsonPropertyName() {
		Assert.assertEquals("one", StringUtils.convertToJsonPropertyName("one"));
		Assert.assertEquals("one-two", StringUtils.convertToJsonPropertyName("one-two"));
		Assert.assertEquals("one_two", StringUtils.convertToJsonPropertyName("one_two"));
		Assert.assertEquals("one_two", StringUtils.convertToJsonPropertyName("one two"));
		Assert.assertEquals("one_two", StringUtils.convertToJsonPropertyName("one)two"));
		Assert.assertEquals("one_two", StringUtils.convertToJsonPropertyName("one$two"));
		Assert.assertEquals("one-2", StringUtils.convertToJsonPropertyName("one-2"));
		Assert.assertEquals("one_2", StringUtils.convertToJsonPropertyName("one$2"));
	}
	
	@Test
	public void testConvertToXmlPropertyName() {
		Assert.assertEquals("one", StringUtils.convertToXmlPropertyName("one"));
		Assert.assertEquals("one-two", StringUtils.convertToXmlPropertyName("one-two"));
		Assert.assertEquals("one_two", StringUtils.convertToXmlPropertyName("one_two"));
		Assert.assertEquals("one-two", StringUtils.convertToXmlPropertyName("one two"));
		Assert.assertEquals("one-two", StringUtils.convertToXmlPropertyName("one)two"));
		Assert.assertEquals("one-two", StringUtils.convertToXmlPropertyName("one$two"));
		Assert.assertEquals("one-2", StringUtils.convertToXmlPropertyName("one-2"));
		Assert.assertEquals("one-2", StringUtils.convertToXmlPropertyName("one$2"));
	}
	
	@Test
	public void testGetShortValue() {
		Assert.assertEquals(-1, StringUtils.getShortValue(null, (short) -1));
		Assert.assertEquals(-1, StringUtils.getShortValue("", (short) -1));
		Assert.assertEquals(-1, StringUtils.getShortValue("abc", (short) -1));
		Assert.assertEquals(15, StringUtils.getShortValue("15", (short) -1));
	}
	
	@Test
	public void testMerge() {
		Assert.assertEquals("", StringUtils.merge(null, ','));
		Assert.assertEquals("", StringUtils.merge(new String[] { }, ','));
		Assert.assertEquals("1,2,3", StringUtils.merge(new String[] { "1", "2", "3" }, ','));		

		Assert.assertEquals("", StringUtils.merge(null, ","));
		Assert.assertEquals("", StringUtils.merge(new String[] { }, ","));
		Assert.assertEquals("1,2,3", StringUtils.merge(new String[] { "1", "2", "3" }, ","));		
	}
	
	@Test
	public void testRepeat() {
		Assert.assertEquals("s", StringUtils.repeat('s', 1));
		Assert.assertEquals("ss", StringUtils.repeat('s', 2));
		Assert.assertEquals("ssssssssss", StringUtils.repeat('s', 10));
		
		// check exceptions
		try {
			StringUtils.repeat('a', 0);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		try {
			StringUtils.repeat('a', -5);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testGetRandomString() {
		// check exceptions
		try {
			StringUtils.getRandomString(0);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		try {
			StringUtils.getRandomString(-10);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testWildcardMatch() {
		Assert.assertTrue(StringUtils.wildcardMatch("abc.wav", "*"));
		Assert.assertTrue(StringUtils.wildcardMatch("abc.wav", "*.w?v"));
		Assert.assertTrue(StringUtils.wildcardMatch("abc.wav", "*b?.wav"));
		Assert.assertTrue(StringUtils.wildcardMatch("abc.wav", "*.wav"));
		Assert.assertTrue(StringUtils.wildcardMatch("abc.wav", "*abc.wav"));
		Assert.assertTrue(StringUtils.wildcardMatch("abc.wav", "???.wav"));
		Assert.assertTrue(StringUtils.wildcardMatch("abc.wav", "???.???"));
		Assert.assertTrue(StringUtils.wildcardMatch("abc.wav", "???.???"));
		
		Assert.assertFalse(StringUtils.wildcardMatch("abc.wav", "*.html"));
		Assert.assertFalse(StringUtils.wildcardMatch("abc.wav", "?.wav"));
		Assert.assertFalse(StringUtils.wildcardMatch("abc.wav", "??.wav"));
		Assert.assertFalse(StringUtils.wildcardMatch("abc.wav", "abc.wi?"));
		
		Assert.assertTrue(StringUtils.wildcardMatch("http://sangupta.com/tech/page10/index.html", "*tech/page*"));
	}
}
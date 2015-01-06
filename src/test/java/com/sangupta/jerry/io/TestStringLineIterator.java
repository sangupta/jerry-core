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
 
package com.sangupta.jerry.io;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit tests for {@link StringLineIterator}.
 * 
 * @author sangupta
 *
 */
public class TestStringLineIterator {
	
	@Test
	public void testNewLines() {
		String input = "\n\n\n\n";
		StringLineIterator it = new StringLineIterator(input);

		Assert.assertEquals(-1, it.getLineNumber());
		Assert.assertEquals(-1, it.getLineBegin());
		Assert.assertEquals(-1, it.getLineEnd());

		// first read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("", it.next());
		Assert.assertEquals(1, it.getLineNumber());

		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("", it.next());
		Assert.assertEquals(2, it.getLineNumber());

		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("", it.next());
		Assert.assertEquals(3, it.getLineNumber());

		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("", it.next());
		Assert.assertEquals(4, it.getLineNumber());

		Assert.assertFalse(it.hasNext());
	}

	@Test
	public void testStringLineIterator1() {
		String input = "one\ntwo\nthree\nfour";
		StringLineIterator it = new StringLineIterator(input);

		Assert.assertEquals(-1, it.getLineNumber());
		Assert.assertEquals(-1, it.getLineBegin());
		Assert.assertEquals(-1, it.getLineEnd());

		// first read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("one", it.next());
		Assert.assertEquals(1, it.getLineNumber());
		Assert.assertEquals("one", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// second read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("two", it.next());
		Assert.assertEquals(2, it.getLineNumber());
		Assert.assertEquals("two", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// third read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("three", it.next());
		Assert.assertEquals(3, it.getLineNumber());
		Assert.assertEquals("three", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// fourth read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("four", it.next());
		Assert.assertEquals(4, it.getLineNumber());
		Assert.assertEquals("four", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		Assert.assertFalse(it.hasNext());
	}

	@Test
	public void testStringLineIterator2() {
		String input = "one\rtwo\rthree\rfour";
		StringLineIterator it = new StringLineIterator(input);

		Assert.assertEquals(-1, it.getLineNumber());
		Assert.assertEquals(-1, it.getLineBegin());
		Assert.assertEquals(-1, it.getLineEnd());

		// first read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("one", it.next());
		Assert.assertEquals(1, it.getLineNumber());
		Assert.assertEquals("one", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// second read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("two", it.next());
		Assert.assertEquals(2, it.getLineNumber());
		Assert.assertEquals("two", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// third read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("three", it.next());
		Assert.assertEquals(3, it.getLineNumber());
		Assert.assertEquals("three", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// fourth read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("four", it.next());
		Assert.assertEquals(4, it.getLineNumber());
		Assert.assertEquals("four", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		Assert.assertFalse(it.hasNext());
	}

	@Test
	public void testStringLineIterator3() {
		String input = "one\r\ntwo\r\nthree\r\nfour";
		StringLineIterator it = new StringLineIterator(input);

		Assert.assertEquals(-1, it.getLineNumber());
		Assert.assertEquals(-1, it.getLineBegin());
		Assert.assertEquals(-1, it.getLineEnd());

		// first read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("one", it.next());
		Assert.assertEquals(1, it.getLineNumber());
		Assert.assertEquals("one", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// second read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("two", it.next());
		Assert.assertEquals(2, it.getLineNumber());
		Assert.assertEquals("two", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// third read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("three", it.next());
		Assert.assertEquals(3, it.getLineNumber());
		Assert.assertEquals("three", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// fourth read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("four", it.next());
		Assert.assertEquals(4, it.getLineNumber());
		Assert.assertEquals("four", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		Assert.assertFalse(it.hasNext());
	}

	@Test
	public void testStringLineIterator4() {
		String input = "one\r\ntwo\nthree\r\nfour";
		StringLineIterator it = new StringLineIterator(input);

		Assert.assertEquals(-1, it.getLineNumber());
		Assert.assertEquals(-1, it.getLineBegin());
		Assert.assertEquals(-1, it.getLineEnd());

		// first read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("one", it.next());
		Assert.assertEquals(1, it.getLineNumber());
		Assert.assertEquals("one", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// second read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("two", it.next());
		Assert.assertEquals(2, it.getLineNumber());
		Assert.assertEquals("two", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// third read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("three", it.next());
		Assert.assertEquals(3, it.getLineNumber());
		Assert.assertEquals("three", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		// fourth read
		Assert.assertTrue(it.hasNext());
		Assert.assertEquals("four", it.next());
		Assert.assertEquals(4, it.getLineNumber());
		Assert.assertEquals("four", input.substring(it.getLineBegin(), it.getLineEnd()));
		
		Assert.assertFalse(it.hasNext());
	}

}

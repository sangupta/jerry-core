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

package com.sangupta.jerry.ds.iterator;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link StringArrayIterator} class.
 * 
 * @author sangupta
 *
 */
public class TestStringArrayIterator {

	@Test
	public void test() {
		StringArrayIterator iterator = new StringArrayIterator(null);
		Assert.assertNotNull(iterator);
		Assert.assertFalse(iterator.hasNext());
		Assert.assertNull(iterator.peek());
		
		iterator = new StringArrayIterator(new String[] { });
		Assert.assertNotNull(iterator);
		Assert.assertFalse(iterator.hasNext());
		Assert.assertNull(iterator.peek());
		
		iterator = new StringArrayIterator(new String[] { "hello" });
		Assert.assertNotNull(iterator);
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals("hello", iterator.peek());
		Assert.assertEquals("hello", iterator.peek());
		Assert.assertEquals("hello", iterator.peek());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals("hello", iterator.next());
		Assert.assertFalse(iterator.hasNext());
		Assert.assertNull(iterator.peek());
		
		iterator = new StringArrayIterator(new String[] { "hello", "world" });
		Assert.assertNotNull(iterator);
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals("hello", iterator.peek());
		Assert.assertEquals("hello", iterator.peek());
		Assert.assertEquals("hello", iterator.peek());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals("hello", iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals("world", iterator.next());
		Assert.assertFalse(iterator.hasNext());
		Assert.assertNull(iterator.peek());
		
		iterator = new StringArrayIterator(new String[] { "hello", "world" });
		try {
			Assert.assertTrue(iterator.hasNext());
			iterator.remove();
			Assert.assertTrue(false);
		} catch(UnsupportedOperationException e) {
			Assert.assertTrue(true);
		}
	}
}

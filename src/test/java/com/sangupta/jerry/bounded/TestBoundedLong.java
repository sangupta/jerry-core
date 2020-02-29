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


package com.sangupta.jerry.bounded;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link BoundedLong} class
 *
 * @author sangupta
 *
 */
public class TestBoundedLong {

	@Test
	public void testBounds() {
		BoundedLong bounded = new BoundedLong(5, 0, 10);

		Assert.assertEquals(5, bounded.get());
		Assert.assertEquals(10, bounded.set(15));
		Assert.assertEquals(9, bounded.set(9));
		Assert.assertEquals(10, bounded.increment());
		Assert.assertEquals(10, bounded.increment());

		Assert.assertEquals(0, bounded.set(-10));
		Assert.assertEquals(0, bounded.set(0));
		Assert.assertEquals(0, bounded.decrement());
		Assert.assertEquals(0, bounded.decrement());

		Assert.assertEquals(1, bounded.increment());
		Assert.assertEquals(0, bounded.decrement());

		// test checkAndSet
		Assert.assertEquals(5, bounded.set(5));
		Assert.assertEquals(5, bounded.checkAndSet(20));
		Assert.assertEquals(5, bounded.checkAndSet(-20));
		Assert.assertEquals(7, bounded.checkAndSet(7));

		// add/subtract/multiply/divide
		Assert.assertEquals(5, bounded.set(5));
		Assert.assertEquals(7, bounded.add(2));
		Assert.assertEquals(5, bounded.subtract(2));

		Assert.assertEquals(4, bounded.set(4));
		Assert.assertEquals(8, bounded.multiply(2));
		Assert.assertEquals(4, bounded.divide(2));

		Assert.assertEquals(10, bounded.multiply(10));
		Assert.assertEquals(0, bounded.divide(-5));

		// exception
		try {
			bounded = new BoundedLong(5, 20, 10);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
}

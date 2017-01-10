/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2017, Sandeep Gupta
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

/**
 * Unit tests for {@link RangeUtils} class
 *
 * @author sangupta
 *
 */
public class TestRangeUtils {

	@Test
	public void testInt() {
		Assert.assertTrue(RangeUtils.isInt(0, 0, 5));
		Assert.assertTrue(RangeUtils.isInt(5, 0, 5));
		Assert.assertTrue(RangeUtils.isInt(3, 0, 5));
		Assert.assertFalse(RangeUtils.isInt(-1, 0, 5));
		Assert.assertFalse(RangeUtils.isInt(6, 0, 5));
	}

	@Test
	public void testLong() {
		Assert.assertTrue(RangeUtils.isLong(0, 0, 5));
		Assert.assertTrue(RangeUtils.isLong(5, 0, 5));
		Assert.assertTrue(RangeUtils.isLong(3, 0, 5));
		Assert.assertFalse(RangeUtils.isLong(-1, 0, 5));
		Assert.assertFalse(RangeUtils.isLong(6, 0, 5));
	}

	@Test
	public void testChar() {
		Assert.assertTrue(RangeUtils.isChar('a', 'a', 'z'));
		Assert.assertTrue(RangeUtils.isChar('z', 'a', 'z'));
		Assert.assertTrue(RangeUtils.isChar('c', 'a', 'z'));
		Assert.assertFalse(RangeUtils.isChar('0', 'a', 'z'));
		Assert.assertFalse(RangeUtils.isChar('A', 'a', 'z'));
	}

}

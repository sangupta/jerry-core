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


package com.sangupta.jerry.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link MutableInt} class
 *
 * @author sangupta
 *
 */
public class TestMutableInt {

	@Test
	public void testMutableInt() {
		MutableInt mutableInt = new MutableInt();
		Assert.assertEquals(0, mutableInt.get());
		mutableInt.set(50);
		Assert.assertEquals(50, mutableInt.get());

		mutableInt.setIfMax(100);
		Assert.assertEquals(100, mutableInt.get());
		mutableInt.setIfMax(75);
		Assert.assertEquals(100, mutableInt.get());

		mutableInt.setIfMin(50);
		Assert.assertEquals(50, mutableInt.get());
		mutableInt.setIfMin(75);
		Assert.assertEquals(50, mutableInt.get());

		mutableInt = new MutableInt(16);
		Assert.assertEquals(16, mutableInt.get());
	}
}

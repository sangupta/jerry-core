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
 * Unit tests for {@link MutableLong} class
 *
 * @author sangupta
 *
 */
public class TestMutableLong {

	@Test
	public void testMutableLong() {
		MutableLong mutableLong = new MutableLong();

		Assert.assertEquals(0, mutableLong.get());
		mutableLong.set(50);
		Assert.assertEquals(50, mutableLong.get());

		mutableLong.setIfMax(100);
		Assert.assertEquals(100, mutableLong.get());
		mutableLong.setIfMax(75);
		Assert.assertEquals(100, mutableLong.get());

		mutableLong.setIfMin(50);
		Assert.assertEquals(50, mutableLong.get());
		mutableLong.setIfMin(75);
		Assert.assertEquals(50, mutableLong.get());

		mutableLong = new MutableLong(16l);
		Assert.assertEquals(16l, mutableLong.get());
	}
}

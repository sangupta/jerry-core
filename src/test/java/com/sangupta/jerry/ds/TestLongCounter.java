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
 
package com.sangupta.jerry.ds;

import java.util.Random;

import org.junit.Assert;

import org.junit.Test;

import com.sangupta.jerry.ds.counter.LongCounter;

/**
 * Test cases for {@link LongCounter} class.
 * 
 * @author sangupta
 *
 */
public class TestLongCounter {
	
	@Test
	public void testIntegerCounter() {
		LongCounter counter = new LongCounter();
		
		// get the value of a counter
		Assert.assertEquals(0, counter.get("test"));
		
		// add some random values to it
		int max = getRandomValue();
		for(int index = 0; index < max; index++) {
			Assert.assertEquals(index + 1, counter.increment("test"));
		}

		for(int index = max; index > 0; index--) {
			Assert.assertEquals(index - 1, counter.decrement("test"));
		}
		
		// test removal
		Assert.assertEquals(0, counter.remove("test"));
		Assert.assertEquals(0, counter.get("test"));
		
		// test for new counters with one of the methods
		Assert.assertEquals(1, counter.increment("test1"));
		Assert.assertEquals(-1, counter.decrement("test2"));
	}

	private int getRandomValue() {
		Random random = new Random();
		return random.nextInt(10000) + 1000 * 1000;
	}
	
}

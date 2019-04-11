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


package com.sangupta.jerry.ds;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;

import org.junit.Test;

import com.sangupta.jerry.ds.counter.IntegerCounter;

/**
 * Test cases for {@link IntegerCounter} class.
 *
 * @author sangupta
 *
 */
public class TestIntegerCounter {

	@Test
	public void testIntegerCounter() {
		IntegerCounter counter = new IntegerCounter();

		// get the value of a counter
		Assert.assertEquals(0, counter.get("test"));
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
		Assert.assertEquals(0, counter.remove("test"));
		Assert.assertEquals(0, counter.remove("test"));

		// test for new counters with one of the methods
		Assert.assertEquals(1, counter.increment("test1"));
		Assert.assertEquals(-1, counter.decrement("test2"));

		// test for set
		counter.set("txt", 15);
		Assert.assertEquals(15, counter.get("txt"));
		counter.increment("txt");
		counter.set("txt", 15);
		Assert.assertEquals(15, counter.get("txt"));
	}
	
	@Test
	public void testMultipleCounters() {
		Random random = new SecureRandom();
		int iterations = random.nextInt(50) + 10;
		IntegerCounter counter = new IntegerCounter();

		Map<String, Integer> values = new HashMap<>();
		for(int index = 0; index < iterations; index++) {
			String name = UUID.randomUUID().toString();
			int value = random.nextInt(1000 * 1000) + 1000;
			
			values.put(name, value);
			counter.set(name, value);
			
			Assert.assertEquals(value, counter.get(name));
			Assert.assertEquals(index + 1, counter.numCounters());
			Assert.assertEquals(index + 1, counter.asMap().size());
			Assert.assertEquals(index + 1, counter.counterNames().size());
		}
		
		// check map
		Set<String> counters = counter.counterNames();
		for(String name : counters) {
			Assert.assertTrue(values.containsKey(name));
		}
		Map<String, Integer> map = counter.asMap();
		for(String name : map.keySet()) {
			Assert.assertEquals(values.get(name), map.get(name));
		}
		
		counter.clear();
		Assert.assertEquals(0, counter.numCounters());
		Assert.assertEquals(0, counter.asMap().size());
		Assert.assertEquals(0, counter.counterNames().size());
	}

	@Test
	public void testExceptions() {
		IntegerCounter counter = new IntegerCounter();

		// get(String)
		try {
			counter.get(null);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			counter.get("");
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		// get(String, int)
		try {
			counter.get(null, 15);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			counter.get("", 15);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}


		// set(String, int)
		try {
			counter.get(null, 15);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			counter.get("", 15);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}

	private int getRandomValue() {
		Random random = new Random();
		return random.nextInt(10000);
	}

}

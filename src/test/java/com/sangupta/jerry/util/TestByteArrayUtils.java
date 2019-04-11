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


package com.sangupta.jerry.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link ByteArrayUtils}.
 *
 * @author sangupta
 *
 */
public class TestByteArrayUtils {

	@Test
	public void testExceptions() {
		// during read
		try {
			ByteArrayUtils.readLong(null, 0);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			byte[] bytes = new byte[100];
			ByteArrayUtils.readLong(bytes, 120);
			Assert.assertTrue(false);
		} catch(IndexOutOfBoundsException e) {
			Assert.assertTrue(true);
		}

		try {
			byte[] bytes = new byte[100];
			ByteArrayUtils.readLong(bytes, -5);
			Assert.assertTrue(false);
		} catch(IndexOutOfBoundsException e) {
			Assert.assertTrue(true);
		}

		// during write
		try {
			ByteArrayUtils.writeLong(null, 3l, 0);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}

		try {
			byte[] bytes = new byte[100];
			ByteArrayUtils.writeLong(bytes, 3l, 120);
			Assert.assertTrue(false);
		} catch(IndexOutOfBoundsException e) {
			Assert.assertTrue(true);
		}

		try {
			byte[] bytes = new byte[100];
			ByteArrayUtils.writeLong(bytes, 3l, -5);
			Assert.assertTrue(false);
		} catch(IndexOutOfBoundsException e) {
			Assert.assertTrue(true);
		}

		// during cardinality
		try {
			ByteArrayUtils.cardinality(null);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testLongConversions() {
		byte[] bytes = new byte[8];

		// run for random 1-million long values
		for(int index = 0; index < 1000 * 1000; index++) {
			double rand = Math.random();
			long num = (long) (((double) Long.MAX_VALUE) * rand);
			ByteArrayUtils.writeLong(bytes, num, 0);
			long read = ByteArrayUtils.readLong(bytes, 0);
			Assert.assertEquals(num, read);
		}
	}

	@Test
	public void testCardinality() {
		byte[] bytes = new byte[8];

		// run for random 1-million long values
		for(int index = 0; index < 1000 * 1000; index++) {
			double rand = Math.random();
			long num = (long) (((double) Long.MAX_VALUE) * rand);
			ByteArrayUtils.writeLong(bytes, num, 0);

			// now test
			long actual = ByteArrayUtils.cardinality(bytes);

			long expected = 0;
			for(int bitPos = 0; bitPos < 64; bitPos++) {
				if(BitUtils.isBitSet(num, bitPos)) {
					expected++;
				}
			}

			Assert.assertEquals(expected, actual);
			Assert.assertEquals(0, ByteArrayUtils.cardinality(new byte[] { }));
		}
	}

}

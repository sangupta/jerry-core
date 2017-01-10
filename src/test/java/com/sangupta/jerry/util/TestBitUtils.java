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
 * Unit tests for {@link BitUtils}.
 *
 * @author sangupta
 *
 */
public class TestBitUtils {

	@Test
	public void testBitSetInt() {
		int i = 2;
		Assert.assertTrue(BitUtils.isBitSet(i, 1));
		Assert.assertFalse(BitUtils.isBitSet(i, 3));
		i = BitUtils.setBit(i, 3);
		Assert.assertTrue(BitUtils.isBitSet(i, 3));
		i = BitUtils.clearBit(i, 3);
		Assert.assertFalse(BitUtils.isBitSet(i, 3));
	}

	@Test
	public void testBitSetLong() {
		long l = 2;
		Assert.assertTrue(BitUtils.isBitSet(l, 1));
		Assert.assertFalse(BitUtils.isBitSet(l, 3));
		l = BitUtils.setBit(l, 3);
		Assert.assertTrue(BitUtils.isBitSet(l, 3));
		l = BitUtils.clearBit(l, 3);
		Assert.assertFalse(BitUtils.isBitSet(l, 3));
	}

	@Test
	public void testGetHighestBitSetIndexByte() {
		for(int bit = 7; bit >= 0; bit--) {
			int first = (1 << (bit + 1)) - 1;
			int second = 1 << bit;

			Assert.assertEquals(bit, BitUtils.getHighestSetBitIndex((byte) first));
			Assert.assertEquals(bit, BitUtils.getHighestSetBitIndex((byte) second));
		}

		Assert.assertEquals(0, BitUtils.getHighestSetBitIndex((byte) 1));
		Assert.assertEquals(-1, BitUtils.getHighestSetBitIndex((byte) 0));
	}

	@Test
	public void testGetHighestBitSetIndexShort() {
		for(int bit = 15; bit >= 0; bit--) {
			int first = (1 << (bit + 1)) - 1;
			int second = 1 << bit;

			Assert.assertEquals(bit, BitUtils.getHighestSetBitIndex((short) first));
			Assert.assertEquals(bit, BitUtils.getHighestSetBitIndex((short) second));
		}

		Assert.assertEquals(0, BitUtils.getHighestSetBitIndex((short) 1));
		Assert.assertEquals(-1, BitUtils.getHighestSetBitIndex((short) 0));
	}

	@Test
	public void testGetHighestBitSetIndexInt() {
		Assert.assertEquals(31, BitUtils.getHighestSetBitIndex(Integer.MAX_VALUE + 1));

		for(int bit = 30; bit >= 0; bit--) {
			int first = (1 << (bit + 1)) - 1;
			int second = 1 << bit;

			Assert.assertEquals(bit, BitUtils.getHighestSetBitIndex((int) first));
			Assert.assertEquals(bit, BitUtils.getHighestSetBitIndex((int) second));
		}

		Assert.assertEquals(0, BitUtils.getHighestSetBitIndex((int) 1));
		Assert.assertEquals(-1, BitUtils.getHighestSetBitIndex((int) 0));
	}

	@Test
	public void testGetHighestBitSetIndexLong() {
		Assert.assertEquals(63, BitUtils.getHighestSetBitIndex(Long.MAX_VALUE + 1));
		Assert.assertEquals(62, BitUtils.getHighestSetBitIndex(Long.MAX_VALUE));

		for(long bit = 62; bit >= 0; bit--) {
			long first = (1l << (bit + 1l)) - 1l;
			long second = 1l << bit;

			Assert.assertEquals(bit, BitUtils.getHighestSetBitIndex((long) first));
			Assert.assertEquals(bit, BitUtils.getHighestSetBitIndex((long) second));
		}

		Assert.assertEquals(0, BitUtils.getHighestSetBitIndex((long) 1));
		Assert.assertEquals(-1, BitUtils.getHighestSetBitIndex((long) 0));
	}

	@Test
	public void testGetLowestBitSetIndexByte() {
		for(int bit = 7; bit >= 0; bit--) {
			int first = (1 << (bit + 1)) - 1;
			int second = 1 << bit;

			Assert.assertEquals(0, BitUtils.getLowestSetBitIndex((byte) first));
			Assert.assertEquals(bit, BitUtils.getLowestSetBitIndex((byte) second));
		}

		Assert.assertEquals(0, BitUtils.getLowestSetBitIndex((byte) 1));
		Assert.assertEquals(-1, BitUtils.getLowestSetBitIndex((byte) 0));
	}

	@Test
	public void testGetLowestBitSetIndexShort() {
		for(int bit = 15; bit >= 0; bit--) {
			int first = (1 << (bit + 1)) - 1;
			int second = 1 << bit;

			Assert.assertEquals(0, BitUtils.getLowestSetBitIndex((short) first));
			Assert.assertEquals(bit, BitUtils.getLowestSetBitIndex((short) second));
		}

		Assert.assertEquals(0, BitUtils.getLowestSetBitIndex((short) 1));
		Assert.assertEquals(-1, BitUtils.getLowestSetBitIndex((short) 0));
	}

	@Test
	public void testGetLowestBitSetIndexInt() {
		Assert.assertEquals(31, BitUtils.getHighestSetBitIndex(Integer.MAX_VALUE + 1));

		for(int bit = 30; bit >= 0; bit--) {
			int first = (1 << (bit + 1)) - 1;
			int second = 1 << bit;

			Assert.assertEquals(0, BitUtils.getLowestSetBitIndex((int) first));
			Assert.assertEquals(bit, BitUtils.getLowestSetBitIndex((int) second));
		}

		Assert.assertEquals(0, BitUtils.getLowestSetBitIndex((int) 1));
		Assert.assertEquals(-1, BitUtils.getLowestSetBitIndex((int) 0));
	}

	@Test
	public void testGetLowestBitSetIndexLong() {
		Assert.assertEquals(63, BitUtils.getLowestSetBitIndex(Long.MAX_VALUE + 1));
		Assert.assertEquals(0, BitUtils.getLowestSetBitIndex(Long.MAX_VALUE));

		for(long bit = 62; bit >= 0; bit--) {
			long first = (1l << (bit + 1l)) - 1l;
			long second = 1l << bit;

			Assert.assertEquals(0, BitUtils.getLowestSetBitIndex((long) first));
			Assert.assertEquals(bit, BitUtils.getLowestSetBitIndex((long) second));
		}

		Assert.assertEquals(0, BitUtils.getLowestSetBitIndex((long) 1));
		Assert.assertEquals(-1, BitUtils.getLowestSetBitIndex((long) 0));
	}


}

package com.sangupta.jerry.util;

import org.junit.Assert;

import org.junit.Test;

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
			
			Assert.assertEquals(bit, BitUtils.getHighestBitSetIndex((byte) first));
			Assert.assertEquals(bit, BitUtils.getHighestBitSetIndex((byte) second));
		}

		Assert.assertEquals(0, BitUtils.getHighestBitSetIndex((byte) 1));
		Assert.assertEquals(-1, BitUtils.getHighestBitSetIndex((byte) 0));
	}
	
	@Test
	public void testGetHighestBitSetIndexShort() {
		for(int bit = 15; bit >= 0; bit--) {
			int first = (1 << (bit + 1)) - 1;
			int second = 1 << bit;
			
			Assert.assertEquals(bit, BitUtils.getHighestBitSetIndex((short) first));
			Assert.assertEquals(bit, BitUtils.getHighestBitSetIndex((short) second));
		}

		Assert.assertEquals(0, BitUtils.getHighestBitSetIndex((short) 1));
		Assert.assertEquals(-1, BitUtils.getHighestBitSetIndex((short) 0));
	}

	@Test
	public void testGetHighestBitSetIndexInt() {
		Assert.assertEquals(31, BitUtils.getHighestBitSetIndex(Integer.MAX_VALUE + 1));

		for(int bit = 30; bit >= 0; bit--) {
			int first = (1 << (bit + 1)) - 1;
			int second = 1 << bit;
			
			Assert.assertEquals(bit, BitUtils.getHighestBitSetIndex((int) first));
			Assert.assertEquals(bit, BitUtils.getHighestBitSetIndex((int) second));
		}

		Assert.assertEquals(0, BitUtils.getHighestBitSetIndex((int) 1));
		Assert.assertEquals(-1, BitUtils.getHighestBitSetIndex((int) 0));
	}

	@Test
	public void testGetHighestBitSetIndexLong() {
		Assert.assertEquals(63, BitUtils.getHighestSetBitIndex(Long.MAX_VALUE + 1));
		Assert.assertEquals(62, BitUtils.getHighestSetBitIndex(Long.MAX_VALUE));
		
		for(long bit = 62; bit >= 0; bit--) {
			long first = (1l << (bit + 1l)) - 1l;
			long second = 1l << bit;
			
			Assert.assertEquals(bit, BitUtils.getHighestSetBitIndex((long) first));
			Assert.assertEquals("Failed for " + bit, bit, BitUtils.getHighestSetBitIndex((long) second));
		}

		Assert.assertEquals(0, BitUtils.getHighestSetBitIndex((long) 1));
		Assert.assertEquals(-1, BitUtils.getHighestSetBitIndex((long) 0));
	}
}

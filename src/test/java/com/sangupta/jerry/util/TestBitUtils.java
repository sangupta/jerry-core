package com.sangupta.jerry.util;

import junit.framework.Assert;

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
}

package com.sangupta.jerry.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link DateUtils}.
 * 
 * @author sangupta
 *
 */
public class TestDateUtils {

	@Test
	public void testGetDate() {
		Assert.assertNull(DateUtils.getDate(null));
		Assert.assertEquals(0, DateUtils.getDate(0l).getTime());
	}
	
	@Test
	public void testGetDifference() {
		Assert.assertEquals(0, DateUtils.getDifference(null, null));
		
		final long time = 1000l * 1000l;
		Date d1 = DateUtils.getDate(time);
		Assert.assertEquals(time, DateUtils.getDifference(null, d1));
		Assert.assertEquals(time, DateUtils.getDifference(d1, null));
		Assert.assertEquals(0, DateUtils.getDifference(d1, d1));
		
		Date d2 = DateUtils.getDate(time * 2l);
		Assert.assertEquals(time, DateUtils.getDifference(d1, d2));
		Assert.assertEquals(time, DateUtils.getDifference(d2, d1));
	}
}

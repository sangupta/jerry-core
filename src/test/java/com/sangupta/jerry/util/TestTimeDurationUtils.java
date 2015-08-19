package com.sangupta.jerry.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link TimeDurationUtils}.
 * 
 * @author sangupta
 *
 */
public class TestTimeDurationUtils {

	@Test
	public void testAgo() {
		Assert.assertEquals("less than a minute ago", TimeDurationUtils.ago(System.currentTimeMillis()));
	}
}

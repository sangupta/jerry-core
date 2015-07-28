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

}

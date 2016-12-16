package com.sangupta.jerry.ds.refresh;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link AutoRefreshable}.
 * 
 * @author sangupta
 *
 */
public class TestAutoRefreshable {

	@Test
	public void test() {
		AutoRefreshable<Long> ar = new AutoRefreshable<Long>(1000) {
			
			@Override
			public Long refresh() {
				return System.currentTimeMillis();
			}
			
		};
		
		Assert.assertNotNull(ar.get());
		long value = ar.get();
		Assert.assertEquals(value, ar.get().longValue());
		
		// wait 100 millis
		try {
			Thread.sleep(100);
			Assert.assertEquals(value, ar.get().longValue());
		} catch (InterruptedException e) {
		}
		
		// wait 200 millis
		try {
			Thread.sleep(200);
			Assert.assertEquals(value, ar.get().longValue());
		} catch (InterruptedException e) {
		}

		// wait 1000 more millis
		try {
			Thread.sleep(1000);
			Assert.assertNotEquals(value, ar.get().longValue());
		} catch (InterruptedException e) {
		}

	}
}

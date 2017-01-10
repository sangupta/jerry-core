package com.sangupta.jerry.exceptions;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link NotImplementedException}.
 * 
 * @author sangupta
 *
 */
public class TestNotImplementedException {
	
	@Test
	public void test() {
		NotImplementedException e = new NotImplementedException();
		Assert.assertNotNull(e);
		
		e = new NotImplementedException("test");
		Assert.assertNotNull(e);
		Assert.assertEquals("test", e.getMessage());
		
		final Throwable t = new Throwable();
		
		e = new NotImplementedException("test", t);
		Assert.assertNotNull(e);
		Assert.assertEquals("test", e.getMessage());
		Assert.assertTrue(t == e.getCause());
		
		e = new NotImplementedException(t);
		Assert.assertNotNull(e);
		Assert.assertTrue(t == e.getCause());
	}

}

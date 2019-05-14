package com.sangupta.jerry.exceptions;

import org.junit.Assert;
import org.junit.Test;

public class TestHttpException {

	@Test
	public void testWithoutMessage() {
		HttpException e = new HttpException(200);
		Assert.assertEquals(200, e.httpCode);
		Assert.assertNull(e.getMessage());
	}
	
	@Test
	public void testWithMessage() {
		HttpException e = new HttpException(200, "hello");
		Assert.assertEquals(200, e.httpCode);
		Assert.assertEquals("hello", e.getMessage());
	}
}

package com.sangupta.jerry.util;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.am.servlet.AmHttpServletResponse;
import com.sangupta.jerry.constants.HttpMimeType;

public class TestResponseUtils {
	
	@Test(expected = InstantiationException.class)
	public void testConstructor() throws InstantiationException {
		new ResponseUtils() { };
	}
	
	@Test
	public void testSendNoSniff() {
		AmHttpServletResponse hsr = new AmHttpServletResponse();
		Assert.assertNull(hsr.headers.getValues("X-Content-Type-Options"));
		ResponseUtils.sendNoSniff(hsr);
		Assert.assertEquals(1, hsr.headers.getValues("X-Content-Type-Options").size());
		Assert.assertEquals("nosniff", hsr.headers.getOne("X-Content-Type-Options"));
	}
	
	@Test
	public void testSendResponse() {
		// exceptions
		try { ResponseUtils.sendResponse(null, (byte[]) null, null); Assert.assertTrue(false); } catch(Exception e) { Assert.assertTrue(true); }
		try { ResponseUtils.sendResponse(new AmHttpServletResponse(), (byte[]) null, HttpMimeType.BINARY); Assert.assertTrue(false); } catch(Exception e) { Assert.assertTrue(true); }
		try { ResponseUtils.sendResponse(new AmHttpServletResponse(), "hello".getBytes(), null); Assert.assertTrue(false); } catch(Exception e) { Assert.assertTrue(true); }
		try { ResponseUtils.sendResponse(null, "hello".getBytes(), HttpMimeType.BINARY); Assert.assertTrue(false); } catch(Exception e) { Assert.assertTrue(true); }
		
		// valid case
		byte[] bytes = "hello world".getBytes();
		AmHttpServletResponse hsr = new AmHttpServletResponse();
		try {
			ResponseUtils.sendResponse(hsr, bytes, HttpMimeType.BINARY);
			
			
		} catch(Exception e) {
			Assert.assertTrue(false);
		}
	}
	
}

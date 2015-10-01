package com.sangupta.jerry.util;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

/**
 * Unit tests for {@link XStreamUtils} class
 * 
 * @author sangupta
 *
 */
public class TestXStreamUtils {
	
	@Test
	public void test() {
		Assert.assertNotNull(XStreamUtils.getXStream());
		
		XStream xs = XStreamUtils.getXStream();
		Assert.assertTrue(xs == XStreamUtils.getXStream());
		
		Assert.assertTrue(xs == XStreamUtils.getXStream(XStreamUtils.class));
		Assert.assertTrue(xs == XStreamUtils.getXStream(XStreamUtils.class));
	}

}

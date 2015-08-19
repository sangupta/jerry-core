package com.sangupta.jerry.util;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.jerry.constants.HttpMimeType;

/**
 * Unit tests for {@link MimeUtils}.
 * 
 * @author sangupta
 *
 */
public class TestMimeUtils {

	@Test
	public void testGetMimeTypeForExtension() {
		Assert.assertEquals(HttpMimeType.BINARY, MimeUtils.getMimeTypeForFileExtension(null));
		Assert.assertEquals(HttpMimeType.BINARY, MimeUtils.getMimeTypeForFileExtension(""));
		
		Assert.assertEquals(HttpMimeType.TEXT_PLAIN, MimeUtils.getMimeTypeForFileExtension("txt"));
	}
}

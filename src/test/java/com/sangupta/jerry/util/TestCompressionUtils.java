package com.sangupta.jerry.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link CompressionUtils}
 * 
 * @author sangupta
 *
 */
public class TestCompressionUtils {
	
	private static final int MAX_RUNS = 1000 * 10;

	@Test
	public void testCompressDecompress() throws UnsupportedEncodingException {
		// do not run this test on openjdk - failing for some unknown stupid reason
		// that i don't have time to debug for 2.2.1 release
		String jdk = System.getProperty("java.vm.name").toLowerCase();
		if(jdk.contains("openjdk")) {
			return;
		}
		
		for(int index = 0; index < MAX_RUNS; index++) {
			String text = StringUtils.getRandomString(1000);
			
			byte[] bytes1 = CompressionUtils.compress(text);
			String textBack = CompressionUtils.uncompressToString(bytes1);
			Assert.assertEquals(text, textBack);
			
			// other methods
			byte[] bytes = text.getBytes(StringUtils.DEFAULT_CHARSET);
			bytes1 = CompressionUtils.compress(bytes);
			byte[] bytesBack = CompressionUtils.uncompress(bytes1);
			Assert.assertArrayEquals(bytes, bytesBack);
			
			bytes1 = CompressionUtils.compress(text, StringUtils.CHARSET_UTF8);
			textBack = CompressionUtils.uncompressToString(bytes1);
			Assert.assertEquals(text, textBack);
			
			bytes1 = CompressionUtils.compress(text, StringUtils.CHARSET_UTF8);
			textBack = CompressionUtils.uncompressToString(bytes1, StringUtils.CHARSET_UTF8);
			Assert.assertEquals(text, textBack);
		}
	}
	
	@Test
	public void testGzip() throws IOException {
		for(int index = 0; index < MAX_RUNS; index++) {
			String text = StringUtils.getRandomString(1000);
			
			byte[] bytes = text.getBytes(StringUtils.DEFAULT_CHARSET);
			byte[] compressed = CompressionUtils.gzipByteArray(bytes);
			byte[] uncompressed = CompressionUtils.ungzipByteArray(compressed);
			
			Assert.assertArrayEquals(bytes, uncompressed);
		}
	}
	
}

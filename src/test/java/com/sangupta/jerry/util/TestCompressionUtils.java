package com.sangupta.jerry.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

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
		for(int index = 0; index < MAX_RUNS; index++) {
			String text = getRandomString();
			
			byte[] bytes1 = CompressionUtils.compress(text);
			String textBack = CompressionUtils.uncompressToString(bytes1);
			Assert.assertEquals(text, textBack);
			
			// other methods
			byte[] bytes = text.getBytes();
			bytes1 = CompressionUtils.compress(bytes);
			byte[] bytesBack = CompressionUtils.uncompress(bytes1);
			Assert.assertArrayEquals(bytes, bytesBack);
			
			bytes1 = CompressionUtils.compress(text, StringUtils.CHARSET_UTF8);
			textBack = CompressionUtils.uncompressToString(bytes1);
			Assert.assertEquals(text, textBack);
			
			bytes1 = CompressionUtils.compress(text, "UTF-8");
			textBack = CompressionUtils.uncompressToString(bytes1);
			Assert.assertEquals(text, textBack);
		}
	}
	
	@Test
	public void testGzip() throws IOException {
		for(int index = 0; index < MAX_RUNS; index++) {
			String text = getRandomString();
			
			byte[] bytes = text.getBytes();
			byte[] compressed = CompressionUtils.gzipByteArray(bytes);
			byte[] uncompressed = CompressionUtils.ungzipByteArray(compressed);
			
			Assert.assertArrayEquals(bytes, uncompressed);
		}
	}
	
	private String getRandomString() {
		StringBuilder builder = new StringBuilder(1000);
		for(int index = 0; index < 25; index++) {
			builder.append(UUID.randomUUID().toString());
		}
		
		return builder.toString();
	}
}

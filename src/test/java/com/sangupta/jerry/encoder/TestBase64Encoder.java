package com.sangupta.jerry.encoder;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Base64Encoder}
 * 
 * @author sangupta
 *
 */
public class TestBase64Encoder {
	
	private static final int MAX_RUNS = 1000 * 10;

	@Test
	public void testEncodeDecode() {
		for(int index = 0; index < MAX_RUNS; index++) {
			String random = getRandomString();
			byte[] bytes = random.getBytes();
			byte[] encoded = Base64Encoder.encodeToByte(bytes, false);

			byte[] decoded = Base64Encoder.decode(encoded);
			Assert.assertArrayEquals(bytes, decoded);
			
			String reconstructed = new String(decoded);
			Assert.assertEquals(random, reconstructed);
			
			
			decoded = Base64Encoder.decodeFast(encoded);
			Assert.assertArrayEquals(bytes, decoded);
		}
	}
	
	@Test
	public void testEncodeDecodeChar() {
		for(int index = 0; index < MAX_RUNS; index++) {
			String random = getRandomString();
			byte[] bytes = random.getBytes();
			char[] encoded = Base64Encoder.encodeToChar(bytes, false);

			byte[] decoded = Base64Encoder.decode(encoded);
			Assert.assertArrayEquals(bytes, decoded);
			
			String reconstructed = new String(decoded);
			Assert.assertEquals(random, reconstructed);
			
			
			decoded = Base64Encoder.decodeFast(encoded);
			Assert.assertArrayEquals(bytes, decoded);
		}
	}
	
	@Test
	public void testEncodeDecodeString() {
		for(int index = 0; index < MAX_RUNS; index++) {
			String random = getRandomString();
			byte[] bytes = random.getBytes();
			String encoded = Base64Encoder.encodeToString(bytes, false);

			byte[] decoded = Base64Encoder.decode(encoded);
			Assert.assertArrayEquals(bytes, decoded);
			
			String reconstructed = new String(decoded);
			Assert.assertEquals(random, reconstructed);
			
			
			decoded = Base64Encoder.decodeFast(encoded);
			Assert.assertArrayEquals(bytes, decoded);
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

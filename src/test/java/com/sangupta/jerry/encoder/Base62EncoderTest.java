package com.sangupta.jerry.encoder;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test {@link Base62Encoder} class.
 * 
 * @author sangupta
 *
 */
public class Base62EncoderTest {

	/**
	 * Test encoding and decoding of first 100 million 
	 * numbers.
	 * 
	 */
	@Test
	public void testEncodingDecoding() {
		final long MAX = 100 * 1000 * 1000; // 100 million
		for(long index = 0; index < MAX; index++) {
			long num = 0 - index;
			
			String enc = Base62Encoder.encode(num);
			long dec = Base62Encoder.decode(enc);
			
			if(num != dec) {
				Assert.fail();
				break;
			}
		}
	}

}

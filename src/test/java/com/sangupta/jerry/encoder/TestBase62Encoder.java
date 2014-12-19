/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2014, Sandeep Gupta
 * 
 * http://sangupta.com/projects/jerry
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.jerry.encoder;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test {@link Base62Encoder} class.
 * 
 * @author sangupta
 *
 */
public class TestBase62Encoder {

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
		
		try { Base62Encoder.decode(null); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		try { Base62Encoder.decode(""); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		try { Base62Encoder.decode("abc++abc"); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		
		String expected = "";
		long[] values = new long[5];
		for(int index = 0; index < 5; index++) {
			values[index] = (long) Math.random() * Long.MAX_VALUE;
			if(index % 2 == 0) {
				values[index] = 0 - values[index];
			}
			expected += Base62Encoder.encode(values[index]);
		}
		
		Assert.assertEquals(expected, Base62Encoder.encode(values[0], values[1], values[2], values[3], values[4]));
	}

}

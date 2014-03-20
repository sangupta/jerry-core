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

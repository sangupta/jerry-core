/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2016, Sandeep Gupta
 *
 * http://sangupta.com/projects/jerry-core
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

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.jerry.constants.OperatingSystem;
import com.sangupta.jerry.util.JDKUtils;
import com.sangupta.jerry.util.OSUtils;
import com.sangupta.jerry.util.StringUtils;

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
		if(JDKUtils.isJDK7()) {
			return;
		}

		if(OSUtils.OS == OperatingSystem.Windows) {
			return;
		}

		for(int index = 0; index < MAX_RUNS; index++) {
			String random = StringUtils.getRandomString(1000);
			byte[] bytes = random.getBytes(StringUtils.DEFAULT_CHARSET);
			byte[] encoded = Base64Encoder.encodeToByte(bytes, false);

			byte[] decoded = Base64Encoder.decode(encoded);
			Assert.assertArrayEquals(bytes, decoded);

			String reconstructed = new String(decoded, StringUtils.DEFAULT_CHARSET);
			Assert.assertEquals(random, reconstructed);


			decoded = Base64Encoder.decodeFast(encoded);
			Assert.assertArrayEquals(bytes, decoded);
		}
	}

	@Test
	public void testEncodeDecodeChar() {
		if(JDKUtils.isJDK7()) {
			return;
		}

		if(OSUtils.OS == OperatingSystem.Windows) {
			return;
		}

		for(int index = 0; index < MAX_RUNS; index++) {
			String random = StringUtils.getRandomString(1000);
			byte[] bytes = random.getBytes(StringUtils.DEFAULT_CHARSET);
			char[] encoded = Base64Encoder.encodeToChar(bytes, false);

			byte[] decoded = Base64Encoder.decode(encoded);
			Assert.assertArrayEquals(bytes, decoded);

			String reconstructed = new String(decoded, StringUtils.DEFAULT_CHARSET);
			Assert.assertEquals(random, reconstructed);


			decoded = Base64Encoder.decodeFast(encoded);
			Assert.assertArrayEquals(bytes, decoded);
		}
	}

	@Test
	public void testEncodeDecodeString() {
		if(JDKUtils.isJDK7()) {
			return;
		}

		if(OSUtils.OS == OperatingSystem.Windows) {
			return;
		}

		for(int index = 0; index < MAX_RUNS; index++) {
			String random = StringUtils.getRandomString(1000);
			byte[] bytes = random.getBytes(StringUtils.DEFAULT_CHARSET);
			String encoded = Base64Encoder.encodeToString(bytes, false);

			byte[] decoded = Base64Encoder.decode(encoded);
			Assert.assertArrayEquals(bytes, decoded);

			String reconstructed = new String(decoded, StringUtils.DEFAULT_CHARSET);
			Assert.assertEquals(random, reconstructed);


			decoded = Base64Encoder.decodeFast(encoded);
			Assert.assertArrayEquals(bytes, decoded);
		}
	}

}

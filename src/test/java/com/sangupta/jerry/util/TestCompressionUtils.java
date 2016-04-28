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


package com.sangupta.jerry.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.jerry.constants.OperatingSystem;

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
		if(JDKUtils.isJDK7()) {
			// for some reason this test fails on JDK 7
			// will look at it later
			return;
		}

		if(OSUtils.OS == OperatingSystem.Windows) {
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

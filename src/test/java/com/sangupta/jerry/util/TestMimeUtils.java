/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
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
		Assert.assertEquals(HttpMimeType.BINARY, MimeUtils.getMimeTypeForFileExtension("sangupta"));

		Assert.assertEquals(HttpMimeType.TEXT_PLAIN, MimeUtils.getMimeTypeForFileExtension("txt"));
	}
}

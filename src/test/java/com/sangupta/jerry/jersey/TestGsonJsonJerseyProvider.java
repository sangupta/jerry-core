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


package com.sangupta.jerry.jersey;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link GsonJsonJerseyProvider}
 *
 * @author sangupta
 *
 */
public class TestGsonJsonJerseyProvider {

	@Test
	public void test() throws WebApplicationException, IOException {
		GsonJsonJerseyProvider handler = new GsonJsonJerseyProvider();

		// the basic ones
		Assert.assertTrue(handler.isReadable(null, null, null, null));
		Assert.assertTrue(handler.isWriteable(null, null, null, null));

		// the main conversion test
		String[] strings = new String[] { "Hello", "World" };

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		handler.writeTo(strings, String[].class, String[].class, null, MediaType.APPLICATION_JSON_TYPE, null, stream);
		String json = new String(stream.toByteArray());

		Assert.assertNotNull(json);

		ByteArrayInputStream in = new ByteArrayInputStream(json.getBytes());
		Object reconstructed = handler.readFrom(Object.class, String[].class, null, MediaType.APPLICATION_JSON_TYPE, null, in);

		Assert.assertNotNull(reconstructed);
		boolean validInstance = (reconstructed instanceof String[]) || (reconstructed instanceof List);
		Assert.assertTrue(validInstance);

		if(reconstructed instanceof String[]) {
			String[] array = (String[]) reconstructed;
			Assert.assertArrayEquals(strings, array);
			return;
		}

		if(reconstructed instanceof List) {
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) reconstructed;
			Assert.assertArrayEquals(strings, list.toArray());
			return;
		}
	}
}

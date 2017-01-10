/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2017, Sandeep Gupta
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


package com.sangupta.jerry.ds;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link SimpleMultiMap} class
 *
 * @author sangupta
 *
 */
public class TestSimpleMultiMap {

	@Test
	public void test() {
		SimpleMultiMap<String, String> map = new SimpleMultiMap<String, String>();

		Assert.assertEquals(0, map.size());
		Assert.assertTrue(map.isEmpty());

		Assert.assertFalse(map.containsKey("sangupta"));

		map.put("sangupta", "v1");
		Assert.assertTrue(map.containsKey("sangupta"));
		Assert.assertFalse(map.isEmpty());
		Assert.assertEquals(1, map.size());

		List<String> values = map.getValues("sangupta");
		Assert.assertNotNull(values);
		Assert.assertEquals(1, values.size());

		Set<String> keys = map.keySet();
		Assert.assertNotNull(keys);
		Assert.assertEquals(1, keys.size());

		map.put("sangupta", "v2");
		Assert.assertTrue(map.containsKey("sangupta"));
		Assert.assertFalse(map.isEmpty());
		Assert.assertEquals(1, map.size());

		Assert.assertNotNull(keys);
		Assert.assertEquals(1, keys.size());

		values = map.getValues("sangupta");
		Assert.assertNotNull(values);
		Assert.assertEquals(2, values.size());

		map.remove("sangupta");
		Assert.assertEquals(0, map.size());
		Assert.assertTrue(map.isEmpty());

		map.put("sangupta", "v1");
		Assert.assertTrue(map.containsKey("sangupta"));
		Assert.assertFalse(map.isEmpty());
		Assert.assertEquals(1, map.size());

		map.clear();
		Assert.assertEquals(0, map.size());
		Assert.assertTrue(map.isEmpty());
	}

}

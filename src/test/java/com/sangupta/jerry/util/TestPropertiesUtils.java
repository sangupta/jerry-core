/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2015, Sandeep Gupta
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
 
package com.sangupta.jerry.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link PropertiesUtils} class
 * 
 * @author sangupta
 *
 */
public class TestPropertiesUtils {

	@Test
	public void testAsMap() {
		Properties prop = null;
		Assert.assertNull(PropertiesUtils.asMap(prop));
		
		prop = new Properties();
		Map<String, String> map = PropertiesUtils.asMap(prop);
		Assert.assertNotNull(map);
		Assert.assertTrue(map.isEmpty());
		
		prop.put("sangupta1", "v1");
		map = PropertiesUtils.asMap(prop);
		Assert.assertFalse(map.isEmpty());
		Assert.assertEquals(1, map.size());
		
		prop.put("sangupta2", "v2");
		map = PropertiesUtils.asMap(prop);
		Assert.assertFalse(map.isEmpty());
		Assert.assertEquals(2, map.size());
	}
	
	@Test
	public void testFromMap() {
		Map<String, String> map = null;
		Assert.assertNull(PropertiesUtils.fromMap(map));
		
		map = new HashMap<String, String>();
		Properties prop = PropertiesUtils.fromMap(map);
		Assert.assertNotNull(prop);
		Assert.assertTrue(prop.isEmpty());
		
		map.put("sangupta1", "v1");
		prop = PropertiesUtils.fromMap(map);
		Assert.assertFalse(prop.isEmpty());
		Assert.assertEquals(1, prop.size());
		
		map.put("sangupta2", "v2");
		prop = PropertiesUtils.fromMap(map);
		Assert.assertFalse(prop.isEmpty());
		Assert.assertEquals(2, prop.size());
	}
}

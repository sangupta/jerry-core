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

	@Test
	public void testCommonMethods() {
		Assert.assertFalse(PropertiesUtils.getBooleanValue(null, null));
		Assert.assertTrue(PropertiesUtils.getBooleanValue(null, null, true));

		Properties properties = new Properties();
		properties.setProperty("hello", "true");
		Assert.assertTrue(PropertiesUtils.getBooleanValue(properties, "hello", false));

		Assert.assertEquals(65, PropertiesUtils.getShortValue(null, null, (short) 65));
		Assert.assertEquals(65, PropertiesUtils.getIntValue(null, null, 65));
		Assert.assertEquals(65, PropertiesUtils.getLongValue(null, null, 65l));
		Assert.assertEquals(65, PropertiesUtils.getFloatValue(null, null, 65f), 0f);
		Assert.assertEquals(65, PropertiesUtils.getDoubleValue(null, null, 65d), 0d);

		properties.setProperty("short", "65");
		properties.setProperty("int", "65");
		properties.setProperty("long", "65");
		properties.setProperty("float", "65");
		properties.setProperty("double", "65");

		Assert.assertEquals(65, PropertiesUtils.getShortValue(properties, "short", (short) 65));
		Assert.assertEquals(65, PropertiesUtils.getIntValue(properties, "int", 65));
		Assert.assertEquals(65, PropertiesUtils.getLongValue(properties, "long", 65l));
		Assert.assertEquals(65, PropertiesUtils.getFloatValue(properties, "float", 65f), 0f);
		Assert.assertEquals(65, PropertiesUtils.getDoubleValue(properties, "double", 65d), 0d);
	}

	@Test
	public void testGetPropertyAsString() {
		Assert.assertNull(PropertiesUtils.getPropertyAsString(null));

		Assert.assertEquals("10", PropertiesUtils.getPropertyAsString((int) 10));
		Assert.assertEquals("A", PropertiesUtils.getPropertyAsString((char) 65));
		Assert.assertEquals("10", PropertiesUtils.getPropertyAsString((short) 10));
		Assert.assertEquals("10", PropertiesUtils.getPropertyAsString((byte) 10));
		Assert.assertEquals("10", PropertiesUtils.getPropertyAsString((long) 10));
		Assert.assertEquals("10.0", PropertiesUtils.getPropertyAsString((float) 10));
		Assert.assertEquals("10.0", PropertiesUtils.getPropertyAsString((double) 10));

		Assert.assertEquals("true", PropertiesUtils.getPropertyAsString(true));

		Assert.assertEquals("true", PropertiesUtils.getPropertyAsString("true"));

		Assert.assertEquals("65,65", PropertiesUtils.getPropertyAsString(new byte[] { 65, 65 }));
		Assert.assertEquals("A,A", PropertiesUtils.getPropertyAsString(new char[] { 65, 65 }));
		Assert.assertEquals("65,65", PropertiesUtils.getPropertyAsString(new short[] { 65, 65 }));
		Assert.assertEquals("65,65", PropertiesUtils.getPropertyAsString(new int[] { 65, 65 }));
		Assert.assertEquals("65,65", PropertiesUtils.getPropertyAsString(new long[] { 65, 65 }));
		Assert.assertEquals("65.0,65.0", PropertiesUtils.getPropertyAsString(new float[] { 65, 65 }));
		Assert.assertEquals("65.0,65.0", PropertiesUtils.getPropertyAsString(new double[] { 65, 65 }));
		Assert.assertEquals("true,false", PropertiesUtils.getPropertyAsString(new boolean[] { true, false }));

		Assert.assertEquals("65,65", PropertiesUtils.getPropertyAsString(new Byte[] { 65, 65 }));
		Assert.assertEquals("A,A", PropertiesUtils.getPropertyAsString(new Character[] { 65, 65 }));
		Assert.assertEquals("65,65", PropertiesUtils.getPropertyAsString(new Short[] { 65, 65 }));
		Assert.assertEquals("65,65", PropertiesUtils.getPropertyAsString(new Integer[] { 65, 65 }));
		Assert.assertEquals("65,65", PropertiesUtils.getPropertyAsString(new Long[] { 65l, 65l }));
		Assert.assertEquals("65.0,65.0", PropertiesUtils.getPropertyAsString(new Float[] { 65f, 65f }));
		Assert.assertEquals("65.0,65.0", PropertiesUtils.getPropertyAsString(new Double[] { 65d, 65d }));
		Assert.assertEquals("true,false", PropertiesUtils.getPropertyAsString(new Boolean[] { true, false }));

		Assert.assertEquals("true,false", PropertiesUtils.getPropertyAsString(new String[] { "true", "false"}));
	}
}

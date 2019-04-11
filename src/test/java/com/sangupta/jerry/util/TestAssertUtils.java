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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit-tests for {@link AssertUtils} utility functions.
 *
 * @author sangupta
 * @added 13 July 2012
 */
public class TestAssertUtils {

	@Test
	public void testIsEmptyString() {
		// strings
		assertTrue(AssertUtils.isEmpty((String) null));
		assertFalse(AssertUtils.isNotEmpty((String) null));

		assertTrue(AssertUtils.isEmpty(""));
		assertFalse(AssertUtils.isNotEmpty(""));

		assertTrue(AssertUtils.isNotEmpty(" "));
		assertFalse(AssertUtils.isEmpty(" "));

		assertTrue(AssertUtils.isNotEmpty(" abc"));
		assertFalse(AssertUtils.isEmpty(" abc"));
	}

	@Test
	public void testIsEmptyArray() {
		assertTrue(AssertUtils.isEmpty((String[]) null));
		assertFalse(AssertUtils.isNotEmpty((String[]) null));

		assertTrue(AssertUtils.isEmpty((Object[]) null));
		assertTrue(AssertUtils.isEmpty((Object[]) new Object[] { }));
		assertFalse(AssertUtils.isEmpty((Object[]) new Object[] { new Object() }));

		assertFalse(AssertUtils.isNotEmpty((Object[]) null));
		assertFalse(AssertUtils.isNotEmpty((Object[]) new Object[] { }));
		assertTrue(AssertUtils.isNotEmpty((Object[]) new Object[] { new Object() }));

		assertTrue(AssertUtils.isNotEmpty(new String[] { "", "" }));
		assertFalse(AssertUtils.isEmpty(new String[] { "", "" }));

		assertTrue(AssertUtils.isEmpty(new byte[] { }));
		assertTrue(AssertUtils.isEmpty(new char[] { }));
		assertTrue(AssertUtils.isEmpty(new short[] { }));
		assertTrue(AssertUtils.isEmpty(new int[] { }));
		assertTrue(AssertUtils.isEmpty(new long[] { }));
		assertTrue(AssertUtils.isEmpty(new float[] { }));
		assertTrue(AssertUtils.isEmpty(new double[] { }));

		assertTrue(AssertUtils.isEmpty((byte[]) null));
		assertTrue(AssertUtils.isEmpty((char[]) null));
		assertTrue(AssertUtils.isEmpty((short[]) null));
		assertTrue(AssertUtils.isEmpty((int[]) null));
		assertTrue(AssertUtils.isEmpty((long[]) null));
		assertTrue(AssertUtils.isEmpty((float[]) null));
		assertTrue(AssertUtils.isEmpty((double[]) null));

		assertFalse(AssertUtils.isEmpty(new byte[] { 0 }));
		assertFalse(AssertUtils.isEmpty(new char[] { 0 }));
		assertFalse(AssertUtils.isEmpty(new short[] { 0 }));
		assertFalse(AssertUtils.isEmpty(new int[] { 0 }));
		assertFalse(AssertUtils.isEmpty(new long[] { 0 }));
		assertFalse(AssertUtils.isEmpty(new float[] { 0 }));
		assertFalse(AssertUtils.isEmpty(new double[] { 0 }));

		assertFalse(AssertUtils.isNotEmpty((byte[]) null));
		assertFalse(AssertUtils.isNotEmpty((char[]) null));
		assertFalse(AssertUtils.isNotEmpty((short[]) null));
		assertFalse(AssertUtils.isNotEmpty((int[]) null));
		assertFalse(AssertUtils.isNotEmpty((long[]) null));
		assertFalse(AssertUtils.isNotEmpty((float[]) null));
		assertFalse(AssertUtils.isNotEmpty((double[]) null));

		assertTrue(AssertUtils.isNotEmpty(new byte[] { 0 }));
		assertTrue(AssertUtils.isNotEmpty(new char[] { 0 }));
		assertTrue(AssertUtils.isNotEmpty(new short[] { 0 }));
		assertTrue(AssertUtils.isNotEmpty(new int[] { 0 }));
		assertTrue(AssertUtils.isNotEmpty(new long[] { 0 }));
		assertTrue(AssertUtils.isNotEmpty(new float[] { 0 }));
		assertTrue(AssertUtils.isNotEmpty(new double[] { 0 }));
	}

	@Test
	public void testIsEmptyMap() {
		Map<String, String> map = null;
		assertTrue(AssertUtils.isEmpty(map));
		assertFalse(AssertUtils.isNotEmpty(map));

		map = new HashMap<String, String>();
		assertTrue(AssertUtils.isEmpty(map));
		assertFalse(AssertUtils.isNotEmpty(map));

		map.put("1", "2");
		assertTrue(AssertUtils.isNotEmpty(map));
		assertFalse(AssertUtils.isEmpty(map));
	}

	@Test
	public void testIsEmptyCollection() {
		Collection<String> coll = null;
		assertTrue(AssertUtils.isEmpty(coll));
		assertFalse(AssertUtils.isNotEmpty(coll));

		coll = new ArrayList<String>();
		assertTrue(AssertUtils.isEmpty(coll));
		assertFalse(AssertUtils.isNotEmpty(coll));

		coll.add("test string");
		assertTrue(AssertUtils.isNotEmpty(coll));
		assertFalse(AssertUtils.isEmpty(coll));
	}

	@Test
	public void testIsEmptyObject() {
		assertTrue(AssertUtils.isEmpty((Object) null));
		assertFalse(AssertUtils.isEmpty(new Object()));

		assertFalse(AssertUtils.isNotEmpty((Object) null));
		assertTrue(AssertUtils.isNotEmpty(new Object()));

		assertTrue(AssertUtils.isEmpty((Object) ((String) "")));
		assertTrue(AssertUtils.isEmpty((Object) (new ArrayList<String>())));
		assertTrue(AssertUtils.isEmpty((Object) (new HashMap<String, String>())));

		assertTrue(AssertUtils.isEmpty((Object) (new int[] { })));
		assertTrue(AssertUtils.isEmpty((Object) (new byte[] { })));
		assertTrue(AssertUtils.isEmpty((Object) (new char[] { })));
		assertTrue(AssertUtils.isEmpty((Object) (new long[] { })));
		assertTrue(AssertUtils.isEmpty((Object) (new short[] { })));
		assertTrue(AssertUtils.isEmpty((Object) (new long[] { })));
		assertTrue(AssertUtils.isEmpty((Object) (new float[] { })));
		assertTrue(AssertUtils.isEmpty((Object) (new double[] { })));
	}

	@Test
	public void testIsBlankString() {
		assertTrue(AssertUtils.isBlank((String) null));
		assertFalse(AssertUtils.isNotBlank((String) null));

		assertTrue(AssertUtils.isBlank(""));
		assertFalse(AssertUtils.isNotBlank(""));

		assertTrue(AssertUtils.isNotEmpty(" "));
		assertFalse(AssertUtils.isNotBlank(" "));

		assertTrue(AssertUtils.isNotBlank(" abc"));
		assertFalse(AssertUtils.isBlank(" abc"));
	}

	@Test
	public void testAreEmpty() {
		assertTrue(AssertUtils.areEmpty((String[]) null));
		assertTrue(AssertUtils.areEmpty(new String[] { } ));
		assertTrue(AssertUtils.areEmpty(new String[] { null, null, "" } ));
		assertFalse(AssertUtils.areEmpty(new String[] { null, "", "123" } ));
	}

	@Test
	public void testAreNotEmpty() {
		assertFalse(AssertUtils.areNotEmpty((String[]) null));
		assertFalse(AssertUtils.areNotEmpty(new String[] { } ));
		assertFalse(AssertUtils.areNotEmpty(new String[] { null, null, "" } ));
		assertFalse(AssertUtils.areNotEmpty(new String[] { null, "", "123" } ));
		assertTrue(AssertUtils.areNotEmpty(new String[] { "123", "123", "123" } ));
	}

	@Test
	public void testIsNotEmptyProperties() {
		assertFalse(AssertUtils.isNotEmpty((Properties) null));

		Properties properties = new Properties();
		assertFalse(AssertUtils.isNotEmpty((Properties) properties));
		properties.setProperty("123", "123");
		assertTrue(AssertUtils.isNotEmpty((Properties) properties));
		properties.clear();
		assertFalse(AssertUtils.isNotEmpty((Properties) properties));
	}
}

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

package com.sangupta.jerry.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit-tests for {@link AssertUtils} utility functions.
 * 
 * @author sangupta
 * @added 13 July 2012
 */
public class AssertUtilsTest {
	
	@Test
	public void testIsEmpty() {
		// strings
		assertTrue(AssertUtils.isEmpty((String) null));
		assertFalse(AssertUtils.isNotEmpty((String) null));
		
		assertTrue(AssertUtils.isEmpty(""));
		assertFalse(AssertUtils.isNotEmpty(""));
		
		assertTrue(AssertUtils.isNotEmpty(" "));
		assertFalse(AssertUtils.isEmpty(" "));
		
		assertTrue(AssertUtils.isNotEmpty(" abc"));
		assertFalse(AssertUtils.isEmpty(" abc"));
		
		// array
		assertTrue(AssertUtils.isEmpty((String[]) null));
		assertFalse(AssertUtils.isNotEmpty((String[]) null));
		
		assertTrue(AssertUtils.isEmpty((Object[]) null));
		assertFalse(AssertUtils.isNotEmpty((Object[]) null));
		
		assertTrue(AssertUtils.isNotEmpty(new String[] { "", "" }));
		assertFalse(AssertUtils.isEmpty(new String[] { "", "" }));
		
		// map
		Map<String, String> map = null;
		assertTrue(AssertUtils.isEmpty(map));
		assertFalse(AssertUtils.isNotEmpty(map));
		
		map = new HashMap<String, String>();
		assertTrue(AssertUtils.isEmpty(map));
		assertFalse(AssertUtils.isNotEmpty(map));
		
		map.put("1", "2");
		assertTrue(AssertUtils.isNotEmpty(map));
		assertFalse(AssertUtils.isEmpty(map));
		
		// collection
		Collection<String> coll = null;
		assertTrue(AssertUtils.isEmpty(coll));
		assertFalse(AssertUtils.isNotEmpty(coll));
		
		coll = new ArrayList<String>();
		assertTrue(AssertUtils.isEmpty(coll));
		assertFalse(AssertUtils.isNotEmpty(coll));
		
		coll.add("test string");
		assertTrue(AssertUtils.isNotEmpty(coll));
		assertFalse(AssertUtils.isEmpty(coll));
		
		// object
		assertTrue(AssertUtils.isEmpty((Object) null));
		assertTrue(AssertUtils.isNotEmpty(new Object()));
	}

}

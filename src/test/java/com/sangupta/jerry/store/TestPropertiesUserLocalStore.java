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
 
package com.sangupta.jerry.store;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit tests for {@link PropertiesUserLocalStore}.
 * 
 * @author sangupta
 *
 */
public class TestPropertiesUserLocalStore {
	
	@Test
	public void testStore() {
		UserLocalStore store;
		
		try { store = new PropertiesUserLocalStore(null, null); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		try { store = new PropertiesUserLocalStore("", null); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		
		try { store = new PropertiesUserLocalStore(".test", null); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		try { store = new PropertiesUserLocalStore(".test", ""); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		
		store = new PropertiesUserLocalStore(".test", UUID.randomUUID().toString() + ".properties");
		
		Assert.assertNull(store.get("prop"));
		Assert.assertEquals("value-default", store.get("prop", "value-default"));
		
		store.put("prop", "value1");
		Assert.assertEquals("value1", store.get("prop"));
		Assert.assertEquals("value1", store.get("prop", "value-default"));
		
		store.put("prop", "value2");
		Assert.assertEquals("value2", store.get("prop"));
		
		store.delete("prop");
		Assert.assertNull(store.get("prop"));
		Assert.assertEquals("value-default", store.get("prop", "value-default"));
	}

}

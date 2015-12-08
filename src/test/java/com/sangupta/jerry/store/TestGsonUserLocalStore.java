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

import org.junit.Assert;

import org.junit.Test;

/**
 * Unit tests for {@link PropertiesUserLocalStore}.
 * 
 * @author sangupta
 *
 */
public class TestGsonUserLocalStore {
	
	@Test
	public void testStore() {
		UserLocalStore store;
		
		try { store = new PropertiesUserLocalStore(null, null); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		try { store = new PropertiesUserLocalStore("", null); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		
		try { store = new PropertiesUserLocalStore(".test", null); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		try { store = new PropertiesUserLocalStore(".test", ""); Assert.assertTrue(false); } catch(IllegalArgumentException e) { Assert.assertTrue(true); }
		
		store = new GsonUserLocalStore(".test", UUID.randomUUID().toString() + ".json");
		
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
	
	@Test
	public void testStoreReadWrite() {
		UserLocalStore store = new GsonUserLocalStore(".test", UUID.randomUUID().toString() + ".json");
		
		MyValueObject mvo = new MyValueObject();
		
		mvo.bite = 36;
		mvo.chr = 's';
		mvo.shrt = 23;
		mvo.nt = 56;
		mvo.lng = 67;
		mvo.flt = 35.4f;
		mvo.dbl = 99.24d;
		mvo.bln = true;
		mvo.strng = "Hello World";
		
		store.saveFrom(mvo);
		
		MyValueObject read = new MyValueObject();
		store.readTo(read);
		
		// compare both the objects
		Assert.assertEquals(mvo.bite, read.bite);
		Assert.assertEquals(mvo.chr, read.chr);
		Assert.assertEquals(mvo.shrt, read.shrt);
		Assert.assertEquals(mvo.nt, read.nt);
		Assert.assertEquals(mvo.lng, read.lng);
		Assert.assertEquals(mvo.flt, read.flt, 0f);
		Assert.assertEquals(mvo.dbl, read.dbl, 0d);
		Assert.assertEquals(mvo.bln, read.bln);
		Assert.assertEquals(mvo.strng, read.strng);
	}
	
	@Test
	public void testStoreReadWriteAnnotated() {
		UserLocalStore store = new GsonUserLocalStore(".test", UUID.randomUUID().toString() + ".json");
		
		MyValueObjectAnnotated mvo = new MyValueObjectAnnotated();
		
		mvo.bite = 36;
		mvo.chr = 's';
		mvo.shrt = 23;
		mvo.nt = 56;
		mvo.lng = 67;
		mvo.flt = 35.4f;
		mvo.dbl = 99.24d;
		mvo.bln = true;
		mvo.strng = "Hello World";
		
		store.saveFrom(mvo);
		
		MyValueObjectAnnotated read = new MyValueObjectAnnotated();
		store.readTo(read);
		
		// compare both the objects
		Assert.assertEquals(mvo.bite, read.bite);
		Assert.assertEquals(mvo.chr, read.chr);
		Assert.assertEquals(mvo.shrt, read.shrt);
		Assert.assertEquals(mvo.nt, read.nt);
		Assert.assertEquals(mvo.lng, read.lng);
		Assert.assertEquals(mvo.flt, read.flt, 0f);
		Assert.assertEquals(mvo.dbl, read.dbl, 0d);
		Assert.assertEquals(mvo.bln, read.bln);
		Assert.assertEquals(mvo.strng, read.strng);
	}

	private class MyValueObject {
		
		private byte bite;
		
		private char chr;
		
		private short shrt;
		
		private int nt;
		
		private long lng;
		
		private float flt;
		
		private double dbl;
		
		private boolean bln;
		
		private String strng;
		
	}
	
	private class MyValueObjectAnnotated {
		
		@PropertyName("value1")
		private byte bite;
		
		@PropertyName("value2")
		private char chr;
		
		@PropertyName("value3")
		private short shrt;
		
		@PropertyName("value4")
		private int nt;
		
		@PropertyName("value5")
		private long lng;
		
		@PropertyName("value6")
		private float flt;
		
		@PropertyName("value7")
		private double dbl;
		
		@PropertyName("value8")
		private boolean bln;
		
		@PropertyName("value9")
		private String strng;
		
	}
}

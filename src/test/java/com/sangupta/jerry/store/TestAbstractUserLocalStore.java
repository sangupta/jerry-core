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


package com.sangupta.jerry.store;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link AbstractUserLocalStore}
 *
 * @author sangupta
 *
 */
public class TestAbstractUserLocalStore {

	@Test
	public void testCommonMethods() {
		UserLocalStore store = new InMemoryUserLocalStore();

		// when store is empty
		Assert.assertEquals(true, store.getBoolean("bool", true));
		Assert.assertEquals(63, store.getShort("short", (short) 63));
		Assert.assertEquals(63, store.getChar("char", (char) 63));
		Assert.assertEquals(63, store.getByte("byte", (byte) 63));
		Assert.assertEquals(63, store.getInt("int", 63));
		Assert.assertEquals(63l, store.getLong("long", 63l));
		Assert.assertEquals(63f, store.getFloat("float", 63f), 0f);
		Assert.assertEquals(63d, store.getDouble("double", 63d), 0d);

		// when store has values
		store.put("bool", false);
		store.put("short", (short) 23);
		store.put("byte", (byte) 23);
		store.put("char", (char) 23);
		store.put("int", 23);
		store.put("long", 23l);
		store.put("float", 23f);
		store.put("double", 23d);
		Assert.assertEquals(false, store.getBoolean("bool", false));
		Assert.assertEquals(23, store.getShort("short", (short) 63));
		Assert.assertEquals(23, store.getChar("char", (char) 63));
		Assert.assertEquals(23, store.getByte("byte", (byte) 63));
		Assert.assertEquals(23, store.getInt("int", 63));
		Assert.assertEquals(23l, store.getLong("long", 63l));
		Assert.assertEquals(23f, store.getFloat("float", 63f), 0f);
		Assert.assertEquals(23d, store.getDouble("double", 63d), 0d);
	}
}

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
 
package com.sangupta.jerry.ds.mutable;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link MutableDouble} class
 * 
 * @author sangupta
 *
 */
public class TestMutableDouble {
	
	@Test
	public void testMutableInt() {
		MutableDouble md = new MutableDouble();
		Assert.assertEquals(0, md.get(), 0f);
		md.set(50);
		Assert.assertEquals(50, md.get(), 0f);
		
		md.setIfMax(100);
		Assert.assertEquals(100, md.get(), 0f);
		md.setIfMax(75);
		Assert.assertEquals(100, md.get(), 0f);
		
		md.setIfMin(50);
		Assert.assertEquals(50, md.get(), 0f);
		md.setIfMin(75);
		Assert.assertEquals(50, md.get(), 0f);
	}
}
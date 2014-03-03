/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012, Sandeep Gupta
 * 
 * http://www.sangupta/projects/jerry
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

import org.junit.Test;

import junit.framework.Assert;

/**
 * Unit tests for {@link StringUtils} class.
 * 
 * @author sangupta
 *
 */
public class StringUtilsTest {

	@Test
	public void testSubstringFrom() {
		String test ="this is a test string to test the functionality of substring";
		String from = "test";
		
		String subStr = StringUtils.substringFrom(test, from, 0);
		Assert.assertEquals(" string to test the functionality of substring", subStr);
		
		subStr = StringUtils.substringFrom(test, from, 11);
		Assert.assertEquals(" the functionality of substring", subStr);
		
		Assert.assertEquals(test, StringUtils.substringFrom(test, null, 0));
		Assert.assertEquals("", StringUtils.substringFrom(null, null, 0));
	}
}

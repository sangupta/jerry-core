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

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link CheckUtils} class
 * 
 * @author sangupta
 *
 */
public class TestCheckUtils {
	
	@Test
	public void testCheckFileExists() throws IOException {
		try {
			CheckUtils.checkFileExists((String) null);
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		File file = File.createTempFile("test-check", ".file");
		Assert.assertTrue(CheckUtils.checkFileExists(file.getAbsolutePath()));
	}

}

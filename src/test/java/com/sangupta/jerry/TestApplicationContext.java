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


package com.sangupta.jerry;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.jerry.util.DateUtils;

/**
 * Unit tests for {@link ApplicationContext} class
 *
 * @author sangupta
 *
 */
public class TestApplicationContext {

	@Test
	public void test() {
		Assert.assertTrue(ApplicationContext.STARTUP_TIME > 0);

		long diff = ApplicationContext.STARTUP_TIME - System.currentTimeMillis();
		Assert.assertTrue(diff < DateUtils.ONE_HOUR);

		Assert.assertNotNull(ApplicationContext.NODE_ID);
	}

}

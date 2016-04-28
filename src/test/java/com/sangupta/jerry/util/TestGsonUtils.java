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

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;

/**
 * Unit tests for {@link GsonUtils} class
 * 
 * @author sangupta
 *
 */
public class TestGsonUtils {

	@Test
	public void test() {
		Assert.assertNotNull(GsonUtils.getGson());
		
		Gson gson = GsonUtils.getGson();
		Assert.assertTrue(gson == GsonUtils.getGson());
		Assert.assertTrue(gson == GsonUtils.getGson(FieldNamingPolicy.IDENTITY));
		
		Assert.assertTrue(gson != GsonUtils.getGson(FieldNamingPolicy.LOWER_CASE_WITH_DASHES));
		Assert.assertTrue(gson != GsonUtils.getGson(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES));
		Assert.assertTrue(gson != GsonUtils.getGson(FieldNamingPolicy.UPPER_CAMEL_CASE));
		Assert.assertTrue(gson != GsonUtils.getGson(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES));
	}
	
}
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

import javax.servlet.ServletRequest;

import org.junit.Assert;
import org.junit.Test;

import com.sangupta.am.servlet.AmHttpServletRequest;

/**
 * Unit tests for {@link RequestUtils}.
 * 
 * @author sangupta
 *
 */
public class TestRequestUtils {
	
	@Test(expected = InstantiationException.class)
	public void testConstructor() throws InstantiationException {
		new RequestUtils() { };
	}

	@Test
	public void testExtractUriServletRequest() {
		Assert.assertNull(RequestUtils.extractUri((ServletRequest) null));
		
		AmHttpServletRequest request = new AmHttpServletRequest();
		request.setRequestURI("/context/path/page.extension");
		request.setContextPath("/context");
		Assert.assertNotNull(RequestUtils.extractUri((ServletRequest) request));
		
		Assert.assertEquals("path/page.extension", RequestUtils.extractUri((ServletRequest) request));
	}
	
	@Test
	public void testExtractUriHttpServletRequest() {
		Assert.assertNull(RequestUtils.extractUri(null));
		
		AmHttpServletRequest request = new AmHttpServletRequest();
		request.setRequestURI("/context/path/page.extension");
		request.setContextPath("/context");
		Assert.assertNotNull(RequestUtils.extractUri(request));
		
		Assert.assertEquals("path/page.extension", RequestUtils.extractUri(request));
	}
	
	@Test
	public void testGetPath() {
		Assert.assertNull(RequestUtils.getPath(null, "help"));

		AmHttpServletRequest request = new AmHttpServletRequest();
		request.setRequestURI("/context/path/page.extension");
		request.setContextPath("/context");
		
		Assert.assertEquals("/context/help", RequestUtils.getPath(request, "help"));
	}
}

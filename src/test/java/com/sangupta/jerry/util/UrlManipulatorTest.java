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

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @author sangupta
 *
 */
public class UrlManipulatorTest {

	@Test
	public void testDecomposition() {
		UrlManipulator manipulator = new UrlManipulator("http://www.some-random-domain.com:80/abc.html?param1=value1&param2=value2#fragmentme");
		Assert.assertEquals("http", manipulator.getScheme());
		Assert.assertEquals("www.some-random-domain.com", manipulator.getHost());
		Assert.assertEquals(80, manipulator.getPort());
		Assert.assertEquals("abc.html", manipulator.getPath());
		Assert.assertEquals(2, manipulator.getNumQueryParams());
		Assert.assertEquals("fragmentme", manipulator.getFragment());
	}
	
	@Test
	public void testManipulationBasic() {
		UrlManipulator manipulator = new UrlManipulator("hTTp://www.some-RANDOM-domain.com:80/abc.html?param1=value1&param2=value2#fragmentme");
		Assert.assertEquals("http://www.some-random-domain.com/abc.html?param1=value1&param2=value2#fragmentme", manipulator.constructURL());
		
		manipulator.setPort(8080);
		Assert.assertEquals("http://www.some-random-domain.com:8080/abc.html?param1=value1&param2=value2#fragmentme", manipulator.constructURL());
		
		manipulator.setQueryParam("param1", "value3");
		Assert.assertEquals("http://www.some-random-domain.com:8080/abc.html?param1=value3&param2=value2#fragmentme", manipulator.constructURL());
		
		manipulator.removeQueryParam("param1");
		Assert.assertEquals("http://www.some-random-domain.com:8080/abc.html?param2=value2#fragmentme", manipulator.constructURL());
		
		manipulator.setQueryParam("param1", "value4");
		Assert.assertEquals("http://www.some-random-domain.com:8080/abc.html?param1=value4&param2=value2#fragmentme", manipulator.constructURL());
	}
	
	@Test
	public void testConstruct() {
		Assert.assertEquals("http://google.com", new UrlManipulator("google.com", null).constructURL());
		Assert.assertEquals("https://google.com", new UrlManipulator("https", "google.com", null).constructURL());
		Assert.assertEquals("https://google.com", new UrlManipulator("https", "google.com", 80, null).constructURL());
	}
}

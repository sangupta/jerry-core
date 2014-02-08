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
 * @author sangupta
 *
 */
public class UrlCanonicalizerTest {
	
	@Test
	public void testCanonicalization() {
		Assert.assertEquals("http://www.some-random-domain.com", UrlCanonicalizer.canonicalize("http://www.some-random-domain.com/"));
		Assert.assertEquals("http://www.some-random-domain.com", UrlCanonicalizer.canonicalize("hTTp://www.some-RANDOM-domain.com"));
		Assert.assertEquals("http://www.some-random-domain.com/abc.html?param1=value1&param2=value2#fragmentme", UrlCanonicalizer.canonicalize("http://www.some-random-domain.com/abc.html#fragmentme?param2=value2&param1=value1"));
		
		Assert.assertEquals("http://www.some-random-domain.com/abc.html", UrlCanonicalizer.getCanonicalizedBase("http://www.some-random-domain.com/abc.html#fragmentme?param2=value2&param1=value1"));
		Assert.assertEquals("http://www.some-random-domain.com", UrlCanonicalizer.getCanonicalizedRoot("http://www.some-random-domain.com/abc.html#fragmentme?param2=value2&param1=value1"));
	}

}

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

import org.junit.Assert;

import org.junit.Test;

/**
 * @author sangupta
 *
 */
public class TestUrlCanonicalizer {
	
	@Test(expected = InstantiationException.class)
	public void testConstructor() throws InstantiationException {
		new UrlCanonicalizer() { };
	}

	@Test
	public void testCanonicalization() {
		Assert.assertNull(UrlCanonicalizer.canonicalize(null));
		Assert.assertNull(UrlCanonicalizer.canonicalize(""));
		Assert.assertNull(UrlCanonicalizer.canonicalize("\t\t"));

		Assert.assertEquals("http://www.some-random-domain.com", UrlCanonicalizer.canonicalize("http://www.some-random-domain.com/"));
		Assert.assertEquals("http://www.some-random-domain.com", UrlCanonicalizer.canonicalize("hTTp://www.some-RANDOM-domain.com"));
		Assert.assertEquals("http://www.some-random-domain.com/abc.html?param1=value1&param2=value2#fragmentme", UrlCanonicalizer.canonicalize("http://www.some-random-domain.com/abc.html#fragmentme?param2=value2&param1=value1"));

		Assert.assertNull(UrlCanonicalizer.getCanonicalizedBase(null));
		Assert.assertNull(UrlCanonicalizer.getCanonicalizedBase(""));
		Assert.assertNull(UrlCanonicalizer.getCanonicalizedBase("\t\t"));

		Assert.assertEquals("http://www.some-random-domain.com/abc.html", UrlCanonicalizer.getCanonicalizedBase("http://www.some-random-domain.com/abc.html#fragmentme?param2=value2&param1=value1"));

		Assert.assertNull(UrlCanonicalizer.getCanonicalizedRoot(null));
		Assert.assertNull(UrlCanonicalizer.getCanonicalizedRoot(""));
		Assert.assertNull(UrlCanonicalizer.getCanonicalizedRoot("\t\t"));

		Assert.assertEquals("http://www.some-random-domain.com", UrlCanonicalizer.getCanonicalizedRoot("http://www.some-random-domain.com/abc.html#fragmentme?param2=value2&param1=value1"));
	}

}

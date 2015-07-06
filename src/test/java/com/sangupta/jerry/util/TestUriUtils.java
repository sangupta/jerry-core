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

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;

import org.junit.Test;

/**
 * Unit tests for {@link UriUtils} utility class.
 * 
 * @author sangupta
 *
 */
public class TestUriUtils {
	
	@Test
	public void testEncodeURIComponent() {
		Assert.assertEquals(null, UriUtils.encodeURIComponent(null));
		Assert.assertEquals("", UriUtils.encodeURIComponent(""));

		Assert.assertEquals("hello%20world!", UriUtils.encodeURIComponent("hello world!"));
	}
	
	@Test
	public void testDecodeURIComponent() {
		Assert.assertEquals(null, UriUtils.decodeURIComponent(null));
		Assert.assertEquals("", UriUtils.decodeURIComponent(""));

		Assert.assertEquals("hello world!", UriUtils.decodeURIComponent("hello%20world!"));
		Assert.assertEquals("hello world!", UriUtils.decodeURIComponent("hello+world!"));
	}
	
	@Test
	public void testGetBaseUrl() {
		Assert.assertNull(UriUtils.getBaseUrl(null));
		Assert.assertNull(UriUtils.getBaseUrl(""));
		Assert.assertNull(UriUtils.getBaseUrl("abc"));
		Assert.assertEquals("http://www.sangupta.com", UriUtils.getBaseUrl("http://www.sangupta.com/index.html"));
		Assert.assertEquals("http://www.sangupta.com:8080", UriUtils.getBaseUrl("http://www.sangupta.com:8080/abc.html"));
	}
	
	@Test
	public void testRemoveSchemeAndDomain() {
		Assert.assertNull(UriUtils.removeSchemeAndDomain(null));
		Assert.assertNull(UriUtils.removeSchemeAndDomain(""));
		Assert.assertNull(UriUtils.removeSchemeAndDomain("abc"));
		Assert.assertEquals("index.html", UriUtils.removeSchemeAndDomain("http://www.sangupta.com/index.html"));
		Assert.assertEquals("abc.html", UriUtils.removeSchemeAndDomain("http://www.sangupta.com:8080/abc.html"));
	}
	
	@Test
	public void testExtractFileName() {
		Assert.assertEquals("abc.html", UriUtils.extractFileName("http://www.sangupta.com/abc.html"));
		Assert.assertEquals("abc.html", UriUtils.extractFileName("http://www.sangupta.com/abc.html?format=xml"));
		Assert.assertEquals("abc.html", UriUtils.extractFileName("http://www.sangupta.com/abc.html?format=xml&text=help"));
		Assert.assertEquals("abc.html", UriUtils.extractFileName("http://www.sangupta.com/abc.html?format=xml&text=help#anchorName"));
		Assert.assertEquals("abc.html", UriUtils.extractFileName("http://www.sangupta.com/abc.html#anchorName?format=xml&text=help"));
	}

	@Test
	public void testExtractExtension() {
		Assert.assertEquals("html", UriUtils.extractExtension("http://www.sangupta.com/abc.html"));
		Assert.assertEquals("html", UriUtils.extractExtension("http://www.sangupta.com/abc.html?format=xml"));
		Assert.assertEquals("html", UriUtils.extractExtension("http://www.sangupta.com/abc.html?format=xml&text=help"));
		Assert.assertEquals("html", UriUtils.extractExtension("http://www.sangupta.com/abc.html?format=xml&text=help#anchorName"));
		Assert.assertEquals("html", UriUtils.extractExtension("http://www.sangupta.com/abc.html#anchorName?format=xml&text=help"));
		Assert.assertEquals(null, UriUtils.extractExtension("http://news.ycombinator.com/rss"));
	}
	
	@Test
	public void testAddWebPathsMultiple() {
		Assert.assertEquals("", UriUtils.addWebPaths((String[]) null));
		Assert.assertEquals("", UriUtils.addWebPaths(new String[] { "" }));
		Assert.assertEquals("file.html", UriUtils.addWebPaths(new String[] { "file.html" }));
		Assert.assertEquals("one/two/three/four/five", UriUtils.addWebPaths(new String[] { "one", "two", "three", "four", "five" }));
		Assert.assertEquals("one/two/three/four/five", UriUtils.addWebPaths(new String[] { "one/", "/two/", "/three/", "/four/", "/five" }));
	}
	
	@Test
	public void testExtractHost() {
		Assert.assertEquals(null, UriUtils.extractHost("www.google.com"));
		Assert.assertEquals("www.google.com", UriUtils.extractHost("http://www.google.com"));
		Assert.assertEquals("www.google.com", UriUtils.extractHost("http://www.google.com/"));
		Assert.assertEquals("www.google.com", UriUtils.extractHost("http://www.google.com/abc.html"));
	}
	
	@Test
	public void testExtractPath() {
		Assert.assertNull(UriUtils.extractPath(null));
		Assert.assertNull(UriUtils.extractPath("")); 
		
		Assert.assertEquals("", UriUtils.extractPath("www.google.com"));
		Assert.assertEquals("", UriUtils.extractPath("http://www.google.com"));
		Assert.assertEquals("/", UriUtils.extractPath("www.google.com/"));
		Assert.assertEquals("/", UriUtils.extractPath("http://www.google.com/"));
		
		Assert.assertEquals("/abc.html", UriUtils.extractPath("http://www.google.com/abc.html?a=b"));
		Assert.assertEquals("/abc.html", UriUtils.extractPath("http://www.google.com/abc.html?a=b#123"));
		Assert.assertEquals("/abc.html", UriUtils.extractPath("http://www.google.com/abc.html#123?a=b"));
		Assert.assertEquals("/abc.html", UriUtils.extractPath("http://www.google.com/abc.html#ab"));
	}
	
	@Test
	public void testExtractProtocol() {
		Assert.assertNull(UriUtils.extractProtocol(null));
		Assert.assertNull(UriUtils.extractProtocol(""));
		
		Assert.assertNull(UriUtils.extractProtocol("hello world"));
		Assert.assertNull(UriUtils.extractProtocol("//abc.html"));
		
		Assert.assertEquals("http", UriUtils.extractProtocol("http://google.com"));
		Assert.assertEquals("http", UriUtils.extractProtocol("HTTP://google.com"));
		Assert.assertEquals("https", UriUtils.extractProtocol("https://google.com"));
		Assert.assertEquals("xyz", UriUtils.extractProtocol("xyz://google.com"));
	}

	@Test
	public void testAppearsValidUrl() {
		Assert.assertFalse(UriUtils.appearsValidUrl(null));
		Assert.assertFalse(UriUtils.appearsValidUrl(""));
		Assert.assertFalse(UriUtils.appearsValidUrl("\t\t"));
		Assert.assertFalse(UriUtils.appearsValidUrl("\n"));
		Assert.assertFalse(UriUtils.appearsValidUrl("   "));
		
		Assert.assertTrue(UriUtils.appearsValidUrl("abc.html"));
		Assert.assertFalse(UriUtils.appearsValidUrl("abc"));
	}
	
	@Test
	public void testUrlEncode() {
		Map<String, String> map = null;
		Assert.assertEquals("", UriUtils.urlEncode(map));
		
		map = new LinkedHashMap<String, String>(); 
		Assert.assertEquals("", UriUtils.urlEncode(map));
		
		map.put("p1", "v1");
		map.put("p2", "v2");
		Assert.assertEquals("p1=v1&p2=v2", UriUtils.urlEncode(map));
		Assert.assertEquals("p1=v1&p2=v2", UriUtils.urlEncode(map, true));
	}
}

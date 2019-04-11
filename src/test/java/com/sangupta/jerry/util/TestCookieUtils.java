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

import javax.servlet.http.Cookie;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link CookieUtils} class
 *
 * @author sangupta
 *
 */
public class TestCookieUtils {

	@Test
	public void testCreate() {
		Cookie cookie = CookieUtils.createCookie("test-name", "test-value", "somedomain.com", "/");
		Assert.assertNotNull(cookie);
		Assert.assertEquals("test-name", cookie.getName());
		Assert.assertEquals("test-value", cookie.getValue());
		Assert.assertEquals("somedomain.com", cookie.getDomain());
		Assert.assertEquals("/", cookie.getPath());
		Assert.assertNull(cookie.getComment());
		Assert.assertEquals(-1, cookie.getMaxAge());
		Assert.assertFalse(cookie.getSecure());
		Assert.assertEquals(0, cookie.getVersion());

		// test another create
		cookie = CookieUtils.createCookie("test-name", "test-value", "somedomain.com", "/", 3);
		Assert.assertNotNull(cookie);
		Assert.assertEquals("test-name", cookie.getName());
		Assert.assertEquals("test-value", cookie.getValue());
		Assert.assertEquals("somedomain.com", cookie.getDomain());
		Assert.assertEquals("/", cookie.getPath());
		Assert.assertNull(cookie.getComment());
		Assert.assertEquals(259200, cookie.getMaxAge());
		Assert.assertFalse(cookie.getSecure());
		Assert.assertEquals(0, cookie.getVersion());

		// test delete
		CookieUtils.deleteCookie(cookie);
		Assert.assertNotNull(cookie);
		Assert.assertEquals("test-name", cookie.getName());
		Assert.assertEquals("test-value", cookie.getValue());
		Assert.assertEquals("somedomain.com", cookie.getDomain());
		Assert.assertEquals("/", cookie.getPath());
		Assert.assertNull(cookie.getComment());
		Assert.assertEquals(0, cookie.getMaxAge());
		Assert.assertFalse(cookie.getSecure());
		Assert.assertEquals(0, cookie.getVersion());
	}

	@Test
	public void testGet() {
		Cookie[] cookies = null;
		Assert.assertNull(CookieUtils.getCookie(cookies, "sangupta"));

		cookies = new Cookie[2];
		Assert.assertNull(CookieUtils.getCookie(cookies, "sangupta"));

		cookies[0] = CookieUtils.createCookie("cookie1", "value1", "domain", "path");
		cookies[1] = CookieUtils.createCookie("cookie2", "value2", "domain", "path");

		Assert.assertNull(CookieUtils.getCookie(cookies, "sangupta"));
		Assert.assertNull(CookieUtils.getCookie(cookies, null));
		Assert.assertNull(CookieUtils.getCookie(cookies, ""));
		Assert.assertNotNull(CookieUtils.getCookie(cookies, "cookie1"));
		Assert.assertNotNull(CookieUtils.getCookie(cookies, "cookie2"));

		Assert.assertNull(CookieUtils.getCookie(cookies, null, "domain2"));
		Assert.assertNull(CookieUtils.getCookie(cookies, "", "domain2"));
		Assert.assertNull(CookieUtils.getCookie(cookies, "sangupta", "domain2"));
		Assert.assertNull(CookieUtils.getCookie(cookies, "cookie1", "domain2"));
		Assert.assertNull(CookieUtils.getCookie(cookies, "cookie2", "domain2"));
		Assert.assertNotNull(CookieUtils.getCookie(cookies, "cookie1", "domain"));
		Assert.assertNotNull(CookieUtils.getCookie(cookies, "cookie2", "domain"));

		cookies = new Cookie[2];
		Assert.assertNull(CookieUtils.getCookie(cookies, "sangupta"));

		cookies[0] = CookieUtils.createCookie("cookie", "value1", "domain", "path");
		cookies[1] = CookieUtils.createCookie("cookie", "value2", "domain", "path");

		Assert.assertNull(CookieUtils.getCookiesWithName(cookies, null));
		Assert.assertNull(CookieUtils.getCookiesWithName(cookies, ""));
		Assert.assertNotNull(CookieUtils.getCookiesWithName(cookies, "cookie"));
		Assert.assertEquals(2, CookieUtils.getCookiesWithName(cookies, "cookie").length);
	}

	@Test
	public void testGetValue() {
		Cookie[] cookies = null;
		Assert.assertNull(CookieUtils.getCookieValue(cookies, "sangupta"));

		cookies = new Cookie[2];
		Assert.assertNull(CookieUtils.getCookieValue(cookies, "sangupta"));

		cookies[0] = CookieUtils.createCookie("cookie1", "value1", "domain", "path");
		cookies[1] = CookieUtils.createCookie("cookie2", "value2", "domain", "path");
		Assert.assertNull(CookieUtils.getCookieValue(cookies, "sangupta"));
		Assert.assertNotNull(CookieUtils.getCookieValue(cookies, "cookie1"));
		Assert.assertNotNull(CookieUtils.getCookieValue(cookies, "cookie2"));
		Assert.assertEquals("value1", CookieUtils.getCookieValue(cookies, "cookie1", "domain"));
		Assert.assertEquals("value2", CookieUtils.getCookieValue(cookies, "cookie2", "domain"));

		Assert.assertNull(CookieUtils.getCookieValue(cookies, "sangupta", "domain2"));
		Assert.assertNull(CookieUtils.getCookieValue(cookies, "cookie1", "domain2"));
		Assert.assertNull(CookieUtils.getCookieValue(cookies, "cookie2", "domain2"));
		Assert.assertNotNull(CookieUtils.getCookieValue(cookies, "cookie1", "domain"));
		Assert.assertNotNull(CookieUtils.getCookieValue(cookies, "cookie2", "domain"));
		Assert.assertEquals("value1", CookieUtils.getCookieValue(cookies, "cookie1", "domain"));
		Assert.assertEquals("value2", CookieUtils.getCookieValue(cookies, "cookie2", "domain"));
	}

	@Test
	public void setAge() {
		Cookie cookie = CookieUtils.createCookie("cookie1", "value1", "domain", "path");

		Assert.assertEquals(-1, cookie.getMaxAge());

		CookieUtils.setMaxAgeInDays(null, 1);
		CookieUtils.setMaxAgeInDays(cookie, 0);
		CookieUtils.setMaxAgeInDays(cookie, 1);
		Assert.assertEquals(86400, cookie.getMaxAge());

		CookieUtils.setMaxAgeInHours(null, 1);
		CookieUtils.setMaxAgeInHours(cookie, 0);
		CookieUtils.setMaxAgeInHours(cookie, 1);
		Assert.assertEquals(3600, cookie.getMaxAge());

		CookieUtils.setMaxAgeInMinutes(null, 1);
		CookieUtils.setMaxAgeInMinutes(cookie, 0);
		CookieUtils.setMaxAgeInMinutes(cookie, 1);
		Assert.assertEquals(60, cookie.getMaxAge());
	}
}

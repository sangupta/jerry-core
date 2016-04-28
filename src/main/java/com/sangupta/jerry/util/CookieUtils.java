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

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utility functions to work with Java Servlet {@link Cookie}s.
 * 
 * @author sangupta
 *
 */
public abstract class CookieUtils {
    
	/**
	 * Create a new cookie. The cookie will expire at the end of browser
	 * session.
	 * 
	 * @param name
	 *            the name of the cookie
	 * 
	 * @param value
	 *            value to assign to the cookie
	 * 
	 * @param domain
	 *            the domain name for which this cookie needs to be set to
	 * 
	 * @param path
	 *            the path for the cookie
	 * 
	 * @return a Java Servlet {@link Cookie} object for the given values
	 */
	public static Cookie createCookie(String name, String value, String domain, String path) {
		Cookie cookie = new Cookie(name, value);
		
		if(domain != null) {
			cookie.setDomain(domain);
		}
		
		if(path != null) {
			cookie.setPath(path);
		}
		
		return cookie;
	}
	
	/**
	 * Create a cookie with the given amount of time.
	 * 
	 * @param name
	 *            the name of the cookie
	 * 
	 * @param value
	 *            value to assign to the cookie
	 * 
	 * @param domain
	 *            the domain name for which this cookie needs to be set to
	 * 
	 * @param path
	 *            the path for the cookie
	 * 
	 * @param days
	 *            the number of days in which this cookie should expire
	 * 
	 * @return a Java Servlet {@link Cookie} object for the given values
	 */
	public static Cookie createCookie(String name, String value, String domain, String path, int days) {
		Cookie cookie = createCookie(name, value, domain, path);
		setMaxAgeInDays(cookie, days);
		return cookie;
	}
	
	/**
	 * Set a {@link Cookie} object's time-to-live to <code>ZERO</code> so that browsers
	 * delete it as soon as they receive. The cookie must then be set to {@link HttpServletResponse}
	 * object to be sent back to browsers.
	 * 
	 * This method is <code>null-safe</code>.
	 *  
	 * @param cookie the {@link Cookie} to be marked for deletion.
	 */
	public static void deleteCookie(Cookie cookie) {
		if(cookie != null) {
			cookie.setMaxAge(0);
		}
	}
	
	/**
	 * Fetch the {@link Cookie} from the available cookies as passed from the
	 * {@link HttpServletRequest}.
	 * 
	 * @param cookies
	 *            the array of {@link Cookie}s as recevied in
	 *            {@link HttpServletRequest} object
	 * 
	 * @param cookieName
	 *            the cookie name being looked for
	 * 
	 * @return the {@link Cookie} object that matches the given name.
	 * 
	 */
	public static Cookie getCookie(Cookie[] cookies, String cookieName) {
		if(cookieName == null || cookieName.length() == 0) {
			return null;
		}
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie == null) {
					continue;
				}
				
				if(cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Fetch the {@link Cookie} from the available cookies as passed from the
	 * {@link HttpServletRequest}, for the given name and the given domain name.
	 * 
	 * @param cookies
	 *            the array of {@link Cookie}s as recevied in
	 *            {@link HttpServletRequest} object
	 * 
	 * @param cookieName
	 *            the cookie name being looked for
	 * 
	 * @param domain
	 *            the domain name to match for the cookie. If there is no
	 *            perfect match with the domain name, the cookie will not be
	 *            returned.
	 * 
	 * @return the {@link Cookie} object that matches the given name.
	 */
	public static Cookie getCookie(Cookie[] cookies, String cookieName, String domain) {
		if(cookieName == null || cookieName.length() == 0) {
			return null;
		}
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie == null) {
					continue;
				}
				
				if(cookie.getName().equals(cookieName) && domain.equals(cookie.getDomain())) {
					return cookie;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Return multiple cookies with the given name. This method is generally
	 * useful when applications on multiple sub-domains are sending cookies for
	 * the same domain name.
	 * 
	 * @param cookies
	 *            the array of {@link Cookie}s as recevied in
	 *            {@link HttpServletRequest} object
	 * 
	 * @param cookieName
	 *            the cookie name being looked for
	 * 
	 * @return an array of {@link Cookie}s that match the given name.
	 * 
	 */
	public static Cookie[] getCookiesWithName(Cookie[] cookies, String cookieName) {
		if(cookieName == null || cookieName.length() == 0) {
			return null;
		}
		
		Cookie[] found = new Cookie[cookies.length];
		int count = 0;
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(cookieName)) {
					found[count++] = cookie;
				}
			}
		}
		
		if(count < found.length) {
			return Arrays.copyOfRange(found, 0, count);
		}
		
		return found;
	}
	
	/**
	 * Fetch the value of the cookie, for the given name. The method is
	 * <code>null-safe</code>.
	 * 
	 * @param cookies
	 *            the list of {@link Cookie}s as fetched from
	 *            {@link HttpServletRequest}
	 * 
	 * @param cookieName
	 *            the name of the cookie being looked for
	 * 
	 * @return the value of the cookie if found, otherwise <code>null</code>.
	 */
	public static String getCookieValue(Cookie[] cookies, String cookieName) {
		Cookie cookie = getCookie(cookies, cookieName);
		if(cookie == null) {
			return null;
		}
		
		return cookie.getValue();
	}
	
	/**
	 * Fetch the value of the cookie, for the given name. The method is
	 * <code>null-safe</code>.
	 * 
	 * @param cookies
	 *            the list of {@link Cookie}s as fetched from
	 *            {@link HttpServletRequest}
	 * 
	 * @param cookieName
	 *            the name of the cookie being looked for
	 * 
	 * @param domain
	 *            the domain name to match for the cookie
	 * 
	 * @return the value of the cookie if found, otherwise <code>null</code>.
	 */
	public static String getCookieValue(Cookie[] cookies, String cookieName, String domain) {
		Cookie cookie = getCookie(cookies, cookieName, domain);
		if(cookie == null) {
			return null;
		}
		
		return cookie.getValue();
	}

	/**
	 * Set the max age of the cookie in number of days. The method
	 * is <code>null-safe</code>.
	 * 
	 * @param cookie
	 *            the {@link Cookie} object
	 *            
	 * @param days
	 *            the number of days to set the cookie age to
	 */
	public static void setMaxAgeInDays(Cookie cookie, int days) {
		setMaxAgeInHours(cookie, 24 * days);
	}
	
	/**
	 * Set the max age of the cookie in number of hours. The method is
	 * <code>null-safe</code>.
	 * 
	 * @param cookie
	 *            the {@link Cookie} object
	 *            
	 * @param hours
	 *            the number of hours to set the cookie age to
	 */
	public static void setMaxAgeInHours(Cookie cookie, int hours) {
		setMaxAgeInMinutes(cookie, 60 * hours);
	}
	
	/**
	 * Set the max age of the cookie in number of minutes. The method is
	 * <code>null-safe</code>.
	 * 
	 * @param cookie
	 *            the {@link Cookie} object.
	 * 
	 * @param minutes
	 *            the number of minutes to set the cookie age to
	 */
	public static void setMaxAgeInMinutes(Cookie cookie, int minutes) {
		if(cookie == null) {
			return;
		}
		
		if(minutes < 0) {
			return;
		}
		
		cookie.setMaxAge(minutes * 60);
	}
	
}
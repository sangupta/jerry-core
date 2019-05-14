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
import javax.servlet.http.HttpServletRequest;

/**
 * Utility method to work with {@link HttpServletRequest}s.
 *
 * @author sangupta
 *
 */
public abstract class RequestUtils {
	
	protected RequestUtils() throws InstantiationException {
		throw new InstantiationException("Instances of this class are forbidden");
	}

	/**
	 * Extract the base URI and strip off the context path as well as any
	 * JSESSIONID that is appended to the URI.
	 *
	 * @param request
	 *            the {@link ServletRequest} object
	 *
	 * @return the extracted URI path
	 */
	public static String extractUri(ServletRequest request) {
		if(request == null) {
			return null;
		}
		
		if(!(request instanceof HttpServletRequest)) {
			throw new IllegalArgumentException("Request is not of HttpServletRequest type");
		}

		return extractUri((HttpServletRequest) request);
	}

	/**
	 * Extract the base URI and strip off the context path as well as any
	 * JSESSIONID that is appended to the URI.
	 *
	 * @param request
	 *            the {@link HttpServletRequest} object
	 *
	 * @return the extracted URI path
	 *
	 */
	public static String extractUri(HttpServletRequest request) {
		if(request == null) {
			return null;
		}
		
		// extract the URL
		String url = request.getRequestURI();
		if(url.startsWith(request.getContextPath())) {
			url = url.substring(request.getContextPath().length());
		}

		int jsessionID = url.indexOf(";jsessionid=");
		if(jsessionID != -1) {
			url = url.substring(0, jsessionID);
		}

		if(url.startsWith("/")) {
			url = url.substring(1);
		}

		return url;
	}

	/**
	 * Return the URL fragment with the request context path appended.
	 *
	 * @param request
	 *            the {@link HttpServletRequest} instance of the incoming
	 *            request
	 *
	 * @param pathFragment
	 *            the path fragment that needs to be converted to URL
	 *
	 * @return the fragment with request context prefixed
	 */
	public static String getPath(HttpServletRequest request, String pathFragment) {
		if(request == null) {
			return null;
		}
		
		return UriUtils.addWebPaths(request.getContextPath(), pathFragment);
	}

}

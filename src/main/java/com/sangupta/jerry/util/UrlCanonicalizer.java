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

/**
 * A Utility class to canonicalize the URL and return either the entire
 * URL, or parts of it.
 *
 * @author sangupta
 *
 */
public abstract class UrlCanonicalizer {

	/**
	 * Canonicalize the given URL. If the URL is <code>null</code>, empty or contains
	 * only white-spaces, a <code>null</code> is returned back to indicate that this is
	 * not a valid URL.
	 *
	 * @param url the url to be canonicalized
	 *
	 * @return the canonicalized URL
	 */
	public static String canonicalize(String url) {
		if(AssertUtils.isBlank(url)) {
			return null;
		}

		return new UrlManipulator(url).constructURL();
	}

	/**
	 * Method to extract the canonicalized base URL. A base url consists of
	 * scheme, domain, port and path. There are no query parameters, nor any
	 * fragment in the URL. If any are present, they are removed.
	 *
	 * @param url
	 *            the url to be canonicalized.
	 *
	 * @return the canonicalized representation
	 */
	public static String getCanonicalizedBase(String url) {
		if(AssertUtils.isBlank(url)) {
			return null;
		}

		UrlManipulator manipulator = new UrlManipulator(url);
		manipulator.clearAllQueryParams();
		manipulator.setFragment(null);

		return manipulator.constructURL();
	}

	/**
	 * Method to extract the canonicalized root URL. A root url consists of
	 * scheme, domain and port, and points to the root path. There are no query
	 * parameters, or fragments. If any are present, they are removed.
	 *
	 * @param url
	 *            the url to be canonicalized.
	 * @return the canonicalized representation
	 */
	public static String getCanonicalizedRoot(String url) {
		if(AssertUtils.isBlank(url)) {
			return null;
		}

		UrlManipulator manipulator = new UrlManipulator(url);
		manipulator.clearAllQueryParams();
		manipulator.setFragment(null);
		manipulator.setPath(null);

		return manipulator.constructURL();
	}
}

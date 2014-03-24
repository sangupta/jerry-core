/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2014, Sandeep Gupta
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class to manipulate a new Url or an existing url. 
 * The objects of this class should NOT be re-used.
 * Currently, does not support multi-value keys in the query string.
 * 
 * @author sangupta
 *
 */
public class UrlManipulator {
	
	/**
	 * The scheme for pure HTTP non-SSL
	 */
	public static final String HTTP_SCHEME = "http";
	
	/**
	 * Holds the scheme or the protocol
	 */
	private String scheme = HTTP_SCHEME;
	
	/**
	 * Holds the host part - the domain
	 */
	private String host;
	
	/**
	 * Holds the port
	 */
	private int port = 80;
	
	/**
	 * Holds the path
	 */
	private String path;
	
	/**
	 * Holds the query parameters
	 */
	private final Map<String, String> queryParams = new HashMap<String, String>();

	/**
	 * Holds the fragment or page anchor
	 */
	private String fragment;
	
	/**
	 * Construct a new URL for the given host and path. A default scheme of HTTP
	 * and default port of 80 is assumed.
	 * 
	 * @param host
	 *            the domain or host to use
	 * 
	 * @param path
	 *            the server path to use
	 * 
	 */
	public UrlManipulator(String host, String path) {
		this.host = host;
		this.path = path;
	}
	
	/**
	 * Construct a new URL for the given host, port and path. A default scheme
	 * of HTTP is assumed.
	 * 
	 * @param host
	 *            the domain or host to use
	 * 
	 * @param port
	 *            the port number to use
	 * 
	 * @param path
	 *            the server path to use
	 */
	public UrlManipulator(String host, int port, String path) {
		this.host = host;
		this.port = port;
		this.path = path;
	}
	
	/**
	 * Construct a new URL for the given scheme, host and path. A default port
	 * value of 80 is assumed.
	 * 
	 * @param scheme
	 *            the scheme or protocol to use
	 * 
	 * @param host
	 *            the domain or host to use
	 * 
	 * @param path
	 *            the server path to use
	 */
	public UrlManipulator(String scheme, String host, String path) {
		this.scheme = scheme;
		this.host = host;
		this.path = path;
	}
	
	/**
	 * Construct a new URL for the given scheme, host, port and path.
	 * 
	 * @param scheme
	 *            the scheme or protocol to use
	 * 
	 * @param host
	 *            the domain or host to use
	 * 
	 * @param port
	 *            the port number to use
	 * 
	 * @param path
	 *            the server path to use
	 */
	public UrlManipulator(String scheme, String host, int port, String path) {
		this.scheme = scheme;
		this.host = host;
		this.port = port;
		this.path = path;
	}
	
	/**
	 * Decompose a URL to allow for easier manipulation. If the URL does not
	 * consists of a scheme (http/https/ftp/etc), a default scheme of HTTP is
	 * considered. No sanity around extracted parts of the URL is made.
	 * 
	 * @param url
	 *            the url to be decomposed
	 *            
	 * @throws IllegalArgumentException
	 *             if the URL supplied is <code>null</code>
	 */
	public UrlManipulator(String url) {
		this(url, true);
	}
	
	/**
	 * Decompose a URL to allow for easier manipulation. If the URL does not
	 * consists of a scheme (http/https/ftp/etc), a default scheme of HTTP is
	 * considered.
	 * 
	 * @param url
	 *            the url to be decomposed
	 * 
	 * @param sanitizeParts
	 *            if set to <code>true</code>, each part extracted from the url
	 *            is sanitized for values that are allowed. For example, a
	 *            scheme can only have characters, a port can only be an
	 *            integer. The flag should be turned off for better performance
	 *            of the method if it is known that the URL is valid before
	 *            hand.
	 * 
	 * @throws IllegalArgumentException
	 *             if the URL supplied is <code>null</code>, empty or contains
	 *             only white spaces. The exception can also be throws if
	 *             sanitization of parts fails.
	 */
	public UrlManipulator(String url, boolean sanitizeParts) {
		if(AssertUtils.isBlank(url)) {
			throw new IllegalArgumentException("URL to be manipulated cannot be null");
		}
		
		// the location from which scanning is to happen
		int scanStart = 0;
		
		// extract the scheme
		int schemeEnd = url.indexOf("://", scanStart);
		if(schemeEnd == -1) {
			this.scheme = HTTP_SCHEME;
		} else {
			this.scheme = url.substring(0, schemeEnd).toLowerCase();
			scanStart = schemeEnd + 3;
			if(sanitizeParts) {
				checkScheme();
			}
		}
		
		// the base domain
		int domainPortEnd = url.indexOf('/', scanStart);
		if(domainPortEnd == -1) {
			// the entire piece is domain and port
			extractDomainAndPort(url, scanStart, url.length());
			
			// no query param and port can be present
			return;
		}
		
		extractDomainAndPort(url, scanStart, domainPortEnd);
		scanStart = ++domainPortEnd;
		
		// now extract the base path
		final int fragmentStart = url.indexOf('#', scanStart);
		final int queryStart = url.indexOf('?', scanStart);
		
		if(queryStart == -1 && fragmentStart == -1) {
			// we are done extracting everything from this url
			// check for path
			this.path = url.substring(domainPortEnd);
			return;
		}
		
		int basePathStart = queryStart;
		if(basePathStart == -1 || (fragmentStart > 0 && fragmentStart < queryStart)) {
			basePathStart = fragmentStart;
		}
		
		this.path = url.substring(domainPortEnd, basePathStart);
		
		// now extract the query parameters
		if(queryStart > 0) {
			if(queryStart < fragmentStart) {
				extractQueryParams(url, queryStart + 1, fragmentStart);
			} else {
				extractQueryParams(url, queryStart + 1, url.length());
			}
		}
		
		// finally extract the fragment
		if(fragmentStart == -1) {
			return;
		}
		
		if(fragmentStart > queryStart) {
			// fragment is present at the end
			this.fragment = url.substring(fragmentStart + 1);
		} else {
			this.fragment = url.substring(fragmentStart + 1, queryStart);
		}
	}
	
	/**
	 * The most common method before disposing off this object would be to
	 * obtain the final constructed url. This is the method for the same, to
	 * obtain the final representation as a String object.
	 * 
	 * @return the final URL representation for details in this manipulator
	 * 
	 */
	public String constructURL() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(this.scheme);
		builder.append("://");
		builder.append(this.host);
		if(this.port != 80) {
			builder.append(':');
			builder.append(String.valueOf(this.port));
		}
		
		// the path
		boolean pathAppended = false;
		
		if(AssertUtils.isNotEmpty(this.path)) {
			if(this.path.charAt(0) != '/') {
				builder.append('/');
			}
			
			builder.append(this.path);
			pathAppended = true;
		}
		
		// the query parameters
		if(AssertUtils.isNotEmpty(this.queryParams)) {
			if(!pathAppended) {
				builder.append('/');
				pathAppended = true;
			}

			builder.append('?');
			boolean first = true;
			final List<String> keys = new ArrayList<String>(this.queryParams.keySet());
			Collections.sort(keys);
			
			String value;
			for(String key : keys) {
				if(!first) {
					builder.append('&');
				}
				
				first = false;
				
				builder.append(key);
				builder.append('=');
				
				value = this.queryParams.get(key);
				if(AssertUtils.isNotEmpty(value)) {
					builder.append(value);
				}
			}
		}
		
		// the fragment
		if(this.fragment != null) {
			if(!pathAppended) {
				builder.append('/');
			}
			
			builder.append('#');
			builder.append(this.fragment);
		}
		
		return builder.toString();
	}
	
	// Java basic overriding methods follow
	
	/**
	 * Return a {@link String} representation of the URL that can be currently
	 * constructed using this manipulator.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.constructURL();
	}
	
	// Methods for manipulation start here
	
	/**
	 * Set the new scheme for this URL.
	 * 
	 * @param newScheme
	 *            the new scheme
	 * 
	 * @throws IllegalArgumentException
	 *             if the scheme is <code>null</code> or empty.
	 * 
	 */
	public void setScheme(String newScheme) {
		if(AssertUtils.isEmpty(newScheme)) {
			throw new IllegalArgumentException("Scheme provided cannot be null/empty");
		}
		
		this.scheme = newScheme.toLowerCase();
	}
	
	/**
	 * Set the new host for this URL.
	 * 
	 * @param host
	 *            the new host
	 * 
	 * @throws IllegalArgumentException
	 *             if the host is <code>null</code> or empty.
	 */
	public void setHost(String host) {
		if(AssertUtils.isBlank(host)) {
			throw new IllegalArgumentException("Host provided cannot be null/empty");
		}
		
		this.host = host.toLowerCase();
	}
	
	/**
	 * Set the new port for this URL.
	 * 
	 * @param port
	 *            the value of the port
	 * 
	 * @throws IllegalArgumentException
	 *             if the value of the port is negative
	 * 
	 */
	public void setPort(int port) {
		if(port < 0) {
			throw new IllegalArgumentException("The provided port cannot be negative");
		}
		
		this.port = port;
	}
	
	/**
	 * Set the new path. A <code>null</code> value indicates that we want to
	 * reset to root path.
	 * 
	 * @param path
	 *            the new path for this URL.
	 */
	public void setPath(String path) {
		if(path == null) {
			this.path = null;
			return;
		}
		
		if(path.charAt(0) == '/') {
			this.path = path.substring(1);
			return;
		}
		
		this.path = path;
	}
	
	/**
	 * Set the new fragment. A value of <code>null</code> or empty string, or a string full
	 * of white-spaces, indicate that we don't need a fragment.
	 * 
	 * @param fragment
	 */
	public void setFragment(String fragment) {
		if(fragment == null) {
			this.fragment = null;
			return;
		}
		
		fragment = fragment.trim();
		if(fragment.length() == 0) {
			this.fragment = null;
			return;
		}
		
		if(fragment.charAt(0) == '#') {
			this.fragment = fragment.substring(1);
			return;
		}
		
		this.fragment = fragment;
	}
	
	/**
	 * Clear all query parameters that may be present in this URL construct.
	 * 
	 */
	public void clearAllQueryParams() {
		this.queryParams.clear();
	}
	
	/**
	 * Add the given key-value pair as query parameter. The value will be
	 * percent encoded.
	 * 
	 * @param key
	 *            the request parameter name
	 *            
	 * @param value
	 *            the parameter value
	 * 
	 * @throws IllegalArgumentException
	 *             if the key is empty/null
	 */
	public void setQueryParam(String key, String value) {
		this.setQueryParam(key, value, true);
	}
	
	/**
	 * Add the given key-value pair as query parameter, optionally encoding the
	 * value if desired. Encoding used is percent-encoding.
	 * 
	 * @param key
	 *            the request parameter name
	 *            
	 * @param value
	 *            the parameter value
	 *            
	 * @param encodeValue
	 *            whether the given value should be percent-encoded or not
	 * 
	 * @throws IllegalArgumentException
	 *             if the key is empty/null
	 */
	public void setQueryParam(String key, String value, boolean encodeValue) {
		if(AssertUtils.isEmpty(key)) {
			throw new IllegalArgumentException("Key cannot be null/empty");
		}
		
		if(AssertUtils.isEmpty(value)) {
			this.queryParams.put(key, null);
			return;
		}
		
		if(!encodeValue) {
			this.queryParams.put(key, value);
			return;
		}
		
		this.queryParams.put(key, UriUtils.encodeURIComponent(value));
	}
	
	/**
	 * Remove the query parameter with the given name.
	 * 
	 * @param key
	 *            the request parameter name
	 *            
	 */
	public void removeQueryParam(String key) {
		this.queryParams.remove(key);
	}
	
	// Simple accessors methods on the internal values start here
	
	/**
	 * Return the current scheme
	 * 
	 * @return the current scheme
	 */
	public String getScheme() {
		return this.scheme;
	}
	
	/**
	 * Return the value of the current host.
	 * 
	 * @return the current host
	 */
	public String getHost() {
		return this.host;
	}
	
	/**
	 * Return the value of the current port
	 * 
	 * @return the current port
	 */
	public int getPort() {
		return this.port;
	}
	
	/**
	 * Return the value of the current path
	 * 
	 * @return the current server path
	 */
	public String getPath() {
		return this.path;
	}
	
	/**
	 * Return the value of the current fragment
	 * 
	 * @return the current fragment
	 */
	public String getFragment() {
		return this.fragment;
	}
	
	/**
	 * Return the number of query parameters
	 * 
	 * @return the current query parameters
	 */
	public int getNumQueryParams() {
		if(this.queryParams == null) {
			return 0;
		}
		
		return this.queryParams.size();
	}
	
	// Supporting internal methods start
	
	/**
	 * Extract the domain name and port from the given URL, where domain and
	 * port start and end indexes are provided.
	 * 
	 * @param url
	 *            the url from which values are extracted
	 * 
	 * @param start
	 *            the start index
	 * 
	 * @param end
	 *            the end index
	 */
	private void extractDomainAndPort(String url, int start, int end) {
		int hasPort = url.indexOf(':', start);
		if(hasPort != -1 && hasPort < end) {
			// port exists
			this.host = url.substring(start, hasPort).toLowerCase();
			
			String portValue = url.substring(hasPort + 1, end);
			
			try {
				this.port = Integer.parseInt(portValue);
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException("Port value cannot be parsed to an integer value");
			}
		} else {
			this.host = url.substring(start, end).toLowerCase();
			this.port = 80;
		}
	}
	
	/**
	 * Extract all the query parameters from this URL. Parse them and extract
	 * the key value pairs from this segment of the url.
	 * 
	 * @param url
	 *            the url from which query paramters need to be extracted
	 * 
	 * @param start
	 *            the starting index for query parameters
	 * 
	 * @param end
	 *            the ending index for query paramets
	 * 
	 */
	private void extractQueryParams(String url, int start, int end) {
		String segment = url.substring(start, end);
		
		String[] tokens = segment.split("&");
		for(String token : tokens) {
			String[] pair = token.split("=");
			if(pair.length != 2) {
				throw new IllegalArgumentException("Query parameter is not a valid pair: " + token);
			}
			
			this.queryParams.put(pair[0], pair[1]);
		}
	}

	/**
	 * Method that checks the scheme to be a valid value. The valid
	 * values in a scheme are an alphabetic character or a digit.
	 * 
	 */
	private void checkScheme() {
		if(this.scheme == null || this.scheme.trim().length() == 0) {
			throw new IllegalArgumentException("Scheme cannot be null/empty");
		}
		
		char[] chars = this.scheme.toCharArray();
		for(char c : chars) {
			if(!(isDigit(c) || isAlphabet(c))) {
				throw new IllegalArgumentException("Invalid characters in scheme: " + this.scheme);
			}
		}
	}

	private static boolean isDigit(char c) {
		if(('0' <= c ) && (c <= '9')) {
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	private static boolean isAlphabet(char c) {
		if(('a' <= c ) && (c <= 'z')) {
			return true;
		}

		if(('A' <= c ) && (c <= 'Z')) {
			return true;
		}

		return false;
	}
	
}

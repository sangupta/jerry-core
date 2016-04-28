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

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility functions around the URI.
 * 
 * @author sangupta
 *
 */
public abstract class UriUtils {
    
	/**
	 * My own logger instance
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UriUtils.class);

	/**
	 * Characters that are allowed in a URI.
	 */
	private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";
	
	/**
	 * Encode the given string as a URI component. A URI component is part of
	 * the URI like a query parameter value, or the fragment name. In percent
	 * encoded values, small-case letters will be used.
	 * 
	 * The method is <code>null</code>-safe.
	 * 
	 * @param input
	 *            the string that needs to be encoded
	 * 
	 * @return the encoded representation
	 */
	public static String encodeURIComponent(String input) {
		return encodeURIComponent(input, false);
	}

	/**
	 * Function to convert a given string into URI encoded format.
	 * 
	 * The method is <code>null</code>-safe.
	 * 
	 * @param input
	 *            the source string
	 * 
	 * @param upperCase
	 *            whether to use upper-case or lower-case letters in the percent
	 *            encoded representation
	 * 
	 * @return the encoded string
	 */
	public static String encodeURIComponent(String input, boolean upperCase) {
		if (AssertUtils.isEmpty(input)) {
			return input;
		}

		int l = input.length();
		StringBuilder output = new StringBuilder(l * 3);
		try {
			for (int i = 0; i < l; i++) {
				String e = input.substring(i, i + 1);
				if (ALLOWED_CHARS.indexOf(e) == -1) {
					byte[] bytes = e.getBytes("utf-8");
					for(byte b : bytes) {
						output.append('%');
						output.append(StringUtils.asHex(b).toUpperCase());
					}
					continue;
				}
				output.append(e);
			}
			return output.toString();
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Unable to encode bytes to UTF-8", e);
		}
		
		return input;
	}
	
	/**
	 * Function to decode a given string from URI encoded format.
	 * 
	 * @param encodedURI
	 *            the encoded string component
	 *            
	 * @return the decoded string
	 */
	public static String decodeURIComponent(String encodedURI) {
		if(AssertUtils.isEmpty(encodedURI)) {
			return encodedURI;
		}
		
		char actualChar;
		StringBuffer buffer = new StringBuffer();

		int bytePattern, sumb = 0;

		for (int index = 0, more = -1; index < encodedURI.length(); index++) {
			actualChar = encodedURI.charAt(index);

			switch (actualChar) {
				case '%': {
					actualChar = encodedURI.charAt(++index);
					int hb = (Character.isDigit(actualChar) ? actualChar - '0' : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
					actualChar = encodedURI.charAt(++index);
					int lb = (Character.isDigit(actualChar) ? actualChar - '0' : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
					bytePattern = (hb << 4) | lb;
					break;
				}

				case '+': {
					bytePattern = ' ';
					break;
				}

				default: {
					bytePattern = actualChar;
				}
			}

			if ((bytePattern & 0xc0) == 0x80) { // 10xxxxxx
				sumb = (sumb << 6) | (bytePattern & 0x3f);
				if (--more == 0)
					buffer.append((char) sumb);
			} else if ((bytePattern & 0x80) == 0x00) { // 0xxxxxxx
				buffer.append((char) bytePattern);
			} else if ((bytePattern & 0xe0) == 0xc0) { // 110xxxxx
				sumb = bytePattern & 0x1f;
				more = 1;
			} else if ((bytePattern & 0xf0) == 0xe0) { // 1110xxxx
				sumb = bytePattern & 0x0f;
				more = 2;
			} else if ((bytePattern & 0xf8) == 0xf0) { // 11110xxx
				sumb = bytePattern & 0x07;
				more = 3;
			} else if ((bytePattern & 0xfc) == 0xf8) { // 111110xx
				sumb = bytePattern & 0x03;
				more = 4;
			} else { // 1111110x
				sumb = bytePattern & 0x01;
				more = 5;
			}
		}
		
		return buffer.toString();
	}
	
	/**
	 * Extract the file name from the URL removing the scheme, domain, query
	 * params and named anchor, if present.
	 * 
	 * @param url
	 *            the URL to be used
	 * 
	 * @return extracted filename from the URL
	 */
	public static String extractFileName(String url) {
		int index1 = url.indexOf('?');
		int index2 = url.indexOf('#');
		
		if(index1 == -1) {
			index1 = url.length() + 1;
		}
		
		if(index2 == -1) {
			index2 = url.length() + 1;
		}
		
		int index = Math.min(index1, index2);
		if(index < url.length()) {
			url = url.substring(0, index);
		}
		
		index1 = url.lastIndexOf('/');
		index2 = url.lastIndexOf('\\');
		
		index = Math.max(index1, index2);
		
		url = url.substring(index + 1);
		
		return url;
	}

	/**
	 * Extract the extension from the given URL.
	 * 
	 * @param url
	 *            the url from which the extension needs to be extracted.
	 * 
	 * @return the extracted extension
	 * 
	 * @throws NullPointerException
	 *             if the URL presented is <code>null</code>
	 */
	public static String extractExtension(String url) {
		// check for any slash characters that remain
		int index = url.lastIndexOf('/');
		if(index != -1) {
			url = url.substring(index + 1);
		}

		// now for the dot part
		index = url.lastIndexOf('.');
		
		// check if extension present
		if(index == -1) {
			return null;
		}
		
		url = url.substring(index + 1);
		
		// query param
		int end = url.indexOf('?');
		if(end != -1) {
			url = url.substring(0, end);
		}
		
		// anchor name
		end = url.indexOf('#');
		if(end != -1) {
			url = url.substring(0, end);
		}
		
		return url;
	}

	/**
	 * Encode the given set of parameters into a URL format, considering that the parameter
	 * values are already encoded.
	 * 
	 * @param params the url parameters that need to be encoded
	 * 
	 * @return string representation of the parameters
	 * 
	 */
	public static String urlEncode(Map<String, String> params) {
		return urlEncode(params, false);
	}

	/**
	 * URL encode the given list of parameters.
	 * 
	 * @param params
	 *            the key-value pair of params that need to be encoded
	 * 
	 * @param encodeValues
	 *            whether the value portions need to be URL-encoded or not
	 * 
	 * @return a string representation of the URL parameters
	 * 
	 */
	public static String urlEncode(Map<String, String> params, boolean encodeValues) {
		if(AssertUtils.isEmpty(params)) {
			return StringUtils.EMPTY_STRING;
		}
		
		Set<Entry<String, String>> entrySet = params.entrySet();
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for(Entry<String, String> entry : entrySet) {
			if(!first) {
				builder.append("&");
			}
			
			first = false;
			
			builder.append(entry.getKey());
			builder.append("=");
			
			if(encodeValues) {
				builder.append(encodeURIComponent(entry.getValue()));
			} else {
				builder.append(entry.getValue());
			}
		}
		
		return builder.toString();
	}
	
	/**
	 * Normalizes a given URL. Add the protocol if necessary, convert domain to lower-case,
	 * remove port number if it is 80, align the query parameters, then sort them, remove
	 * the anchor link, change https to http
	 * 
	 * @param taintedURL the URL that may be tainted
	 * 
	 * @return the normalized URL
	 * 
	 * @deprecated {@link UrlCanonicalizer} or {@link UrlManipulator} classes should be used
	 * instead of this
	 */
	public static String normalizeUrl(String taintedURL) {
		if(AssertUtils.isEmpty(taintedURL)) {
			return taintedURL;
		}
		
		int hasProtocol = taintedURL.indexOf("://");
		if(hasProtocol == -1) {
			// no protocol found
			// append HTTP to the URL
			taintedURL = "http://" + taintedURL;
		}
		
		final URL url;
        try {
            url = new URL(taintedURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL: " + taintedURL);
        }

        final String path = url.getPath().replace("/$", "");
        final SortedMap<String, String> params = createParameterMap(url.getQuery());
        final int port = url.getPort();
        final String queryString;

        if (params != null) {
            queryString = "?" + canonicalize(params);
        } else {
            queryString = "";
        }
        
        StringBuffer sb = new StringBuffer();
        sb.append(url.getProtocol());
        sb.append("://");
        sb.append(url.getHost());
        if(port != -1 && port != 80) {
        	sb.append(":");
        	sb.append(port);
        }
        sb.append(path);
        sb.append(queryString);
        
        return sb.toString();
	}
	
	/**
	 * Takes a query string, separates the constituent name-value pairs, and
	 * stores them in a SortedMap ordered by lexicographical order.
	 * 
	 * @param queryString
	 *            the query string that needs to be parsed
	 * 
	 * @return a {@link SortedMap} instance of the parameters, or
	 *         <code>null</code> if the query string is <code>null</code> or empty
	 */
    private static SortedMap<String, String> createParameterMap(final String queryString) {
        if (AssertUtils.isEmpty(queryString)) {
            return null;
        }

        final String[] pairs = queryString.split("&");
        final Map<String, String> params = new HashMap<String, String>(pairs.length);

        for (final String pair : pairs) {
            if (pair.length() < 1) {
                continue;
            }

            String[] tokens = pair.split("=", 2);
            for (int j = 0; j < tokens.length; j++) {
                try {
                    tokens[j] = URLDecoder.decode(tokens[j], "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
            
            switch (tokens.length) {
                case 1:
                    if (pair.charAt(0) == '=') {
                        params.put("", tokens[0]);
                    } else {
                        params.put(tokens[0], "");
                    }
                    
                    break;

                case 2:
                    params.put(tokens[0], tokens[1]);
                    break;
            }
        }

        return new TreeMap<String, String>(params);
    }
    
    /**
	 * Canonicalize the query string.
	 * 
	 * @param sortedParamMap
	 *            Parameter name-value pairs in lexicographical order.
	 *            
	 * @return canonical form of query string.
	 */
    private static String canonicalize(final SortedMap<String, String> sortedParamMap) {
        if (sortedParamMap == null || sortedParamMap.isEmpty()) {
            return "";
        }

        final StringBuffer sb = new StringBuffer(350);
        final Iterator<Map.Entry<String, String>> iter = sortedParamMap.entrySet().iterator();

        while (iter.hasNext()) {
            final Map.Entry<String, String> pair = iter.next();
            sb.append(percentEncodeRfc3986(pair.getKey()));
            sb.append('=');
            sb.append(percentEncodeRfc3986(pair.getValue()));
            if (iter.hasNext()) {
                sb.append('&');
            }
        }

        return sb.toString();
    }

    /**
	 * Percent-encode values according the RFC 3986. The built-in Java
	 * URLEncoder does not encode according to the RFC, so we make the extra
	 * replacements.
	 * 
	 * @param string
	 *            Decoded string.
	 *            
	 * @return Encoded string per RFC 3986.
	 */
    private static String percentEncodeRfc3986(final String string) {
        try {
            return URLEncoder.encode(string, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            return string;
        }
    }

	/**
	 * Extract the host value from the URL. If there is no scheme 
	 * separator: <code>://</code> available, a <code>null</code> is 
	 * returned. No checks for the sanity of the host value are made.
	 * 
	 * Also, the value is NOT canonicalized before returning. 
	 * 
	 * @param url
	 *            the URL from which host/domain needs to be extracted
	 * 
	 * @return the extracted domain/host
	 * 
	 */
	public static String extractHost(String url) {
		if(AssertUtils.isEmpty(url)) {
			return null;
		}
		
		int start = url.indexOf("://");
		if(start == -1) {
			// we must check if URL startwith //
			if(!url.startsWith("//")) {
				return null;
			}
			
			start = -1; // -1 because we add 3 down below considering we matched :// and not //
		}
		
		start += 3;
		int end = url.indexOf('/', start);
		if(end == -1) {
			return url.substring(start);
		}
		
		return url.substring(start, end);
	}
	
	/**
	 * Extract the path value from the URL. The path in a URL is considered from
	 * the first leading slash to the end before the query or the fragment
	 * separator.
	 * 
	 * @param url
	 *            the URL from which the path needs to be extracted
	 * 
	 * @return the extracted path value, or <code>null</code> if url is
	 *         <code>null</code> or empty, or empty string if path is not found
	 */
	public static String extractPath(String url) {
		if(AssertUtils.isEmpty(url)) {
			return null;
		}
		
		int schemeEnd = url.indexOf("://");
		if(schemeEnd >= 0) {
			schemeEnd += 3;
		}
		
		int pathStart = url.indexOf('/', schemeEnd);
		if(pathStart == -1) {
			// no path present - return an empty string
			return StringUtils.EMPTY_STRING;
		}
		
		// find query and fragment separators
		int queryStart = url.indexOf('?', pathStart);
		int fragmentStart = url.indexOf('#', pathStart);
		
		if(queryStart == -1 && fragmentStart == -1) {
			return url.substring(pathStart);
		}
		
		if(queryStart != -1 && fragmentStart != -1) {
			// find min of both
			int min = (queryStart < fragmentStart) ? queryStart : fragmentStart;
			return url.substring(pathStart, min);
		}
		
		if(queryStart != -1) {
			return url.substring(pathStart, queryStart);
		}
		
		return url.substring(pathStart, fragmentStart);
	}

	/**
	 * Extract the protocol or the scheme from the given URL. For example, in
	 * the URL http://www.sangupta.com, the protocol is http. Neither checks for
	 * the validily of the scheme is made, nor the value is canonicalized.
	 * 
	 * @param url
	 *            the URL from which the scheme/protocol needs to be extracted.
	 * 
	 * @return the scheme/protocol if found, or <code>null</code>.
	 */
	public static String extractProtocol(String url) {
		if(AssertUtils.isEmpty(url)) {
			return null;
		}
		
		int index = url.indexOf("://");
		if(index == -1) {
			return null;
		}
		
		return url.substring(0, index).toLowerCase();	
	}

	/**
	 * Extract the base url (scheme + domain) from the given URL.
	 * 
	 * @param url
	 *            the url from which the information needs to be extracted
	 * 
	 * @return the base URL as extracted, or <code>null</code> in case the URL
	 * 		   is empty or cannot be parsed properly.
	 */
	public static String getBaseUrl(String url) {
		if(AssertUtils.isEmpty(url)) {
			return null;
		}

		int index = url.indexOf("://");
		index = url.indexOf('/', index + 3);
		
		if(index == -1) {
			return null;
		}
		
		return url.substring(0, index);
	}
	
	/**
	 * Remove the scheme and domain name from the url and return the entire
	 * path, query string and name anchor, if present.
	 * 
	 * @param url
	 *            the URL from which the information that needs to be stripped
	 *            off
	 * 
	 * @return the url without scheme and domain, or <code>null</code> in case
	 *         the URL is empty or cannot be parsed properly.
	 * 
	 */
	public static String removeSchemeAndDomain(String url) {
		if(AssertUtils.isEmpty(url)) {
			return null;
		}
		
		int index = url.indexOf("://");
		index = url.indexOf('/', index + 3);
		
		if(index == -1) {
			return null;
		}
		
		return url.substring(index + 1);
	}

	/**
	 * Add two web paths making sure that we only have one forward slash in the
	 * URL.
	 * 
	 * @param base
	 *            the base path
	 * 
	 * @param suffix
	 *            the suffix to add
	 * 
	 * @return the combined URL safe string
	 */
	public static String addWebPaths(String base, String suffix) {
		StringBuilder builder = new StringBuilder(base);
		if(base.endsWith("/")) {
			if(suffix.startsWith("/")) {
				builder.append(suffix.substring(1));
			} else {
				builder.append(suffix);
			}
		} else {
			if(suffix.startsWith("/")) {
				builder.append(suffix);
			} else {
				builder.append('/');
				builder.append(suffix);
			}
		}
		
		return builder.toString();
	}
	
	/**
	 * Add multiple web-path components given in the order.
	 * 
	 * @param components
	 *            the components that need to be added
	 * 
	 * @return the complete added web-paths
	 * 
	 */
	public static String addWebPaths(String... components) {
		if(components == null || components.length == 0) {
			return "";
		}
		
		if(components.length == 1) {
			return components[0];
		}
		
		StringBuilder builder = new StringBuilder(components[0]);
		for(int index = 1; index < components.length; index++) {
			String suffix = components[index];
			
			if(AssertUtils.isEmpty(suffix)) {
				continue;
			}
			
			if(builder.length() > 2 && builder.charAt(builder.length() - 1) == '/') {
				if(suffix.startsWith("/")) {
					builder.append(suffix.substring(1));
				} else {
					builder.append(suffix);
				}
			} else {
				if(suffix.startsWith("/")) {
					builder.append(suffix);
				} else {
					builder.append('/');
					builder.append(suffix);
				}
			}
		}
		return builder.toString();
	}

	/**
	 * Simple function to see if a string resembles a URL or not.
	 * 
	 * @param url
	 *            the string to be tested
	 * 
	 * @return <code>true</code> if the string appears to be a valid url,
	 *         <code>false</code> otherwise
	 */
	public static boolean appearsValidUrl(String url) {
		try {
			UrlManipulator manipulator = new UrlManipulator(url, true);
			String host = manipulator.getHost();
			if(host != null) {
				if(host.indexOf('.') == -1) {
					return false;
				}
			}
			
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}
	
}
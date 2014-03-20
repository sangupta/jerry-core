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

/**
 * Utility class to work with XML content. Provides methods to extract
 * values where in XML content need not be parsed, only searched and then
 * extracted.
 * 
 * @author sangupta
 *
 */
public class XMLUtils {
	
	/**
	 * Extract parameter value from XML content. The content is not XML parsed
	 * but the parameter searched inside the content.
	 * 
	 * @param content
	 *            the XML
	 *            
	 * @param param
	 *            the parameter that needs to be extracted
	 * 
	 * @return the extracted value or <code>null</code> if param is not found.
	 */
	public static String extractParam(String content, String param) {
		String find = param + "=\"";
		int si = content.indexOf(find);
		if(si == -1) {
			return null;
		}
		
		int ei = content.indexOf('"', si + find.length());
		if(ei == -1) {
			return null;
		}
		
		return content.substring(si + find.length(), ei);
	}

	/**
	 * Extract tag content from XML content. The content is not parsed but the
	 * tag starting and ending searched in the XML string.
	 * 
	 * @param content
	 *            the XML
	 * 
	 * @param tag
	 *            the tag that needs to be extracted
	 * 
	 * @return the extracted tag, or <code>null</code> if the tag is not found
	 * 
	 */
	public static String extractTag(String content, String tag) {
		String start = "<" + tag + ">";
		int si = content.indexOf(start);
		if(si == -1) {
			return null;
		}
		
		String end = "</" + tag + ">";
		int ei = content.indexOf(end, si + start.length());
		if(ei == -1) {
			return null;
		}
		
		return content.substring(si + start.length(), ei);
	}

}

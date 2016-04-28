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

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.SourceFormatter;
import net.htmlparser.jericho.StartTag;

/**
 * Utility functions around HTML code.
 * 
 * @author sangupta
 *
 */
public class HtmlUtils {
	
	/**
	 * Tidy the HTML source by reformatting the entire HTML. This is
	 * particularly useful when the application needs to emit HTML.
	 * 
	 * @param htmlSource
	 *            the unformatted HTML source
	 * 
	 * @return the formatted HTML source.
	 * 
	 */
	public static String tidyHtml(String htmlSource) {
		if(htmlSource == null) {
			return htmlSource;
		}
		
		try {
			Source source = new Source(htmlSource) ;
			StringWriter writer = new StringWriter();
			new SourceFormatter(source).setIndentString("  ").setTidyTags(true).writeTo(writer);
			writer.close();
			
			return writer.toString();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return htmlSource;
	}
	
	/**
	 * Return all tags that start with the given name.
	 * 
	 * @param htmlBody
	 *            the HTML of the page
	 *            
	 * @param tagName
	 *            the name of the tag being looked for
	 *            
	 * @return an {@link ArrayList} of all starting tags
	 */
	public static List<StartTag> getAllTags(String htmlBody, String tagName) {
		if(AssertUtils.isEmpty(htmlBody)) {
			return null;
		}
		
		Source source = null;
		try {
			source = new Source(htmlBody);
			
			List<StartTag> tags = source.getAllStartTags(tagName);
			return tags;
		} catch(Exception e) {
			// we are unable to parse the page body
			// and extract the links
		}
		
		return null;
	}
	
	/**
	 * Extract the values of the specified attribute 'attributeToExtract' of all
	 * tags that start with the given 'tagName' in the HTML body. Also, if
	 * matching attributes are specified then they must match the given values.
	 * Also, 'ignoreMissingAttributes' this defines what to do if the matching
	 * attribute is not present in the given attributes of the tag.
	 * 
	 * @param htmlBody
	 *            the html body represented as string
	 * 
	 * @param tagName
	 *            the tag name to look for
	 * 
	 * @param attributeToExtract
	 *            the attribute to extract
	 * 
	 * @param matchingAttributes
	 *            the map of matching attributes and values, that need to be
	 *            matched if present
	 * 
	 * @param ignoreMissingAttributes
	 *            should we ignore any missing attribute when matching list of
	 *            given attributes
	 * 
	 * @return the list of all values, of all such matching tags
	 * 
	 */
	public static List<String> getAttributeForAllTags(final String htmlBody, final String tagName, final String attributeToExtract, final Map<String, String> matchingAttributes, final boolean ignoreMissingAttributes) {
		List<StartTag> tags = getAllTags(htmlBody, tagName);
		if(AssertUtils.isEmpty(tags)) {
			return null;
		}
		
		final List<String> values = new ArrayList<String>();
		final boolean checkMatching = AssertUtils.isNotEmpty(matchingAttributes);
		
		for(StartTag tag : tags) {
			Attributes attributes = tag.getAttributes();
			if(attributes == null || attributes.isEmpty()) {
				continue;
			}
			
			// check if we have the matching attributes
			boolean readyToExtract = true;
			if(checkMatching) {
				for(Entry<String, String> entry : matchingAttributes.entrySet()) {
					final String attributeName = entry.getKey();
					final String attributeValue = entry.getValue();
					
					Attribute attribute = attributes.get(attributeName);
					if(attribute != null) {
						// not a matching value
						if(!(attribute.getValue().equalsIgnoreCase(attributeValue))) {
							// attribute value does not match
							readyToExtract = false;
							break;
						}
					} else {
						if(!ignoreMissingAttributes) {
							// we cannot ignore missing attributes
							// check for next tag
							readyToExtract = false;
							break;
						}
					}
				}
			}
			
			// extract the value of the tag that we need
			if(readyToExtract) {
				Attribute attribute = attributes.get(attributeToExtract);
				if(attribute != null) {
					values.add(attribute.getValue());
				}
			}
		}
		
		// if the set is still empty - return null
		if(values.isEmpty()) {
			return null;
		}
		
		return values;
	}
	
	/**
	 * Strip the given HTML content to specified text length. All opening
	 * tags are then closed to make sure that the HTML is perfectly safe.
	 * 
	 * Tags such as <code>br</code> are skipped for closing.
	 * 
	 * @param content the HTML content that you want to trim down
	 * @param length the desired length of the text field
	 * @return the HTML code that contains text trimmed down to said length
	 */
	public static String trimHTML(final String content, final int length) {
		int currentIndex = 0;
		int chosenTextLength = 0;
		String tag;
		Stack<String> tags = new Stack<String>();
		do {
			int index = content.indexOf('<', currentIndex);
			if(index > currentIndex) {
				chosenTextLength += (index - currentIndex - 1);
				currentIndex = index;
			}
			
			if(chosenTextLength >= length) {
				break;
			}
			
			if(index != -1) {
				index = content.indexOf('>', index);
				tag = content.substring(currentIndex + 1, index);
				if(!tag.startsWith("/")) {
					if(tag.endsWith("/")) {
						tag = tag.substring(0, tag.length() - 1);
					}
					
					tags.push(tag.trim());
				} else {
					tag = tag.substring(1);
					do {
						if(tags.size() == 0) {
							break;
						}
						
						String pop = tags.pop();
						if(pop.equalsIgnoreCase(tag)) {
							break;
						}
					} while(true);
				}
				
				currentIndex = index;
			}
			
			if(index == -1) {
				break;
			}
		} while(true);
		
		// this implies that the content did not have
		// any HTML tag in it
		if(chosenTextLength == 0) {
			// trim the text to last available word within the length
			String text;
			if(length < content.length()) {
				text = content.substring(0, length);

				int index = text.lastIndexOf(' ');
				if(index > -1) {
					text = text.substring(0, index);
				}
			} else {
				text = content;
			}
			
			return text;
		}
		
		if(chosenTextLength > length) {
			int subtract = chosenTextLength - length;
			currentIndex = currentIndex - subtract;
		}
		
		if(tags.size() == 0) {
			return content.substring(0, currentIndex);
		}
		
		StringBuilder builder = new StringBuilder(content.substring(0, currentIndex));
		int size = tags.size();
		for(int index = 0; index < size; index++) {
			tag = tags.pop();
			
			if(!"br".equalsIgnoreCase(tag)) {
				builder.append("</");
				builder.append(tag);
				builder.append('>');
			}
		}
		
		return builder.toString();
	}
	
	/**
	 * Convert the entries in the map to a string object separated by a
	 * <code>&lt;br /&gt;</code> tag.
	 * 
	 * @param map
	 *            the values to convert
	 * 
	 * @return the string representation
	 */
	public static String mapAsHtmlString(Map<String, String> map) {
		if(AssertUtils.isEmpty(map)) {
			return StringUtils.BLANK_STRING;
		}
		
		StringBuilder builder = new StringBuilder();
		for(Entry<String, String> entry : map.entrySet()) {
			builder.append(entry.getKey());
			builder.append(": ");
			builder.append(entry.getValue());
			builder.append("<br />");
		}
		
		return builder.toString();
	}
	
}
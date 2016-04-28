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

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;

/**
 * @author sangupta
 *
 */
public abstract class DomUtils {
    
	/**
	 * Returns the value of the attribute from the element.
	 * 
	 * @param element
	 *            the element from which the attribute is to be read
	 * 
	 * @param attributeName
	 *            the attribute name
	 *
	 * @return the value of the attribute
	 * 
	 * @throws IllegalArgumentException
	 *             if <code>element</code> or <code>attributeName</code> are
	 *             null
	 */
	public static String getAttributeValue(Element element, String attributeName) {
		if(element == null) {
			throw new IllegalArgumentException("Element cannot be null");
		}
		
		if(AssertUtils.isEmpty(attributeName)) {
			throw new IllegalArgumentException("Attribute name cannot be null/empty");
		}
		
		Attribute attribute = element.getAttribute(attributeName);
		if(attribute != null) {
			return attribute.getValue();
		}
		
		return null;
	}

	/**
	 * Returns the integer value of the attribute from the element.
	 * 
	 * @param element
	 *            the element from which the attribute is to be read
	 * 
	 * @param attributeName
	 *            the attribute name
	 * 
	 * @return the value of the attribute
	 * 
	 * @throws IllegalArgumentException
	 *             if <code>element</code> or <code>attributeName</code> are
	 *             null
	 */
	public static int getAttributeValueAsInt(Element element, String attributeName) {
		if(element == null) {
			throw new IllegalArgumentException("Element cannot be null");
		}
		
		if(AssertUtils.isEmpty(attributeName)) {
			throw new IllegalArgumentException("Attribute name cannot be null/empty");
		}
		
		Attribute attribute = element.getAttribute(attributeName);
		if(attribute != null) {
			try {
				return attribute.getIntValue();
			} catch (DataConversionException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	/**
	 * Returns the value of a given attribute for the given tagName amongst the
	 * supplied foreign markup values.
	 * 
	 * @param tagName
	 *            the tag name to find
	 * 
	 * @param attributeName
	 *            the attribute name to extract
	 * 
	 * @param elements
	 *            list of elements to search in
	 * 
	 * @return the value of the attribute if found, <code>null</code> otherwise
	 */
	public static String getTagAttribute(String tagName, String attributeName, List<Element> elements) {
		for(Element element : elements) {
			if(tagName.equals(element.getName())) {
				Attribute attribute = element.getAttribute(attributeName);
				if(attribute != null) {
					return attribute.getValue();
				}
			}
		}
		
		return null;
	}

	/**
	 * Returns the value of the element with the given tagName, where the
	 * supplied attribute matches the supplied attribute value in the supplied
	 * foreign markup values.
	 * 
	 * @param tagName
	 *            the tag to search for
	 * 
	 * @param attributeName
	 *            the attribute name to look for
	 * 
	 * @param attributeValue
	 *            the attribute value to look for
	 * 
	 * @param elements
	 *            the elements to search in
	 * 
	 * @return all the values of the attributes which have matched
	 * 
	 */
	public static List<String> getTagValues(String tagName, String attributeName, String attributeValue, List<Element> elements) {
		List<String> values = new ArrayList<String>();
		
		for(Element element : elements) {
			if(tagName.equals(element.getName())) {
				String value = element.getAttribute(attributeName).getValue();
				if(attributeValue.equals(value)) {
					values.add(element.getText());
				}
			}
		}
		
		return values;
	}
	
	/**
	 * Returns the value of the element with the given tagName amongst the
	 * supplied foreign markup values.
	 * 
	 * @param tagName
	 *            the tag name to look for
	 * 
	 * @param elements
	 *            the elements to search in
	 * 
	 * @return list of all values that match the tag name
	 * 
	 */
	public static List<String> getTagValues(String tagName, List<Element> elements) {
		List<String> values = new ArrayList<String>();
		
		for(Element element : elements) {
			if(tagName.equals(element.getName())) {
				values.add(element.getText());
			}
		}
		
		return values;
	}

}
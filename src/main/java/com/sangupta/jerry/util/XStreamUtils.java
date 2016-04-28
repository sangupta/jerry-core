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

import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.XStream;

/**
 * Utility class that provides an annotation configured {@link XStream} object.
 * Caches the list of classes that have been processed for faster processing.
 * 
 * @author sangupta
 *
 */
public abstract class XStreamUtils {
    
	/**
	 * The {@link XStream} object that keeps getting invoked and processed
	 */
	private static final XStream XSTREAM = new XStream();
	
	static {
		XSTREAM.autodetectAnnotations(true);
		XSTREAM.setMode(XStream.NO_REFERENCES);
	}
	
	/**
	 * Holds whether we have already processed the annotations for this class or not.
	 * Using a hashset instead of a list has an advantage that lookup for a key
	 * is O(1) and not O(n).
	 */
	private static Set<Class<?>> processed = new HashSet<Class<?>>();
	
	/**
	 * Returns the {@link XStream} object processing the annotations of the
	 * class supplied.
	 * 
	 * @param clazz
	 *            the {@link Class} type to process annotations for
	 * 
	 * @return the {@link XStream} object after processing the given classes
	 * 
	 */
	public static XStream getXStream(Class<?> clazz) {
		synchronized (processed) {
			if(!processed.contains(clazz)) {
				XSTREAM.processAnnotations(clazz);
				processed.add(clazz);
			}
		}
		
		return XSTREAM;
	}
	
	/**
	 * Returns the currently processed {@link XStream} object.
	 * 
	 * @return the current {@link XStream} object
	 */
	public static XStream getXStream() {
		return XSTREAM;
	}

}
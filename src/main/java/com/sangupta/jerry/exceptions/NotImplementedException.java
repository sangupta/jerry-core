/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012, Sandeep Gupta
 * 
 * http://www.sangupta/projects/jerry
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

package com.sangupta.jerry.exceptions;

/**
 * A {@link RuntimeException} that signifies that the functionality
 * intended at the point of execution has not yet been implemented. This
 * exception is meant only to be used during development and testing. Any
 * reporting of this error in the production system, indicates a feature
 * that has been skipped implementation.
 * 
 * @author sangupta
 *
 */
public class NotImplementedException extends RuntimeException {
	
	/**
	 * Generated via Eclipse
	 */
	private static final long serialVersionUID = 989609195314666208L;

	/**
	 * 
	 */
	public NotImplementedException() {
		super();
	}

	/**
	 * Convenience constructor
	 * 
	 * @param message the message that needs to be thrown as error
	 */
	public NotImplementedException(String message) {
		super(message);
	}

	/**
	 * Convenience constructor
	 * 
	 * @param t any root-cause for this error
	 */
	public NotImplementedException(Throwable t) {
		super(t);
	}

	/**
	 * Convenience constructor
	 * 
	 * @param message the message that needs to be thrown as error
	 * @param t any root-cause for this error
	 */
	public NotImplementedException(String message, Throwable t) {
		super(message, t);
	}
}

/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
 *
 * https://sangupta.com/projects/jerry-core
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

import com.sangupta.jerry.constants.HttpStatusCode;

/**
 * Exception that can be used to represent HTTP based exceptions
 * like in REST calls.
 *  
 * @author sangupta
 * @since 3.1.0
 */
public class HttpException extends RuntimeException {

	/**
	 * Generated via Eclipse
	 */
	private static final long serialVersionUID = -3852079813023916088L;

	/**
	 * The http code that this error represents
	 */
	public final int httpCode;

	/**
	 * The message that is being carried by this exception instance
	 */
	private String message;

	/**
	 * Convenience constructor
	 * 
	 * @param code the {@link HttpStatusCode} that this exception represents
	 * 
	 */
	public HttpException(int code) {
		this.httpCode = code;
	}

	/**
	 * Convenience constructor
	 * 
	 * @param code    the {@link HttpStatusCode} that this exception represents
	 * 
	 * @param message the message that needs to be carried
	 */
	public HttpException(int code, String message) {
		this.httpCode = code;
		this.message = message;
	}

	// Usual accessors follow

	/**
	 * /**
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}

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
 

package com.sangupta.jerry.security;

import java.security.Principal;

/**
 * A simple context that holds the currently valid {@link Principal}
 * for proper authentication and authorization.
 * 
 * @author sangupta
 *
 */
public class SecurityContext {
	
	/**
	 * Thread local instance to store the principal per thread
	 */
	private static ThreadLocal<Principal> holder = new ThreadLocal<Principal>();
	
	/**
	 * The anonymous user account
	 */
	private static Principal ANONYMOUS_USER_PRINCIPAL = null;
	
	/**
	 * Method that sets up the anonymous user account. If no user is assigned to
	 * the request, the anonymous user account will be returned.
	 * 
	 * @param principal
	 *            the {@link Principal} instance to use for anonymous users
	 */
	public static void setupAnonymousUserAccount(Principal principal) {
		ANONYMOUS_USER_PRINCIPAL = principal;
	}
	
	/**
	 * Setup a principal in this context
	 * 
	 * @param principal
	 *            the {@link Principal} instance of use for the currently
	 *            signed-in user
	 */
	public static void setContext(Principal principal) {
		holder.set(principal);
	}

	/**
	 * Return the currently set principal
	 * 
	 * @return the current principal, or the anonymous principal object if set
	 * 
	 */
	public static Principal getPrincipal() {
		Principal principal = holder.get();
		if(principal != null) {
			return principal;
		}
		
		return ANONYMOUS_USER_PRINCIPAL;
	}
	
	/**
	 * Check if the current user is anonymous or not.
	 * 
	 * @return <code>true</code> if no principal is set, or if the currently set
	 *         principal is the anonymous identified principal;
	 *         <code>false</code> otherwise
	 */
	public static boolean isAnonymousUser() {
		Principal principal = holder.get();
		if(principal == null) {
			return true;
		}
		
		// the following == check is intentional for we want to compare
		// object instances here, and not object values
		if(principal == ANONYMOUS_USER_PRINCIPAL) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Clear the security context of set principal
	 * 
	 */
	public static void clearPrincipal() {
		holder.remove();
	}
}
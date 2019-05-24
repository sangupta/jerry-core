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

package com.sangupta.jerry.security;

import java.security.Principal;

import com.sangupta.jerry.util.AssertUtils;

/**
 * A simple context that holds the currently valid {@link Principal} for proper
 * authentication and authorization.
 *
 * @author sangupta
 *
 */
public class SecurityContext {

	/**
	 * Thread local instance to store the principal per thread
	 */
	private static final ThreadLocal<Principal> PRINCIPAL_HOLDER = new ThreadLocal<Principal>();

	/**
	 * Thread local instance to store the tenant per thread
	 */
	private static final ThreadLocal<String> TENANT_HOLDER = new ThreadLocal<String>();

	/**
	 * The anonymous user account
	 */
	private static Principal ANONYMOUS_USER_PRINCIPAL = null;

	/**
	 * Method that sets up the anonymous user account. If no user is assigned to the
	 * request, the anonymous user account will be returned.
	 *
	 * @param principal the {@link Principal} instance to use for anonymous users
	 */
	public static void setupAnonymousUserAccount(Principal principal) {
		ANONYMOUS_USER_PRINCIPAL = principal;
	}

	/**
	 * Setup a principal in this context.
	 *
	 * @param principal the {@link Principal} instance of use for the currently
	 *                  signed-in user
	 */
	public static void setPrincipal(Principal principal) {
		PRINCIPAL_HOLDER.set(principal);
	}

	/**
	 * Setup a tenant in this context.
	 * 
	 * @param tenant the {@link String} based id for the tenant for which this
	 *               request is flowing.
	 */
	public static void setTenant(String tenant) {
		TENANT_HOLDER.set(tenant);
	}

	/**
	 * Return the currently set principal
	 *
	 * @return the current principal, or the anonymous principal object if set
	 *
	 */
	public static Principal getPrincipal() {
		Principal principal = PRINCIPAL_HOLDER.get();
		if (principal != null) {
			return principal;
		}

		return ANONYMOUS_USER_PRINCIPAL;
	}

	/**
	 * Check if the current user is anonymous or not.
	 *
	 * @return <code>true</code> if no principal is set, or if the currently set
	 *         principal is the anonymous identified principal; <code>false</code>
	 *         otherwise
	 */
	public static boolean isAnonymousUser() {
		Principal principal = PRINCIPAL_HOLDER.get();
		if (principal == null) {
			return true;
		}

		// the following == check is intentional for we want to compare
		// object instances here, and not object values
		if (principal == ANONYMOUS_USER_PRINCIPAL) {
			return true;
		}

		return false;
	}
	
	/**
	 * Check if the tenant ID provided is the same as the tenant
	 * in the {@link SecurityContext}.
	 * 
	 * @param tenant
	 * @return
	 */
	public static boolean isSameTenant(String tenant) {
		String current = TENANT_HOLDER.get();
		if(AssertUtils.isEmpty(current)) {
			if(AssertUtils.isEmpty(tenant)) { 
				return true;
			}
			
			return false;
		}
		
		return current.equals(tenant);
	}

	/**
	 * Clear the security context of set principal value.
	 *
	 */
	public static void clearPrincipal() {
		PRINCIPAL_HOLDER.remove();
	}

	/**
	 * Clear the security context of the set tenant value.
	 * 
	 */
	public static void clearTenant() {
		TENANT_HOLDER.remove();
	}

	/**
	 * Clear the security context of the set principal and tenant values.
	 * 
	 */
	public static void clear() {
		PRINCIPAL_HOLDER.remove();
		TENANT_HOLDER.remove();
	}
}

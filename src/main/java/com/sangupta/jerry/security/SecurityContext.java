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

import com.sangupta.jerry.entity.UserAwarePrincipal;
import com.sangupta.jerry.util.AssertUtils;

/**
 * A simple context that holds the currently valid {@link Principal} for proper
 * authentication and authorization.
 *
 * @author sangupta
 * 
 * @since 1.0.0
 *
 */
public class SecurityContext {

	/**
	 * Thread local instance to store the principal per thread
	 */
	private static final ThreadLocal<UserAwarePrincipal> PRINCIPAL_HOLDER = new ThreadLocal<>();
	
	/**
	 * Thread local instance to store the auth token if provided by the header
	 */
	private static final ThreadLocal<String> TOKEN_HOLDER = new ThreadLocal<>();

	/**
	 * Thread local instance to store the tenant per thread
	 */
	private static final ThreadLocal<String> TENANT_HOLDER = new ThreadLocal<>();

	/**
	 * 
	 */
	private static Class<? extends UserAwarePrincipal> userClass = null;

	/**
	 * Setup a principal in this context.
	 *
	 * @param principal the {@link Principal} instance of use for the currently
	 *                  signed-in user
	 */
	public static void setPrincipal(UserAwarePrincipal principal) {
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
	
	public static void setUserClass(Class<? extends UserAwarePrincipal> userClass) {
	    SecurityContext.userClass = userClass;
	}

	/**
	 * Return the currently set principal
	 *
	 * @return the current principal, or the anonymous principal object if set
	 *
	 */
	public static UserAwarePrincipal getPrincipal() {
	    return PRINCIPAL_HOLDER.get();
	}
	
	@SuppressWarnings("unchecked")
    public static <T> T getUser() {
	    if(SecurityContext.userClass == null) {
	        throw new IllegalStateException("Must set the user class via SecurityContext.setUserClass()");
	    }
	    
	    UserAwarePrincipal principal = getPrincipal();
	    if(principal == null) {
	        return null;
	    }
	    
	    return (T) SecurityContext.userClass.cast(principal);
	}
	
	public static String getUserToken() {
	    return TOKEN_HOLDER.get();
	}
	
	public static void setUserToken(String token) {
	    TOKEN_HOLDER.set(token);
	}
	
	/**
	 * Returns the user ID for the currently set {@link Principal}.
	 * 
	 * @since 4.0.0
	 * 
	 * @return
	 */
	public static String getUserID() {
	    UserAwarePrincipal principal = getPrincipal();
	    if(principal == null) {
	        return null;
	    }
	    
	    return principal.getUserID();
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
	
	public static void clearPrincipal() {
		PRINCIPAL_HOLDER.remove();
	}

	/**
	 * Clear the security context of the set principal and tenant values.
	 * 
	 */
	public static void clear() {
		PRINCIPAL_HOLDER.remove();
		TENANT_HOLDER.remove();
		TOKEN_HOLDER.remove();
	}
}

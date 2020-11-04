package com.sangupta.jerry.http;

/**
 * Enumeration for different strategies that help define how the tenant for an
 * incoming request needs to be identified.
 * 
 * @author sangupta
 *
 */
public enum TenantDetectionStrategy {
	
	/**
	 * HTTP request parameter defines the tenant
	 */
	REQUEST_PARAM,
	
	/**
	 * HTTP request header defines the tenant
	 */
	REQUEST_HTTP_HEADER,
	
	/**
	 * A path variable that shall be used to detect the tenant
	 */
	PATH_VARIABLE,
	
	/**
	 * In this case the user authentication system needs to detect
	 * and set the tenant based on user's entitlements.
	 * 
	 */
	USER_AUTH;

}

package com.sangupta.jerry.entity;

/**
 * Specifies that the entity being worked upon is tenant specific and thus
 * security checks for the same should be applied.
 * 
 * @author sangupta
 * 
 */
public interface TenantAwareEntity {
	
	public String getTenant();
	
}

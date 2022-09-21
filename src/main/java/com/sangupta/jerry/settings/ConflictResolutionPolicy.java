package com.sangupta.jerry.settings;

public enum ConflictResolutionPolicy {
	
	BLIND_COPY, // for everything
	
	MIN, // for numbers
	
	MAX, // for numbers
	
	GREATER_THAN, // for comparable
	
	LESS_THAN, // for comparable
	
	NON_ZERO, // for numbers
	
	NON_NULL, // for objects
	
	NON_NEGATIVE, // for numbers
	
	NON_EMPTY, // for strings
	
	UNION, // for collection
	
	INTERSECTION // for collection

}

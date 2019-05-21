package com.sangupta.jerry.entity;

/**
 * Interface to mark properties that can be used to auto-set time when they are
 * inserted in a data store.
 * 
 * @author sangupta
 *
 */
public interface CreationTimedEntity {

	public long getCreated();

	public void setCreated(long creationTime);

}

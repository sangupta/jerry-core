package com.sangupta.jerry.entity;

/**
 * Interface to mark properties that can be used to auto-set time when they are
 * inserted in a data store.
 * 
 * @author sangupta
 *
 */
public interface CreationTimeStampedEntity {

    /**
     * Get the time when the entity was created.
     * 
     * @return
     */
	public long getCreated();

	/**
	 * Set the time when the entity was created.
	 * 
	 * @param creationTime
	 */
	public void setCreated(long creationTime);

}

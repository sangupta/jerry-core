package com.sangupta.jerry.entity;

/**
 * Contract for any entity that wishes to track the creation time of the
 * entity in the data store.
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
